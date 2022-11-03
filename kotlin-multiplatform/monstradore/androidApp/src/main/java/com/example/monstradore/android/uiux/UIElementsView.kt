package com.example.monstradore.android.uiux

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.monstradore.android.R
import kotlinx.coroutines.launch

@Composable
fun UIElementsContent() {
    Column(
        modifier = Modifier
            .padding(5.dp)
        //.verticalScroll(rememberScrollState())
    ) {
        Text(
            text = "Reichhaltige UI Elemente",
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold
        )
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
        ActionElements()
        Text(
            text = "Plattformspezifische Elemente",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
        SpecificNavigation()
    }
}

@Composable
fun BasicElements() {
    Column {
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

@Composable
fun ActionElements() {
    Column {
        BottomSheetElement()
        MenuElement()
        DialogElement()
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BottomSheetElement() {
    val scope = rememberCoroutineScope()
    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = BottomSheetState(BottomSheetValue.Collapsed)
    )
    BottomSheetScaffold(
        sheetContent = {
            Box(
                Modifier
                    .fillMaxWidth()
                    .height(500.dp),
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
}

@Composable
fun MenuElement() {
    var showMenu by remember { mutableStateOf(false) }
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
}

@Composable
fun DialogElement() {
    Column {
        val openDialog = remember { mutableStateOf(false) }
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
                    TextButton(
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

@Composable
fun SpecificNavigation() {
    Row {
        Button(onClick = { }) {
            Text(text = "Android")
        }
        Button(onClick = { }) {
            Text(text = "iOS")
        }
    }
}