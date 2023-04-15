package com.example.shopkmm01.business.interactors.main

import com.example.shopkmm01.business.constants.ErrorHandling
import com.example.shopkmm01.business.constants.ErrorHandling.GENERAL_ERROR_Title
import com.example.shopkmm01.business.core.DataState
import com.example.shopkmm01.business.core.ProgressBarState
import com.example.shopkmm01.business.core.UIComponent
import com.example.shopkmm01.business.datasource.network.main.MainService
import com.example.shopkmm01.business.domain.main.Product
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetProducts(
    private val service: MainService,
) {


    fun execute(): Flow<DataState<List<Product>>> = flow {
        try {
            emit(DataState.Loading(progressBarState = ProgressBarState.Loading))


          /*  val Shops: List<Shop> = try { // catch network exceptions
                service.getShopList(
                    page = page,
                    status = status,
                    dateFilter = dateFilter,
                    importanceFilter = importanceFilter,
                    group_id = group_id,
                    user_session = userSession
                )
            } catch (e: Exception) {
                e.printStackTrace() // log to crashlytics?
                Log.i(TAG, "execute e: " + e.message)
                if (isShopInDatabaseEmpty()) {
                    throw Exception(ErrorHandling.FAILED_NETWORK)
                }
                listOf<Shop>()
            }


            // cache the network data
            cache.insert(Shops)

            // emit data from cache
            //val cachedShops = cache.selectAllByPaging(page = page, status = status)
            val cachedShops = cache.selectAllByPaging(
                page = page,
                status = status,
                dateFilter = dateFilter,
                importanceFilter = importanceFilter
            )
            */

            val products = service.getProducts()


            emit(DataState.Data(products))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(DataState.Response<List<Product>>(
                uiComponent = UIComponent.Dialog(
                    title = ErrorHandling.GENERAL_ERROR_Title,
                    description =  e.message?:ErrorHandling.GENERAL_ERROR_DESC
                )
            ))
        } finally {
            emit(DataState.Loading(progressBarState = ProgressBarState.Idle))
        }
    }


}