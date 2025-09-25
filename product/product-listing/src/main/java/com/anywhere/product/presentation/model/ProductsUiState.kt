package com.anywhere.product.presentation.model

sealed interface ProductsUiState {
    data class Success(val data: List<ProductUi>) : ProductsUiState
    object Loading : ProductsUiState
    data class Error(val message: String) : ProductsUiState
    object None : ProductsUiState
}


data class ProductUi(
    val id: Int,
    val title: String,
    val price: Double,
    val thumbnail: String,
    val category: String,
    val description: String
)