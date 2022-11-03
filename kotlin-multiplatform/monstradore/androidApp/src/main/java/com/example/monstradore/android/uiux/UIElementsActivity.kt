package com.example.monstradore.android.uiux

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.monstradore.android.R
import com.example.monstradore.android.uiux.ui.theme.MonstradoreTheme
import kotlinx.coroutines.launch

class UIElementsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MonstradoreTheme {
                Column(
                    modifier = Modifier
                        .padding(5.dp)
                        .verticalScroll(rememberScrollState())
                ) {
                    Text(
                        text = "Grundlegende Elemente",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                    BasicElements()
                    Text(
                        text = "Elemente mit Statusverwaltung",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                    StateElements()
                    Text(
                        text = "Fortgeschrittene Elemente",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                    AdvancedElements()
                }
            }
        }
    }
}

@Composable
fun BasicElements() {
    Column(Modifier.verticalScroll(rememberScrollState())) {
        Text(text = "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua.")
        Image(
            painter = painterResource(id = R.drawable.sample),
            contentDescription = stringResource(id = R.string.sample_img)
        )
        LazyColumn {
            items(5) { index ->
                Text(text = "Item: $index")
            }
        }
        Button(onClick = { }) {
            Text(text = "Button")
        }
    }
}

@Composable
fun StateElements() {
    var sliderValue by remember { mutableStateOf(0f) }
    var text by remember { mutableStateOf("") }
    val checkedState = remember { mutableStateOf(true) }
    Column {
        Slider(value = sliderValue, onValueChange = { newValue ->
            sliderValue = newValue
        }
        )
        TextField(
            value = text,
            onValueChange = { text = it },
            label = { Text("Label") }
        )
        Switch(
            checked = checkedState.value,
            onCheckedChange = { checkedState.value = it }
        )
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AdvancedElements() {
    Column {
        val scope = rememberCoroutineScope()
        val scaffoldState = rememberBottomSheetScaffoldState(
            bottomSheetState = BottomSheetState(BottomSheetValue.Collapsed)
        )
        var showMenu by remember { mutableStateOf(false) }
        val openDialog = remember { mutableStateOf(false) }
        BottomSheetScaffold(
            sheetContent = {
                Box(
                    Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                        .background(Color.LightGray),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Text in Bottom Sheet")
                }
            },
            scaffoldState = scaffoldState
        ) {
            Button(onClick = {
                scope.launch {
                    if (scaffoldState.bottomSheetState.isCollapsed) {
                        scaffoldState.bottomSheetState.expand()
                    } else {
                        scaffoldState.bottomSheetState.collapse()
                    }
                }
            }) {
                Text(text = "Bottom Sheet")
            }
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Button(onClick = { showMenu = !showMenu }) {
                Text(text = "Menu")
            }
            DropdownMenu(
                expanded = showMenu,
                onDismissRequest = { showMenu = false }) {
                DropdownMenuItem(onClick = { }) {
                    Text(text = "Menu Item")
                }
            }
        }
        Button(onClick = {
            openDialog.value = true
        }) {
            Text("Dialog")
        }
        if (openDialog.value) {
            AlertDialog(
                onDismissRequest = {
                    openDialog.value = false
                },
                title = {
                    Text(text = "Dialog Titel")
                },
                text = {
                    Text("Dialog Text")
                },
                confirmButton = {
                    TextButton(
                        onClick = {
                            openDialog.value = false
                        }) {
                        Text("OK")
                    }

                },
                dismissButton = {
                    Button(

                        onClick = {
                            openDialog.value = false
                        }) {
                        Text("Cancel")
                    }
                }
            )
        }
    }
}