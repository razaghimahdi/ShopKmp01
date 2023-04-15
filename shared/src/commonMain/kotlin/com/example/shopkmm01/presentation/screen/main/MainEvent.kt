package com.example.shopkmm01.presentation.screen.main

import com.example.shopkmm01.business.core.UIComponent
import com.example.shopkmm01.business.domain.main.Category

sealed class MainEvent{

    object GetCategories : MainEvent()

    data class UpdateSelectedCategory(val category: Category) : MainEvent()

    object GetProducts : MainEvent()

    object RefreshFailedNetwork : MainEvent()

    object OnRemoveHeadFromQueue : MainEvent()

    data class Error(
        val uiComponent: UIComponent
    ): MainEvent()

}
