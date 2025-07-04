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
import com.tanucode.levelup.domain.repository.UserRepository
import com.tanucode.levelup.domain.usecase.GetProductsUseCase
import com.tanucode.levelup.domain.usecase.GetPurchasedProductsUseCase
import com.tanucode.levelup.domain.usecase.GetUserUseCase
import com.tanucode.levelup.domain.usecase.PurchaseProductUseCase
import com.tanucode.levelup.domain.usecase.UpdateUserCoinsUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LevelUpApp : Application() {


    val database: LevelUpDatabase by lazy { LevelUpDatabase.getDatabase(this) }

    val taskRepository: TaskRepository by lazy { TaskRepository(database,database.taskDao(),database.taskCompletionDao()) }

    val listRepository: ListRepository by lazy { ListRepository(database.listDao()) }

    private val userLocalDs by lazy { UserLocalDataSource(database.userDao()) }
    val userRepository: UserRepository by lazy {
        UserRepositoryImpl(userLocalDs)
    }

    val statsRepository by lazy { StatsRepositoryImpl(taskRepository) }

    val ownedStickerRepository: OwnedStickerRepository by lazy { OwnedStickerRepositoryImpl(ownedStickerLocalDs) }

    // DataSources
    private val productLocalDs by lazy { ProductLocalDataSource(database.productDao()) }
    private val spaceLocalDs   by lazy { SpaceLocalDataSource(database.spaceDao()) }

    private val productRemoteDs by lazy { ProductRemoteDataSource(ApiClient.productApi) }
    private val spaceRemoteDs   by lazy { SpaceRemoteDataSource(ApiClient.spaceApi) }

    val productRepository by lazy { ProductRepositoryImpl(productLocalDs,productRemoteDs) }

    private val ownedStickerLocalDs by lazy { OwnedStickerLocalDataSource(database.ownedStickerDao()) }

    //UseCases
    // 0) Use-case para leer el usuario desde DB
    val getUserUseCase: GetUserUseCase by lazy {
        GetUserUseCase(userRepository)
    }

    // 00) Use-case para actualizar las goldCoins del usuario
    val updateUserCoinsUseCase: UpdateUserCoinsUseCase by lazy {
        UpdateUserCoinsUseCase(userRepository)
    }
    // 1) Use-case para obtener la lista de productos
    val getProductsUseCase: GetProductsUseCase by lazy {
        GetProductsUseCase(productRepository)
    }

    // 2) Use-case para obtener los IDs de productos ya comprados
    val getPurchasedProductsUseCase: GetPurchasedProductsUseCase by lazy {
        GetPurchasedProductsUseCase(ownedStickerRepository)
    }

    // 3) Use-case compuesto para realizar una compra completa
    val buyProductUseCase: BuyProductUseCase by lazy {
        BuyProductUseCase(
            getUserUseCase,              // use-case de lectura de usuario
            updateUserCoinsUseCase,      // use-case de descuento de monedas
            PurchaseProductUseCase(ownedStickerRepository) // registra la compra
        )
    }



    override fun onCreate() {
        super.onCreate()

        CoroutineScope(Dispatchers.IO).launch {
            val userDao = database.userDao()

            if(userDao.getUserById() == null){
                userDao.insertUser(
                    UserEntity(
                        name       = "User",
                        avatarUri  = null,
                        goldCoins  = 0f,
                        silverCoins= 0f
                    )
                )
            }

            if(listRepository.getAllLists().isEmpty()) {
                listRepository.insertList(
                    ListEntity(
                        customName = null,
                        systemKey = "objectives",
                        type = "system",
                        colorId = 2,
                        icon = "ic_inbox",
                        sortOrder = 0
                    )
                )

                val userDao = database.userDao()
                if(userDao.getUserById() == null){
                    userDao.insertUser(
                        UserEntity(
                            name = "User",
                            avatarUri = null,
                            goldCoins = 0f,
                            silverCoins = 0f
                        )
                    )
                }

            }
        }
    }
}
