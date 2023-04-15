package com.example.shopkmm01.presentation.ui.theme


import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import com.example.shopkmm01.presentation.ui.theme.style.ShopKmm01AppColors.background
import com.example.shopkmm01.presentation.ui.theme.style.ShopKmm01AppColors.primary
import com.example.shopkmm01.presentation.ui.theme.style.ShopKmm01AppColors.primaryVariant
import com.example.shopkmm01.presentation.ui.theme.style.ShopKmm01AppColors.red_400
import com.example.shopkmm01.presentation.ui.theme.style.ShopKmm01AppColors.secondary

private val DarkColorPalette = darkColors(
    primary = primary,
    primaryVariant = primaryVariant,
    secondary = secondary,
    onError = red_400,
    background = background,
)

private val LightColorPalette = lightColors(
    primary = primary,
    primaryVariant = primaryVariant,
    secondary = secondary,
    onError = red_400,
    background = background,

)

@Composable
fun ShopKmm01Theme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}

