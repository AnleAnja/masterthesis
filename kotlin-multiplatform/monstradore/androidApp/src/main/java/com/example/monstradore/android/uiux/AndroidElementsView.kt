package com.example.monstradore.android.uiux

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountBox
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material3.AssistChip
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AndroidElementsContent() {
    Column(
        modifier = Modifier
            .padding(5.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = "",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )
        val scaffoldState = rememberScaffoldState()
        val scope = rememberCoroutineScope()
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Top App Bar") }
                )
            },
            bottomBar = {
                BottomAppBar { }
            },
            drawerContent = {
                Text(
                    text = "Navigation Drawer Content",
                    modifier = Modifier.padding(20.dp),
                    textAlign = TextAlign.Center
                )
            },
            scaffoldState = scaffoldState,
            floatingActionButton = {
                FloatingActionButton(onClick = { }) {
                    Text(
                        text = "Floating Action Button",
                        modifier = Modifier.padding(5.dp)
                    )
                }
            }
        ) {
            Column(
                modifier = Modifier
                    .padding(5.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                Text(
                    text = "Card",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Card(
                    modifier = Modifier
                        //.fillMaxWidth()
                        .padding(15.dp),
                    elevation = 10.dp
                ) {
                    Text(
                        "Card Content",
                        textAlign = TextAlign.Center
                    )
                }
                Text(
                    text = "Chip",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                /*AssistChip(
                    onClick = { },
                    label = { Text("Chip Content") }
                )*/
                Text(
                    text = "Navigation Rail",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                NavigationRail {
                    NavigationRailItem(
                        icon = {
                            Icon(
                                imageVector = Icons.Rounded.Home,
                                contentDescription = "1. Button"
                            )
                        },
                        onClick = {},
                        selected = false
                    )
                    NavigationRailItem(
                        icon = {
                            Icon(
                                imageVector = Icons.Rounded.Add,
                                contentDescription = "2. Button"
                            )
                        },
                        onClick = {},
                        selected = false
                    )
                    NavigationRailItem(
                        icon = {
                            Icon(
                                imageVector = Icons.Rounded.AccountBox,
                                contentDescription = "3. Button"
                            )
                        },
                        onClick = {},
                        selected = false
                    )
                }
                Text(
                    text = "Snackbar",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Button(onClick = {
                    scope.launch {
                        scaffoldState.snackbarHostState
                            .showSnackbar("Snackbar Content")
                    }
                }) {
                    Text("Snackbar")
                }
                Text(
                    text = "Time Picker",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    "Nicht nativ verfügbar",
                    color = Color.Red
                )
                Text(
                    text = "Date Picker",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    "Nicht nativ verfügbar",
                    color = Color.Red
                )
                Text(
                    text = "Radio Buttons",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                var state by remember { mutableStateOf(true) }
                Row(Modifier.selectableGroup()) {
                    RadioButton(
                        selected = state,
                        onClick = { state = true }
                    )
                    RadioButton(
                        selected = !state,
                        onClick = { state = false }
                    )
                }
                Text(
                    text = "Checkbox",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                val checkedState = remember { mutableStateOf(true) }
                Checkbox(
                    checked = checkedState.value,
                    onCheckedChange = { checkedState.value = it }
                )
            }
        }
    }
}