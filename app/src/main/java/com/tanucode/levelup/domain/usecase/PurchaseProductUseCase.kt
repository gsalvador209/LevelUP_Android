package com.tanucode.levelup.domain.usecase

import com.tanucode.levelup.domain.repository.OwnedStickerRepository

class PurchaseProductUseCase(
    private val repo: OwnedStickerRepository
) {
    suspend operator fun invoke(userId: String, productId: String) {
        repo.purchaseProduct(userId, productId)
    }
}
