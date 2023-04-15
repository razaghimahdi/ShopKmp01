package com.example.shopkmm01.business.datasource.network.main

import com.example.shopkmm01.business.constants.Constants
import com.example.shopkmm01.business.datasource.network.main.responses.CategoryDTO
import com.example.shopkmm01.business.datasource.network.main.responses.ProductDTO
import com.example.shopkmm01.business.datasource.network.main.responses.toCategory
import com.example.shopkmm01.business.datasource.network.main.responses.toProduct
import com.example.shopkmm01.business.domain.main.Category
import com.example.shopkmm01.business.domain.main.Product
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlin.collections.get

class MainServiceImpl (
    private val httpClient: HttpClient
) : MainService {
    override suspend fun getProducts(): List<Product> {
        return httpClient.get {
            url {
                takeFrom(Constants.BASE_URL)
                encodedPath = Constants.PRODUCTS_URL
            }
        }.body<List<ProductDTO>>().map {it.toProduct()  }
    }

    override suspend fun getCategories(): List<Category> {
        return httpClient.get {
            url {
                takeFrom(Constants.BASE_URL)
                encodedPath = Constants.CATEGORY_URL
            }
        }.body<List<String>>().map {Category(it)  }
    }
}