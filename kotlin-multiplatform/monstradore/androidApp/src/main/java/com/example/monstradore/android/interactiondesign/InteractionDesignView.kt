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
import com.example.monstradore.structures.generateInteractionDesignList

@Composable
fun InteractionDesignContent() {
    val listElements = remember { mutableStateListOf<String>() }
    listElements.addAll(generateInteractionDesignList())
    var showMenuIndex by remember { mutableStateOf(-1) }
    LazyColumn {
            items(listElements) { element ->
                Box(
                    modifier = Modifier.pointerInput(Unit) {
                        detectTapGestures(
                            onLongPress = { showMenuIndex = listElements.indexOf(element)+1 }
                        )
                    }
                ) {
                    Box {
                        Text(
                            element,
                            modifier = Modifier.padding(10.dp)
                        )
                        Divider()
                    }
                    DropdownMenu(
                        expanded = (listElements.indexOf(element)+1  == showMenuIndex),
                        onDismissRequest = { showMenuIndex = -1 }) {
                        DropdownMenuItem(onClick = {
                            listElements.remove(element)
                            showMenuIndex = -1
                        }) {
                            Text("Löschen")
                        }
                    }
                }
            }
    }
}