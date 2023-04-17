package com.example.shopkmm01.business.interactors.main

import com.example.shopkmm01.business.constants.ErrorHandling
import com.example.shopkmm01.business.constants.ErrorHandling.GENERAL_ERROR_Title
import com.example.shopkmm01.business.core.DataState
import com.example.shopkmm01.business.core.ProgressBarState
import com.example.shopkmm01.business.core.UIComponent
import com.example.shopkmm01.business.datasource.cache.product.ProductCache
import com.example.shopkmm01.business.datasource.network.main.MainService
import com.example.shopkmm01.business.domain.main.Product
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetProductsFromCache(
    private val cache: ProductCache,
) {

    fun execute(): Flow<DataState<List<Product>>> = flow {
        try {
            emit(DataState.Loading(progressBarState = ProgressBarState.Loading))


            // emit data from cache
            val cachedProducts = cache.getAll()


            emit(DataState.Data(cachedProducts))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(
                DataState.Response<List<Product>>(
                    uiComponent = UIComponent.Dialog(
                        title = ErrorHandling.GENERAL_ERROR_Title,
                        description = e.message ?: ErrorHandling.GENERAL_ERROR_DESC
                    )
                )
            )
        } finally {
            emit(DataState.Loading(progressBarState = ProgressBarState.Idle))
        }
    }


}