package com.example.monstradore.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.monstradore.android.ui.theme.MonstradoreTheme
import com.example.monstradore.structures.Category
import com.example.monstradore.structures.Features

class OverviewActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MonstradoreTheme {
                Content()
            }
        }
    }
}

@Composable
fun Content() {
    val categories = Features.overview
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "monstradore") }
            )
        }
    ) { CategoryList(categories) }
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterialApi::class)
@Composable
fun CategoryList(categories: List<Category>) {
    val headerBackground = if (isSystemInDarkTheme()) Color.Black else Color.White
    val headerForeground = if (isSystemInDarkTheme()) Color.White else Color.Black
    LazyColumn(modifier = Modifier.fillMaxWidth()) {
        categories.forEach { (name, features) ->
            stickyHeader {
                ListItem(modifier = Modifier
                    .fillMaxWidth()
                    .background(headerBackground)
                ) {
                    Text(
                        text = name,
                        fontSize = 23.sp,
                        fontWeight = FontWeight.Bold,
                        color = headerForeground
                    )
                }
            }
            items(features) { feature ->
                ListItem(modifier = Modifier.clickable(onClick = { })) {
                    Text(
                        text = feature,
                        fontSize = 20.sp
                    )
                }
            }
        }
    }
}