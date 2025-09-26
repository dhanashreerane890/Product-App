package com.anywhere.product.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anywhere.product.data.api.mappers.toUiList
import com.anywhere.product.domain.usecase.FetchProductsUseCase
import com.anywhere.product.domain.usecase.ProductListingUseCase
import com.anywhere.product.presentation.model.ProductsUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProductViewModel(
    private val productListingUseCase: ProductListingUseCase,
    private val fetchProductsUseCase: FetchProductsUseCase
) : ViewModel() {

    private val _uiState: MutableStateFlow<ProductsUiState> = MutableStateFlow(ProductsUiState.None)
    val uiState: StateFlow<ProductsUiState> = _uiState.asStateFlow()

    init {
        fetchProducts()
    }

    fun fetchProducts() {
        viewModelScope.launch {

            fetchProductsUseCase.fetchProductsFromDb()
                .onStart {
                    _uiState.update {
                        ProductsUiState.Loading
                    }
                }
                .catch { e ->
                    _uiState.update {
                        ProductsUiState.Error(e.message.toString())
                    }
                }
                .map {
                    it.ifEmpty {
                        productListingUseCase.fetchProductsFromApi()
                    }
                }
                .collect { productList ->
                    _uiState.update {
                        ProductsUiState.Success(productList.toUiList())
                    }
                }

        }
    }


}

