package com.razzaghi.shopkmm01

import androidx.compose.runtime.Composable
import com.example.shopkmm01.common.DriverFactory
import com.example.shopkmm01.presentation.CommonView

@Composable
internal fun AppViewiOS() {
    CommonView(DriverFactory().createDriver())
}