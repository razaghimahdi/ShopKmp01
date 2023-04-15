package com.example.shopkmm01.presentation.ui.screen_navigation

import com.example.shopkmm01.business.domain.main.Product

sealed interface Navigation {
    object MainNavigation : Navigation
    data class DetailNavigation(val product: Product) : Navigation
}

data class ScreensState(val navigation: Navigation = Navigation.MainNavigation)