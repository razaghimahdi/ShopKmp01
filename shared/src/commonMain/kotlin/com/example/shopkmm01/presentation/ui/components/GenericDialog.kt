package com.example.shopkmm01.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection


@Composable
fun GenericDialog(
    modifier: Modifier = Modifier.fillMaxSize().background(MaterialTheme.colors.background),
    title: String,
    description: String? = null,
    onRemoveHeadFromQueue: () -> Unit,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = title
        )

        if (description != null) {
            Text(text = description)
        }


        Button(onClick = onRemoveHeadFromQueue){
            Text("Dismiss")
        }

    }

}