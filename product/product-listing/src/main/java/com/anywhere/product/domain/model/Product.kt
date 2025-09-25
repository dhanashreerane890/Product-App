package com.anywhere.product.domain.model

data class Product(
    val id: Int,
    val title: String,
    val description: String,
    val price: Double,
    val thumbnail: String,
    val category: String
)