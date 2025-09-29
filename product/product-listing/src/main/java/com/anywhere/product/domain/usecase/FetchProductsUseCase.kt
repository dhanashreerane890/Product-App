package com.anywhere.product.domain.usecase

import com.anywhere.product.domain.model.Product
import com.anywhere.product.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow

class FetchProductsUseCase(
    private val productRepository: ProductRepository
) {
    suspend fun fetchProductsFromDb(): Flow<List<Product>> {
        return productRepository.getProductsFromDb()
    }
}