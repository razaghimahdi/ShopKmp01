package com.example.shopkmm01.business.datasource.cache.category

import com.example.shopkmm01.business.domain.main.Category
import com.razzaghi.shopkmm01.cache.Category_Entity


fun String.toCategory(): Category {
    return Category(category = this)
}


fun List<String>.toCategoriesList(): List<Category> {
    return this.map { it.toCategory() }
}