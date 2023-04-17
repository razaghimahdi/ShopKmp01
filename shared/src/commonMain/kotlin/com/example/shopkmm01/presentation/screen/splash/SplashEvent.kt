package com.example.shopkmm01.presentation.screen.splash

import com.example.shopkmm01.business.core.UIComponent
import com.example.shopkmm01.business.domain.main.Category

sealed class SplashEvent{

    object GetCategories : SplashEvent()

    object GetProducts : SplashEvent()

    object RefreshFailedNetwork : SplashEvent()

    object OnRemoveHeadFromQueue : SplashEvent()

    data class Error(
        val uiComponent: UIComponent
    ): SplashEvent()

}
