package com.tanucode.levelup.domain.usecase

import com.tanucode.levelup.domain.repository.OwnedStickerRepository
import kotlinx.coroutines.flow.Flow

class GetPurchasedProductsUseCase(
    private val repo: OwnedStickerRepository
) {
    operator fun invoke(userId: String): Flow<List<String>> =
        repo.getPurchasedIdsFlow()
}
