package com.example.monstradore.android.networkcall

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.monstradore.structures.NetworkApi
import kotlinx.coroutines.launch

@Composable
fun NetworkCallContent() {
    val scope = rememberCoroutineScope()
    var text by remember { mutableStateOf("Loading") }
    LaunchedEffect(true) {
        scope.launch {
            text = try {
                NetworkApi().getResponse()
            } catch (e: Exception) {
                e.localizedMessage ?: "error"
            }
        }
    }
    Column(
        modifier = Modifier.padding(5.dp)
    ) {
        Text(
            text = "Netzwerkcall",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Text(text)
    }
}