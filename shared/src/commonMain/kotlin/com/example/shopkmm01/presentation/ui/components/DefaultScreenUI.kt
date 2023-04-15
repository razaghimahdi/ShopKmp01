package com.example.shopkmm01.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import com.example.shopkmm01.business.core.ProgressBarState
import com.example.shopkmm01.business.core.Queue
import com.example.shopkmm01.business.core.UIComponent

@Composable
fun DefaultScreenUI(
    queue: Queue<UIComponent> = Queue(mutableListOf()),
    onRemoveHeadFromQueue: () -> Unit,
    progressBarState: ProgressBarState = ProgressBarState.Idle,
    content: @Composable () -> Unit,
) {

        val scaffoldState = rememberScaffoldState()
        Scaffold(
            scaffoldState = scaffoldState
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colors.background),
                contentAlignment = Alignment.Center
            ) {
                content()
                // process the queue
                if (!queue.isEmpty()) {
                    queue.peek()?.let { uiComponent ->
                        if (uiComponent is UIComponent.Dialog) {

                            GenericDialog(
                                modifier = Modifier
                                    .fillMaxWidth(0.9f),
                                title = uiComponent.title,
                                description = uiComponent.description,
                                onRemoveHeadFromQueue = onRemoveHeadFromQueue
                            )

                        }
                    }
                }

                if (progressBarState is ProgressBarState.Loading) {
                    CircularIndeterminateProgressBar()
                }
            }
        }
    }











