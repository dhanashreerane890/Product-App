package com.anywhere.product.data.api.service

import com.anywhere.product.data.api.model.ProductApiResponse
import retrofit2.Response
import retrofit2.http.GET

interface ProductApiService {
    @GET("products")
    suspend fun getProducts(): Response<ProductApiResponse>
}