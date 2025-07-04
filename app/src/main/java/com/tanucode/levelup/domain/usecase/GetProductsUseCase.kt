package com.tanucode.levelup.domain.usecase

import com.tanucode.levelup.domain.model.Product
import com.tanucode.levelup.domain.repository.ProductRepository

class GetProductsUseCase(
    private val repo: ProductRepository
) {
    suspend operator fun invoke(): List<Product> =
        repo.getProducts()
}
