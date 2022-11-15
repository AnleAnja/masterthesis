package com.example.monstradore.android.inputmethods

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun InputMethodsContent() {
    Column(
        modifier = Modifier.padding(10.dp)
    ) {
        Text(
            text = "Texteingabe",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        TextInput()
        Text(
            text = "Texteingabe",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        OutlinedTextInput()
        Text(
            text = "Passworteingabe",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        PasswordTextInput()
    }
}

@Composable
fun TextInput() {
    var text by remember { mutableStateOf("") }

    TextField(
        value = text,
        onValueChange = { text = it },
        label = { Text("Label") }
    )
}

@Composable
fun OutlinedTextInput() {
    var text by remember { mutableStateOf("") }
    OutlinedTextField(
        value = text,
        onValueChange = { text = it },
        label = { Text("Outlined Label") }
    )
}

@Composable
fun PasswordTextInput() {
    var text by remember { mutableStateOf("") }

    TextField(
        value = text,
        onValueChange = { text = it },
        label = { Text("Password Label") },
        visualTransformation = PasswordVisualTransformation()
    )
}