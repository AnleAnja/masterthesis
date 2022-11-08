package com.example.monstradore.android.networkcall

import androidx.compose.runtime.*
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
}