package com.example.monstradore.android.interactiondesign

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp

@Composable
fun InteractionDesignContent() {
    var listElements = mutableListOf<String>()
    var showMenu by remember { mutableStateOf(false) }
    repeat(25) {
        listElements.add("Dieses Element hat den Index $it.")
    }
    LazyColumn(
        modifier = Modifier.pointerInput(Unit) {
            detectTapGestures(
                onLongPress = { showMenu = !showMenu }
            )
        }
    ) {
            items(listElements) { element ->
                Box {
                    Box {
                        Text(
                            element,
                            modifier = Modifier.padding(10.dp)
                        )
                        Divider()
                    }
                    DropdownMenu(
                        expanded = showMenu,
                        onDismissRequest = { showMenu = false }) {
                        DropdownMenuItem(onClick = { listElements.remove(element) }) {
                            Text("Löschen")
                        }
                    }
                }
            }
    }
}