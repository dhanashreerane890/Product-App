package com.anywhere.product.domain.repository

import com.anywhere.product.domain.model.Product
import kotlinx.coroutines.flow.Flow

interface ProductRepository {

    suspend fun fetchProducts(): Result<List<Product>>

    suspend fun insertProducts(products: List<Product>)

    suspend fun clearProducts(products: List<Product>)

    suspend fun getProductsFromDb(): Flow<List<Product>>

}



