package com.example.shopkmm01.business.interactors.main

import com.example.shopkmm01.business.constants.ErrorHandling
import com.example.shopkmm01.business.core.DataState
import com.example.shopkmm01.business.core.ProgressBarState
import com.example.shopkmm01.business.core.UIComponent
import com.example.shopkmm01.business.datasource.cache.category.CategoryCache
import com.example.shopkmm01.business.datasource.network.main.MainService
import com.example.shopkmm01.business.domain.main.Category
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetCategories(
    private val service: MainService,
    private val cache: CategoryCache
) {


    fun execute(): Flow<DataState<List<Category>>> = flow {
        try {
            emit(DataState.Loading(progressBarState = ProgressBarState.Loading))


            val categories: List<Category> = try { // catch network exceptions
                service.getCategories()
            } catch (e: Exception) {
                e.printStackTrace() // log to crashlytics?
                listOf<Category>()
            }


            // cache the network data
            cache.insert(categories)

            // emit data from cache
            val cachedShops = cache.getAll()

            emit(DataState.Data(cachedShops))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(
                DataState.Response<List<Category>>(
                    uiComponent = UIComponent.Dialog(
                        title = "",
                        description = ErrorHandling.GENERAL_ERROR
                    )
                )
            )
        } finally {
            emit(DataState.Loading(progressBarState = ProgressBarState.Idle))
        }
    }


}