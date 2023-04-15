package com.example.shopkmm01.business.interactors.main

import com.example.shopkmm01.business.datasource.network.main.MainService

data class MainInteractors(
    val getProducts: GetProducts,
    val getCategories: GetCategories,
){


    companion object Factory {
        fun build(): MainInteractors {
            val service = MainService.build()
            return MainInteractors(
                getProducts = GetProducts(
                    service = service,
                ),
                getCategories = GetCategories(
                    service = service,
                ),
            )
        }

    }



}
