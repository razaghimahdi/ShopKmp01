package com.example.shopkmm01.business.datasource.cache.product

import app.cash.sqldelight.db.SqlDriver
import com.example.shopkmm01.business.domain.main.Product
import com.razzaghi.shopkmm01.Database


interface ProductCache {

    fun insert(product: Product)

    fun insert(products:List<Product>)

    fun getAll():List<Product>

    fun get(id: Int):Product?

    companion object Factory {
        fun build(sqlDriver: SqlDriver): ProductCache {
            return ProductCacheImpl(Database(sqlDriver))
        }

        val schema  = Database.Schema

        val dbName: String = "shop.db"
    }

}