package com.example.shopkmm01.presentation.screen.splash

import app.cash.sqldelight.db.SqlDriver
import com.example.shopkmm01.business.core.DataState
import com.example.shopkmm01.business.core.Queue
import com.example.shopkmm01.business.core.UIComponent
import com.example.shopkmm01.business.domain.main.Category
import com.example.shopkmm01.business.interactors.main.MainInteractors
 import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class SplashViewModel(sqlDriver: SqlDriver) {

    private val viewModelScope = CoroutineScope(Dispatchers.Main)

    private lateinit var mainInteractors: MainInteractors


    internal val state = MutableStateFlow<SplashState>(SplashState())



    internal fun onTriggerEvent(event: SplashEvent) {
        when (event) {
            is SplashEvent.GetCategories -> {
                getCategories()
            }
            is SplashEvent.GetProducts -> {
                getProducts()
            }
            is SplashEvent.RefreshFailedNetwork -> {
                refreshFailedNetwork()
            }
            is SplashEvent.OnRemoveHeadFromQueue -> {
                removeHeadMessage()
            }
            is SplashEvent.Error -> {
                appendToMessageQueue(event.uiComponent)
            }
        }
    }


    init {
        mainInteractors = MainInteractors.build(sqlDriver)
        onTriggerEvent(SplashEvent.GetProducts)
    }

    private fun getProducts() {
        mainInteractors.getProducts.execute()
            .onEach { dataState ->
                when (dataState) {
                    is DataState.Response -> {
                        onTriggerEvent(SplashEvent.Error(dataState.uiComponent))
                    }
                    is DataState.Data -> {
                        dataState.data?.let {
                            getCategories()
                        }
                    }
                    is DataState.Loading -> {
                        state.value =
                            state.value.copy(progressBarState = dataState.progressBarState)
                    }
                }
            }.launchIn(viewModelScope)
    }

    private fun getCategories() {
        mainInteractors.getCategories.execute()
            .onEach { dataState ->
                when (dataState) {
                    is DataState.Response -> {
                        onTriggerEvent(SplashEvent.Error(dataState.uiComponent))
                    }
                    is DataState.Data -> {
                        dataState.data?.let {
                            state.value = state.value.copy(readyToNavigate = true)
                        }
                    }
                    is DataState.Loading -> {
                        state.value =
                            state.value.copy(progressBarState = dataState.progressBarState)
                    }
                }
            }.launchIn(viewModelScope)
    }

    private fun refreshFailedNetwork() {

    }

    private fun appendToMessageQueue(uiComponent: UIComponent) {
        if (uiComponent is UIComponent.None) {

            return
        }

        val queue = state.value.errorQueue
        queue.add(uiComponent)
        state.value = state.value.copy(errorQueue = Queue(mutableListOf())) // force recompose
        state.value = state.value.copy(errorQueue = queue)
    }

    private fun removeHeadMessage() {
        try {
            val queue = state.value.errorQueue
            queue.remove() // can throw exception if empty
            state.value = state.value.copy(errorQueue = Queue(mutableListOf())) // force recompose
            state.value = state.value.copy(errorQueue = queue)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


}