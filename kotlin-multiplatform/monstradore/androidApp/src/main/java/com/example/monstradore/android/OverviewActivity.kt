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
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.monstradore.android.fileaccess.FileAccessContent
import com.example.monstradore.android.inputmethods.InputMethodsContent
import com.example.monstradore.android.navigation.NavigationContent
import com.example.monstradore.android.networkcall.NetworkCallContent
import com.example.monstradore.android.ui.theme.MonstradoreTheme
import com.example.monstradore.android.uiux.AndroidElementsContent
import com.example.monstradore.android.uiux.UIElementsContent
import com.example.monstradore.android.uiux.iOSElementsContent
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
    Scaffold {
        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = "categories") {
            composable("categories") { CategoryList(categories, navController) }
            composable("uielements") { UIElementsContent(navController) }
            composable("androidelements") { AndroidElementsContent() }
            composable("ioselements") { iOSElementsContent() }
            composable("navigation") { NavigationContent() }
            composable("inputmethods") { InputMethodsContent() }
            composable("networkcalls") { NetworkCallContent() }
            composable("fileaccess") { FileAccessContent() }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterialApi::class)
@Composable
fun CategoryList(categories: List<Category>, navController: NavController) {
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
                ListItem(modifier = Modifier.clickable(onClick = {
                    when(feature) {
                        "Reichhaltige UI Elemente" -> navController.navigate("uielements")
                        "Navigation" -> navController.navigate("navigation")
                        "Eingabemethoden" -> navController.navigate("inputmethods")
                        "Netzwerkcalls" -> navController.navigate("networkcalls")
                        "Dateizugriff + Persistierung" -> navController.navigate("fileaccess")
                    }
                })) {
                    Text(
                        text = feature,
                        fontSize = 20.sp
                    )
                }
            }
        }
    }
}