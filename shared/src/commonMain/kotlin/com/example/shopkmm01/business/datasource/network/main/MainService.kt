package com.example.shopkmm01.business.datasource.network.main

import com.example.shopkmm01.business.core.KtorHttpClient
import com.example.shopkmm01.business.domain.main.Category
import com.example.shopkmm01.business.domain.main.Product


interface MainService{

    suspend fun getProducts(): List<Product>

    suspend fun getCategories(): List<Category>

    companion object Factory {
        fun build(): MainService {
            return MainServiceImpl(
                httpClient = KtorHttpClient.httpClient()
            )
        }
    }

}
