package com.example.monstradore.android.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.example.monstradore.structures.Navigation.tabTitles
import com.example.monstradore.structures.Navigation.itemTitles

@Composable
fun NavigationContent() {
    var tabState by remember { mutableStateOf(0) }
    var bottomNavState by remember { mutableStateOf(0) }
    Scaffold(
        bottomBar = { BottomNavigationBar(bottomNavState) { index -> bottomNavState = index } }
    ) {
        TabBar(tabState, bottomNavState) { index -> tabState = index }
    }
}

@Composable
fun TabBar(tabState: Int, itemState: Int, updateTabState: (Int) -> Unit) {
    val titles = tabTitles
    Column {
        TabRow(selectedTabIndex = tabState) {
            titles.forEachIndexed { index, title ->
                Tab(
                    text = { Text(title) },
                    selected = tabState == index,
                    onClick = { updateTabState(index) }
                )
            }
        }
        TabContent(tabState, itemState)
    }
}

@Composable
fun BottomNavigationBar(itemState: Int, updateItemState: (Int) -> Unit) {
    val titles = itemTitles

    BottomNavigation {
        titles.forEachIndexed { index, title ->
            BottomNavigationItem(
                icon = { Icon(Icons.Filled.Home, contentDescription = null) },
                label = { Text(title) },
                selected = itemState == index,
                onClick = { updateItemState(index) }
            )
        }
    }
}

@Composable
fun TabContent(tabState: Int, itemState: Int) {
    Text(
        text = "Tab ${tabState + 1} Content",
        textAlign = TextAlign.Center,
        modifier = Modifier.fillMaxWidth()
    )
    Text(
        text = "Item ${itemState + 1} Content",
        textAlign = TextAlign.Center,
        modifier = Modifier.fillMaxWidth()
    )
}