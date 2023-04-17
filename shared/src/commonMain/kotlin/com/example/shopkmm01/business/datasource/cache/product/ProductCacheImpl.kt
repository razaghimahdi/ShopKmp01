package com.example.shopkmm01.business.datasource.cache.product

import com.example.shopkmm01.business.domain.main.Product
import com.razzaghi.shopkmm01.Database
import com.razzaghi.shopkmm01.cache.ProductDbQueries


class ProductCacheImpl(
    private val database: Database,
) : ProductCache {

    private val queries: ProductDbQueries = database.productDbQueries

    override fun insert(product: Product) {
        queries.insertProduct(
            id = product.id.toLong(),
            title = product.title,
            price = product.price.toString(),
            description = product.description,
            category = product.category,
            image = product.image,
            rating_rate = product.rating.rate.toString(),
            rating_count = product.rating.count.toString()
        )
    }

    override fun insert(products: List<Product>) {
        for (product in products) {
            insert(product)
        }
    }


    override fun getAll(): List<Product> {
        return queries.selectAll().executeAsList().toProductList()
    }

    override fun get(productId: Int): Product? {
        return try {
            queries.getProductById(id = productId.toLong()).executeAsOne().toProduct()
        } catch (e: NullPointerException) {
            null
        }
    }
}

