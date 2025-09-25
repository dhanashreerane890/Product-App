package com.anywhere.product.domain.usecase

import com.anywhere.product.domain.model.Product
import com.anywhere.product.domain.repository.ProductRepository

class ProductListingUseCase(
    private val productRepository: ProductRepository
) {

    suspend fun fetchProductsFromApi(): List<Product> {
        val productList = productRepository.fetchProducts().getOrDefault(emptyList())
        if (productList.isNotEmpty()) {
            productRepository.insertProducts(productList)
        }
        return productList
    }
}