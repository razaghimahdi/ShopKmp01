package com.example.shopkmm01.presentation.screen.splash

import com.example.shopkmm01.business.core.ProgressBarState
import com.example.shopkmm01.business.core.Queue
import com.example.shopkmm01.business.core.UIComponent
import com.example.shopkmm01.business.domain.main.Category
import com.example.shopkmm01.business.domain.main.Product

data class SplashState(
    val progressBarState: ProgressBarState = ProgressBarState.Idle,
    val readyToNavigate: Boolean = false,
    val errorQueue: Queue<UIComponent> = Queue(mutableListOf()),
)
