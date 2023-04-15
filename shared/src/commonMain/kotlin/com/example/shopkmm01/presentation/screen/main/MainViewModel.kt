package com.example.shopkmm01.presentation.screen.main

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
import kotlinx.coroutines.launch

class MainViewModel {
    private val viewModelScope = CoroutineScope(Dispatchers.Main)

    private val mainInteractors = MainInteractors.build()

    val state = MutableStateFlow<MainState>(MainState())


    fun onTriggerEvent(event: MainEvent) {
        when (event) {
            is MainEvent.GetCategories -> {
                getCategories()
            }
            is MainEvent.UpdateSelectedCategory -> {
                updateSelectedCategory(value = event.category)
            }
            is MainEvent.GetProducts -> {
                getProducts()
            }
            is MainEvent.RefreshFailedNetwork -> {
                refreshFailedNetwork()
            }
            is MainEvent.OnRemoveHeadFromQueue -> {
                removeHeadMessage()
            }
            is MainEvent.Error -> {
                appendToMessageQueue(event.uiComponent)
            }
        }
    }


    init {
        onTriggerEvent(MainEvent.GetProducts)
    }

    private fun updateSelectedCategory(value: Category) {
        state.value = state.value.copy(selectedCategory = value )
    }

    private fun getProducts() {
        mainInteractors.getProducts.execute()
            .onEach { dataState ->
                when (dataState) {
                    is DataState.Response -> {
                        onTriggerEvent(MainEvent.Error(dataState.uiComponent))
                    }
                    is DataState.Data -> {
                        dataState.data?.let {
                            state.value = state.value.copy(products = it)
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
                        onTriggerEvent(MainEvent.Error(dataState.uiComponent))
                    }
                    is DataState.Data -> {
                        dataState.data?.let {
                            val list = arrayListOf<Category>()
                            list.add(Category("all"))
                            list.addAll(it)
                            state.value = state.value.copy(categories = list)
                            onTriggerEvent(MainEvent.UpdateSelectedCategory(list[0]))
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