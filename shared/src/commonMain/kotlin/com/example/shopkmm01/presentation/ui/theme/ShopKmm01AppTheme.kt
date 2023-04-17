package com.example.shopkmm01.presentation.ui.theme

import androidx.compose.runtime.Composable
import app.cash.sqldelight.db.SqlDriver
import com.example.shopkmm01.presentation.CommonView

@Composable
internal fun ShopKmm01AppTheme(sqlDriver: SqlDriver, content: @Composable () -> Unit) {
    CommonView(sqlDriver = sqlDriver)
}
