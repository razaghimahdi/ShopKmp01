package com.example.shopkmm01.business.datasource.cache.product

import com.example.shopkmm01.business.domain.main.Product
import com.example.shopkmm01.business.domain.main.Rating
import com.razzaghi.shopkmm01.cache.Product_Entity

fun Product_Entity.toProduct(): Product {
    return Product(
        id = id.toInt(),
        title = title,
        price = price.toDouble(),
        description = description,
        category = category,
        image = image,
        rating = Rating(rate = rating_rate.toDouble(), count = rating_count.toInt())
    )
}

fun List<Product_Entity>.toProductList(): List<Product> {
    return this.map { it.toProduct() }
}