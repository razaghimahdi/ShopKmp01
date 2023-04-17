package com.example.shopkmm01.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import app.cash.sqldelight.db.SqlDriver
import com.example.shopkmm01.presentation.screen.details.DetailScreen
import com.example.shopkmm01.presentation.screen.main.MainScreen
import com.example.shopkmm01.presentation.screen.main.MainViewModel
import com.example.shopkmm01.presentation.screen.splash.SplashScreen
import com.example.shopkmm01.presentation.screen.splash.SplashViewModel
import com.example.shopkmm01.presentation.ui.screen_navigation.Navigation
import com.example.shopkmm01.presentation.ui.screen_navigation.ScreensState
import com.example.shopkmm01.presentation.ui.theme.ShopKmm01Theme


@Composable
internal fun CommonView(sqlDriver: SqlDriver) {
    val mainViewModel = lazy { MainViewModel(sqlDriver) }
    val splashViewModel = lazy{ SplashViewModel(sqlDriver) }
    val screenNavigationState = remember { mutableStateOf(ScreensState()) }

    ShopKmm01Theme() {

        when (val state = screenNavigationState.value.navigation) {
            is Navigation.DetailNavigation -> DetailScreen(
                navigationState = screenNavigationState,
                product = state.product
            )
            is Navigation.SplashNavigation -> SplashScreen(
                navigationState = screenNavigationState,
                splashViewModel.value
            )
            Navigation.MainNavigation -> MainScreen(screenNavigationState, mainViewModel.value)
        }
    }
}
