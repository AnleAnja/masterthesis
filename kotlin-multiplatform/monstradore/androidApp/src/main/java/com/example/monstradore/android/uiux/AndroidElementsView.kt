package com.example.monstradore.android.uiux

import androidx.compose.material.BottomAppBar
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable

@Composable
fun AppBars() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Top App Bar") }
            )
        },
        bottomBar = {
            BottomAppBar{ }
        }
    ) {

    }
}