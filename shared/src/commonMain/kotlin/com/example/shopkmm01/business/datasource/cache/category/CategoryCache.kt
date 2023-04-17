package com.example.shopkmm01.business.datasource.cache.category

import app.cash.sqldelight.db.SqlDriver
import com.example.shopkmm01.business.domain.main.Category
import com.razzaghi.shopkmm01.Database


interface CategoryCache {

    fun insert(category: Category)

    fun insert(categories:List<Category>)

    fun getAll():List<Category>

    companion object Factory {
        fun build(sqlDriver: SqlDriver): CategoryCache {
            return CategoryCacheImpl(Database(sqlDriver))
        }

        val schema  = Database.Schema

        val dbName: String = "shop.db"
    }

}