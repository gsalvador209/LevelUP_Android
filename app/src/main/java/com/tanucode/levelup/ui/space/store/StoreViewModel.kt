package com.tanucode.levelup.presentation.viewmodel

import BuyProductUseCase
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.android.material.snackbar.Snackbar
import com.tanucode.levelup.domain.usecase.GetProductsUseCase
import com.tanucode.levelup.domain.model.Product
import com.tanucode.levelup.domain.usecase.GetPurchasedProductsUseCase
import com.tanucode.levelup.domain.usecase.PurchaseProductUseCase
import com.tanucode.levelup.util.Constants
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class StoreViewModel(
    private val getProducts: GetProductsUseCase,
    private val getPurchasedFlow: GetPurchasedProductsUseCase,
    private val buyProduct: BuyProductUseCase
) : ViewModel() {

    private val _products = MutableLiveData<List<Product>>(emptyList())
    val products: LiveData<List<Product>> = _products

    //private val _purchasedIds = MutableLiveData<Set<String>>(emptySet())
    val purchasedIds: StateFlow<Set<String>> =
        getPurchasedFlow(Constants.USER_ID_SINGLETON)
            .map{ it.toSet() }
            .stateIn(viewModelScope, SharingStarted.Eagerly,emptySet())

    private val _buyResult = MutableLiveData<Boolean>()
    val buyResult: LiveData<Boolean> = _buyResult


    init {
        fetchAll()
        //fetchPurchased()
    }

    fun onBuy(product: Product) {
        viewModelScope.launch {
            val success = buyProduct(product)
            _buyResult.postValue(success)
        }
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

//    private fun fetchPurchased() {
//        viewModelScope.launch {
//            val userId = Constants.USER_ID_SINGLETON
//            //_purchasedIds.value = getPurchasedProductsUseCase(userId).toSet()
//        }
//    }
}
