package com.example.shopkmm01.business.core

sealed class ProgressBarState{

    object Loading: ProgressBarState()

    object Idle: ProgressBarState()

}

