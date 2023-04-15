package com.example.shopkmm01.presentation.screen.main

import com.example.shopkmm01.business.core.ProgressBarState
import com.example.shopkmm01.business.core.Queue
import com.example.shopkmm01.business.core.UIComponent
import com.example.shopkmm01.business.domain.main.Category
import com.example.shopkmm01.business.domain.main.Product

data class MainState(
    val progressBarState: ProgressBarState = ProgressBarState.Idle,
    val products: List<Product> = listOf(),
    val categories: List<Category> = listOf(),
    val selectedCategory: Category = Category(""),
    val errorQueue: Queue<UIComponent> = Queue(mutableListOf()),
)
