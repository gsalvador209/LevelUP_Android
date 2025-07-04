package com.tanucode.levelup.application

import BuyProductUseCase
import android.app.Application
import com.tanucode.levelup.data.db.LevelUpDatabase
import com.tanucode.levelup.data.db.entity.ListEntity
import com.tanucode.levelup.data.db.entity.UserEntity
import com.tanucode.levelup.data.local.datasource.OwnedStickerLocalDataSource
import com.tanucode.levelup.data.local.datasource.ProductLocalDataSource
import com.tanucode.levelup.data.local.datasource.SpaceLocalDataSource
import com.tanucode.levelup.data.local.datasource.UserLocalDataSource
import com.tanucode.levelup.data.remote.api.ApiClient
import com.tanucode.levelup.data.remote.datasource.ProductRemoteDataSource
import com.tanucode.levelup.data.remote.datasource.SpaceRemoteDataSource
import com.tanucode.levelup.data.repository.ListRepository
import com.tanucode.levelup.data.repository.OwnedStickerRepositoryImpl
import com.tanucode.levelup.data.repository.ProductRepositoryImpl
import com.tanucode.levelup.data.repository.StatsRepositoryImpl
import com.tanucode.levelup.data.repository.TaskRepository
import com.tanucode.levelup.data.repository.UserRepositoryImpl
import com.tanucode.levelup.domain.repository.OwnedStickerRepository
import com.tanucode.levelup.domain.repository.ProductRepository
import com.tanucode.levelup.domain.repository.SpaceRepository
import com.tanucode.levelup.domain.repository.UserRepository
import com.tanucode.levelup.domain.usecase.GetProductsUseCase
import com.tanucode.levelup.domain.usecase.GetPurchasedProductsUseCase
import com.tanucode.levelup.domain.usecase.GetUserUseCase
import com.tanucode.levelup.domain.usecase.PurchaseProductUseCase
import com.tanucode.levelup.domain.usecase.UpdateUserCoinsUseCase
import com.tanucode.levelup.domain.usecase.UpdateUserSilverCoinsUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LevelUpApp : Application() {

    // --- Database ---
    val database: LevelUpDatabase by lazy {
        LevelUpDatabase.getDatabase(this)
    }

    // --- Local DataSources ---
    private val userLocalDataSource by lazy {
        UserLocalDataSource(database.userDao())
    }
    private val productLocalDataSource by lazy {
        ProductLocalDataSource(database.productDao())
    }
    private val spaceLocalDataSource by lazy {
        SpaceLocalDataSource(database.spaceDao())
    }
    private val ownedStickerLocalDataSource by lazy {
        OwnedStickerLocalDataSource(database.ownedStickerDao())
    }

    // --- Remote DataSources ---
    private val productRemoteDataSource by lazy {
        ProductRemoteDataSource(ApiClient.productApi)
    }
    private val spaceRemoteDataSource by lazy {
        SpaceRemoteDataSource(ApiClient.spaceApi)
    }

    // --- Repositories ---
    val userRepository: UserRepository by lazy {
        UserRepositoryImpl(userLocalDataSource)
    }
    val productRepository: ProductRepository by lazy {
        ProductRepositoryImpl(productLocalDataSource, productRemoteDataSource)
    }
//    val spaceRepository: SpaceRepository by lazy {
//        SpaceRepositoryImpl(spaceLocalDataSource, spaceRemoteDataSource)
//    }
    val ownedStickerRepository: OwnedStickerRepository by lazy {
        OwnedStickerRepositoryImpl(ownedStickerLocalDataSource)
    }
    val listRepository: ListRepository by lazy {
        ListRepository(database.listDao())
    }
    val taskRepository: TaskRepository by lazy {
        TaskRepository(database, database.taskDao(), database.taskCompletionDao())
    }
    val statsRepository by lazy {
        StatsRepositoryImpl(taskRepository)
    }

    // --- UseCases: User ---
    val getUserUseCase: GetUserUseCase by lazy {
        GetUserUseCase(userRepository)
    }
    val updateUserCoinsUseCase: UpdateUserCoinsUseCase by lazy {
        UpdateUserCoinsUseCase(userRepository)
    }
    val updateUserSilverCoinsUseCase: UpdateUserSilverCoinsUseCase by lazy {
        UpdateUserSilverCoinsUseCase(userRepository)
    }

    // --- UseCases: Products / Stickers ---
    val getProductsUseCase: GetProductsUseCase by lazy {
        GetProductsUseCase(productRepository)
    }
    val getPurchasedProductsUseCase: GetPurchasedProductsUseCase by lazy {
        GetPurchasedProductsUseCase(ownedStickerRepository)
    }
    val buyProductUseCase: BuyProductUseCase by lazy {
        BuyProductUseCase(
            getUserUseCase,
            updateUserCoinsUseCase,
            PurchaseProductUseCase(ownedStickerRepository)
        )
    }

    // --- UseCases: Tasks & Lists ---
//    val getTasksWithListUseCase: GetTasksWithListUseCase by lazy {
//        GetTasksWithListUseCase(taskRepository)
//    }
//    val toggleTaskCompletionUseCase: ToggleTaskCompletionUseCase by lazy {
//        ToggleTaskCompletionUseCase(taskRepository)
//    }

    override fun onCreate() {
        super.onCreate()

        // Seed initial data: user and default lists
        CoroutineScope(Dispatchers.IO).launch {
            // 1) Seed User
            val userDao = database.userDao()
            if (userDao.getUserById() == null) {
                userDao.insertUser(
                    UserEntity(
                        name        = "User",
                        avatarUri   = null,
                        goldCoins   = 0f,
                        silverCoins = 0f
                    )
                )
            }

            // 2) Seed default lists: Inbox first, then Objectives
            if (listRepository.getAllLists().isEmpty()) {
                listRepository.insertList(
                    ListEntity(
                        customName = null,
                        systemKey  = "inbox",
                        type       = "system",
                        colorId    = 1,
                        icon       = "ic_inbox",
                        sortOrder  = 0
                    )
                )
                listRepository.insertList(
                    ListEntity(
                        customName = null,
                        systemKey  = "objectives",
                        type       = "system",
                        colorId    = 2,
                        icon       = "ic_objectives",
                        sortOrder  = 1
                    )
                )
            }
        }
    }
}
