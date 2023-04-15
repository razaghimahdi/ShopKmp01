package com.example.shopkmm01.presentation

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import com.example.shopkmm01.presentation.screen.details.DetailScreen
import com.example.shopkmm01.presentation.screen.main.MainScreen
import com.example.shopkmm01.presentation.screen.main.MainViewModel
import com.example.shopkmm01.presentation.ui.screen_navigation.Navigation
import com.example.shopkmm01.presentation.ui.screen_navigation.ScreensState
import com.example.shopkmm01.presentation.ui.theme.ShopKmm01Theme
import com.example.shopkmm01.presentation.ui.theme.style.ShopKmm01AppColors


@Composable
internal fun CommonView() {
    val viewModel = MainViewModel()
    val screenNavigationState = remember { mutableStateOf(ScreensState()) }

    ShopKmm01Theme() {

        when (val state = screenNavigationState.value.navigation) {
            is Navigation.DetailNavigation -> DetailScreen(
                navigationState = screenNavigationState,
                product = state.product
            )
            Navigation.MainNavigation -> MainScreen(screenNavigationState, viewModel)
        }
    }
}
