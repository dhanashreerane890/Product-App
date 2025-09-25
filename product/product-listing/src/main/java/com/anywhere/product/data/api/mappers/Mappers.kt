package com.anywhere.product.data.api.mappers

import com.anywhere.core.data.model.ProductEntity
import com.anywhere.product.data.api.model.ProductApiResponse
import com.anywhere.product.domain.model.Product
import com.anywhere.product.presentation.model.ProductUi


fun ProductEntity.toDomain(): Product {
    return Product(
        id = this.id,
        title = this.title,
        description = this.description,
        price = this.price,
        thumbnail = this.thumbnail,
        category = this.category
    )
}

fun Product.toEntity(): ProductEntity {
    return ProductEntity(
        id = this.id,
        title = this.title,
        description = this.description,
        price = this.price,
        thumbnail = this.thumbnail,
        category = this.category
    )
}

fun Product.toUi(): ProductUi {
    return ProductUi(
        id = this.id,
        title = this.title,
        description = this.description,
        price = this.price,
        thumbnail = this.thumbnail,
        category = this.category
    )
}

fun ProductApiResponse.Product.toDomain(): Product {
    return Product(
        id = this.id,
        title = this.title,
        description = this.description,
        price = this.price,
        thumbnail = this.thumbnail,
        category = this.category
    )
}

@JvmName("toDomainListFromApi")
fun List<ProductApiResponse.Product>.toDomainList(): List<Product> {
    return this.map {
        it.toDomain()
    }
}

@JvmName("toDomainListFromEntities")
fun List<ProductEntity>.toDomainList(): List<Product> {
    return this.map {
        it.toDomain()
    }
}

fun List<Product>.toEntityList(): List<ProductEntity> = map { it.toEntity() }

fun List<Product>.toUiList(): List<ProductUi> = map { it.toUi() }


