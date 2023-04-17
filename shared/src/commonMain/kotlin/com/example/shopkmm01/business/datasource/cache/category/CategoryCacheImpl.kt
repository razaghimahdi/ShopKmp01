package com.example.shopkmm01.business.datasource.cache.category

import com.example.shopkmm01.business.domain.main.Category
import com.razzaghi.shopkmm01.Database
import com.razzaghi.shopkmm01.cache.CategoryDbQueries


class CategoryCacheImpl(
    private val database: Database,
) : CategoryCache {

    private val queries: CategoryDbQueries = database.categoryDbQueries

    override fun insert(category: Category) {
        queries.insertCategory(category = category.category)
    }

    override fun insert(categories: List<Category>) {
        for (category in categories) {
            insert(category)
        }
    }


    override fun getAll(): List<Category> {
        return queries.selectAll().executeAsList().toCategoriesList()
    }

}

