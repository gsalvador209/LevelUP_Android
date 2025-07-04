package com.tanucode.levelup.domain.usecase

import com.tanucode.levelup.domain.repository.OwnedStickerRepository

class GetPurchasedProductsUseCase(
    private val repo: OwnedStickerRepository
) {
    suspend operator fun invoke(userId: String): List<String> =
        repo.getPurchasedProductIds(userId)
}
