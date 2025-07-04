package com.tanucode.levelup.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.tanucode.levelup.domain.usecase.GetProductsUseCase
import com.tanucode.levelup.domain.model.Product
import com.tanucode.levelup.domain.usecase.GetPurchasedProductsUseCase
import com.tanucode.levelup.domain.usecase.PurchaseProductUseCase
import com.tanucode.levelup.util.Constants
import kotlinx.coroutines.launch

class StoreViewModel(
    private val getProducts: GetProductsUseCase,
    private val getPurchasedProductsUseCase: GetPurchasedProductsUseCase,
    private val purchaseProductUseCase: PurchaseProductUseCase
) : ViewModel() {

    private val _products = MutableLiveData<List<Product>>(emptyList())
    val products: LiveData<List<Product>> = _products

    private val _purchasedIds = MutableLiveData<Set<String>>(emptySet())
    val purchasedIds: LiveData<Set<String>> = _purchasedIds

    init {
        fetchAll()
        fetchPurchased()
    }

    private fun fetchAll() {
        viewModelScope.launch {
            val list = try {
                getProducts()
            } catch (e: Exception) {
                emptyList()
            }
            _products.postValue(list)
        }
    }

    private fun fetchPurchased() {
        viewModelScope.launch {
            val userId = Constants.USER_ID_SINGLETON
            _purchasedIds.value = getPurchasedProductsUseCase(userId).toSet()
        }
    }

    fun purchaseProduct(productId: String) {
        viewModelScope.launch {
            val userId = "current_user_id"
            purchaseProductUseCase(userId, productId)
            // Volvemos a recargar los IDs comprados
            _purchasedIds.value = getPurchasedProductsUseCase(userId).toSet()
        }
    }
}
