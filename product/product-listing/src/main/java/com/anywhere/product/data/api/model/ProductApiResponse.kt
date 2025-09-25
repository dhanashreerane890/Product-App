package com.anywhere.product.data.api.model

data class ProductApiResponse(
    val products: List<Product>,
    val total: Int,
    val skip: Int,
    val limit: Int
){
    data class Product(
        val id: Int,
        val title: String,
        val description: String,
        val price: Double,
        val thumbnail: String,
        val category: String
    )
}