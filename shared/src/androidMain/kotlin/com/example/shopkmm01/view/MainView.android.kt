package com.example.shopkmm01.view

import android.content.Context
import androidx.compose.runtime.Composable
import com.example.shopkmm01.common.DriverFactory
import com.example.shopkmm01.presentation.CommonView
import com.example.shopkmm01.presentation.ui.theme.ShopKmm01AppTheme

@Composable
fun AppViewAndroid(context:Context) {
   val sqlDriver = DriverFactory(context).createDriver()
    ShopKmm01AppTheme(sqlDriver) {
        CommonView(sqlDriver)
    }
}



