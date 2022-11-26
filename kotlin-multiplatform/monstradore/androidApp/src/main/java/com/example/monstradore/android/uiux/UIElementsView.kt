package com.example.monstradore.android.uiux

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.monstradore.android.R
import kotlinx.coroutines.launch

@Composable
fun UIElementsContent(navController: NavController) {
    Column(
        modifier = Modifier
            .padding(5.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = "Grundlegende Elemente",
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold
        )
        BasicElements()
        Text(
            text = "Elemente mit Statusverwaltung",
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold
        )
        StateElements()
        Text(
            text = "Plattformspezifische Elemente",
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold
        )
        SpecificNavigation(navController)
        Text(
            text = "Fortgeschrittene Elemente",
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold
        )
        ActionElements()
    }
}

@Composable
fun BasicElements() {
    Column {
        Text(
            text = "Text",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Text(text = "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua.")
        Text(
            text = "Bild",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Image(
            painter = painterResource(id = R.drawable.sample),
            contentDescription = stringResource(id = R.string.sample_img)
        )
        Text(
            text = "Liste",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Column {
            repeat(5) {
                Text(text = "Item: $it")
            }
        }
        Text(
            text = "Button",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Button(onClick = { }) {
            Text(text = "Button")
        }
        Text(
            text = "Icon Button",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        IconButton(onClick = { }) {
            Icon(Icons.Outlined.Home, contentDescription = "Icon Button description")
        }
    }
}

@Composable
fun StateElements() {
    var sliderValue by remember { mutableStateOf(0f) }
    var text by remember { mutableStateOf("") }
    val checkedState = remember { mutableStateOf(true) }
    Column {
        Text(
            text = "Slider",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Slider(value = sliderValue, onValueChange = { newValue ->
            sliderValue = newValue
        }
        )
        Text(
            text = "Textfeld",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        TextField(
            value = text,
            onValueChange = { text = it },
            label = { Text("Label") }
        )
        Text(
            text = "Switch / Toggle",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Switch(
            checked = checkedState.value,
            onCheckedChange = { checkedState.value = it }
        )
    }
}

@Composable
fun ActionElements() {
    Row(
        horizontalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        MenuElement()
        DialogElement()
        //BottomSheetElement()
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
        scaffoldState = scaffoldState,
        sheetPeekHeight = 0.dp
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
    Box {
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
fun SpecificNavigation(navController: NavController) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        Button(onClick = { navController.navigate("androidelements") }) {
            Text(text = "Android")
        }
        Button(onClick = { navController.navigate("ioselements") }) {
            Text(text = "iOS")
        }
    }
}