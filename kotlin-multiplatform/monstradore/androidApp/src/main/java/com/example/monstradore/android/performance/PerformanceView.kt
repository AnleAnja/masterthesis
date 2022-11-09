package com.example.monstradore.android.performance

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material3.Button
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.monstradore.structures.prime
import kotlinx.coroutines.*

@OptIn(DelicateCoroutinesApi::class)
@Composable
fun PerformanceContent() {

    var input by remember { mutableStateOf("20000") }
    var result by remember { mutableStateOf(0) }
    var time by remember { mutableStateOf(0L) }
    Column (
        modifier = Modifier.padding(10.dp)
    ) {
        Text(
            text = "Performance",
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold
        )
        Text(text = "Die wievielte Primzahl soll berechnet werden?")
            TextField(
                value = input,
                onValueChange = { input = it },
                label = { Text("Zahl eingeben") }
            )
            Button(onClick = {
                GlobalScope.launch(Dispatchers.IO) {
                    val starttime = System.currentTimeMillis()
                    val primeResult = prime(input.toInt())
                    val endtime = System.currentTimeMillis()
                    time = (endtime - starttime) / 1000
                    withContext(Dispatchers.Main) {
                        result = primeResult
                    }
                }
            }) {
                Text("Berechnen")
        }
        Text("Ergebnis: $result")
        Text("Benötigte Zeit: $time Sekunden")
    }
}