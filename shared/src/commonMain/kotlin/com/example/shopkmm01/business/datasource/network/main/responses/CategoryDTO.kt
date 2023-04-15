package com.example.shopkmm01.business.datasource.network.main.responses

import com.example.shopkmm01.business.domain.main.Category

data class CategoryDTO(val category: String)

fun CategoryDTO.toCategory() = Category(this.category)