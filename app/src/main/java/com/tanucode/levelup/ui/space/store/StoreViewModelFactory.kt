package com.tanucode.levelup.presentation.ui.store

import BuyProductUseCase
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tanucode.levelup.domain.usecase.GetProductsUseCase
import com.tanucode.levelup.domain.usecase.GetPurchasedProductsUseCase
import com.tanucode.levelup.domain.usecase.PurchaseProductUseCase
import com.tanucode.levelup.presentation.viewmodel.StoreViewModel

class StoreViewModelFactory(
    private val getProductsUseCase: GetProductsUseCase,
    private val getPurchasedProductsUseCase: GetPurchasedProductsUseCase,
    private val buyProductUseCase: BuyProductUseCase
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(StoreViewModel::class.java)) {
            return StoreViewModel(
                getProductsUseCase,
                getPurchasedProductsUseCase,
                buyProductUseCase
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }
}
