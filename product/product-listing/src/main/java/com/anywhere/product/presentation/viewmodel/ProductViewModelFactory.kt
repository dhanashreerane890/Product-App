package com.anywhere.product.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.CreationExtras
import com.anywhere.core.data.database.AppDatabase
import com.anywhere.logger.api.Logger
import com.anywhere.logger.api.createLogger
import com.anywhere.logger.impl.LoggerImpl
import com.anywhere.network.NetworkModule
import com.anywhere.product.data.api.repository.ProductRepositoryImpl
import com.anywhere.product.data.api.service.ProductApiService
import com.anywhere.product.domain.usecase.FetchProductsUseCase
import com.anywhere.product.domain.usecase.ProductListingUseCase

class ProductViewModelFactory() : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {

        if (modelClass.isAssignableFrom(ProductViewModel::class.java)) {

            val application = requireNotNull(extras[APPLICATION_KEY])
            val apiService = NetworkModule.getRetrofit().create(ProductApiService::class.java)
            val database = AppDatabase.getDatabase(
                context = application
            )
            val loggerFactory: Logger.Factory = LoggerImpl.Factory()
            val repository = ProductRepositoryImpl(
                productApiService = apiService,
                productDao = database.productDao(),
                logger = loggerFactory.createLogger()
            )

            @Suppress("UNCHECKED_CAST")
            return ProductViewModel(
                productListingUseCase = ProductListingUseCase(
                    productRepository = repository
                ),
                fetchProductsUseCase = FetchProductsUseCase(
                    productRepository = repository
                )
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }
}