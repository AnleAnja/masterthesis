package com.example.monstradore.android.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign

@Composable
fun NavigationContent() {
    Scaffold(
        bottomBar = { BottomNavigationBar() }
    ) {
        TabBar()
    }
}

@Composable
fun TabBar() {
    var tabState by remember { mutableStateOf(0) }
    val titles = listOf("Tab 1", "Tab 2", "Tab 3")
    Column {
        TabRow(selectedTabIndex = tabState) {
            titles.forEachIndexed { index, title ->
                Tab(
                    text = { Text(title) },
                    selected = tabState == index,
                    onClick = { tabState = index }
                )
            }
        }
        TabContent(tabState)
    }
}

@Composable
fun BottomNavigationBar() {
    var bottomNavState by remember { mutableStateOf(0) }
    val titles = listOf("Item 1", "Item 2", "Item 3")

    BottomNavigation {
        titles.forEachIndexed { index, title ->
            BottomNavigationItem(
                icon = { Icon(Icons.Filled.Home, contentDescription = null) },
                label = { Text(title) },
                selected = bottomNavState == index,
                onClick = { bottomNavState = index }
            )
        }
    }
}

@Composable
fun TabContent(state: Int) {
    Text(
        text = "Tab ${state + 1} Content",
        textAlign = TextAlign.Center,
        modifier = Modifier.fillMaxWidth()
    )
}