package com.anywhere.product.data.api.repository

import com.anywhere.core.data.dao.ProductDao
import com.anywhere.logger.api.Logger
import com.anywhere.product.data.api.mappers.fromAPItoDomainList
import com.anywhere.product.data.api.mappers.fromDBtoDomainList
import com.anywhere.product.data.api.mappers.toEntityList
import com.anywhere.product.data.api.service.ProductApiService
import com.anywhere.product.domain.model.Product
import com.anywhere.product.domain.repository.ProductRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class ProductRepositoryImpl(
    private val productApiService: ProductApiService,
    private val productDao: ProductDao,
    private val coroutineDispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val logger: Logger
) : ProductRepository {

    override suspend fun fetchProducts(): Result<List<Product>> {
        return withContext(coroutineDispatcher) {
            val response = productApiService.getProducts()
            if (response.isSuccessful) {
                Result.success(response.body()?.products?.fromAPItoDomainList().orEmpty())
            } else {
                logger.error("Error: ${response.code()} ${response.message()}")
                Result.failure(Exception("Error: ${response.code()} ${response.message()}"))
            }
        }
    }


    override suspend fun clearProducts(products: List<Product>) {
        withContext(coroutineDispatcher) {
            logger.debug("Clearing products from database")
            productDao.insertProducts(products.toEntityList())
        }
    }

    override suspend fun insertProducts(products: List<Product>) {
        withContext(coroutineDispatcher) {
            logger.debug("Inserting products into database")
            productDao.insertProducts(products.toEntityList())
        }
    }

    override suspend fun getProductsFromDb(): Flow<List<Product>> {
        return withContext(coroutineDispatcher) {
            logger.debug("Fetching products from database")
            productDao.getAllProducts().map { entities ->
                entities.fromDBtoDomainList()
            }
        }
    }
}
