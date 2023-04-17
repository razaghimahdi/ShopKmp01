package com.example.shopkmm01.business.interactors.main

import app.cash.sqldelight.db.SqlDriver
import com.example.shopkmm01.business.datasource.cache.category.CategoryCache
import com.example.shopkmm01.business.datasource.cache.product.ProductCache
import com.example.shopkmm01.business.datasource.network.main.MainService

data class MainInteractors(
    val getProducts: GetProducts,
    val getCategories: GetCategories,
    val getProductsFromCache: GetProductsFromCache,
    val getCategoriesFromCache: GetCategoriesFromCache,
) {


    companion object Factory {
        fun build(sqlDriver: SqlDriver,): MainInteractors {
            val service = MainService.build()
            val productCache = ProductCache.build(sqlDriver)
            val categoryCache = CategoryCache.build(sqlDriver)
            return MainInteractors(
                getProducts = GetProducts(
                    service = service,
                    cache = productCache,
                ),
                getCategories = GetCategories(
                    service = service,
                    cache = categoryCache,
                ),
                getProductsFromCache = GetProductsFromCache(
                    cache = productCache,
                ),
                getCategoriesFromCache = GetCategoriesFromCache(
                    cache = categoryCache,
                ),
            )
        }

    }


}
