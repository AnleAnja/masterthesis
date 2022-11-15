package com.example.monstradore.android.gestures

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage
import com.example.monstradore.android.R

@Composable
fun GesturesContent() {
    ImagePreview(model = R.drawable.sample, "")
}

@Composable
fun ImagePreview(model: Any, contentDescription: String? = null) {
    var zoom by remember { mutableStateOf(1f) }
    Box(modifier = Modifier
        .fillMaxSize()
        .pointerInput(Unit) {
            detectTapGestures(
                onDoubleTap = {
                    zoom = if(zoom == 1f) 4f
                    else 1f
                }
            )
        }
    ) {
        AsyncImage(
            model,
            contentDescription = contentDescription,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .graphicsLayer(
                    scaleX = zoom,
                    scaleY = zoom
                )
                .pointerInput(Unit) {
                    detectTransformGestures(
                        onGesture = { _, _, gestureZoom, _ ->
                            zoom = (zoom * gestureZoom).coerceIn(1F..4F)
                        }
                    )
                }
                .fillMaxSize()
        )
    }
}