package com.example.monstradore.android.animations

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private enum class BoxState {
    Small,
    Large
}

@Composable
fun AnimationsContent() {
    Column(
        modifier = Modifier.padding(5.dp)
    ) {
        Text(
            text = "Animationen",
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold
        )
        AnimatedTransition()
        Spacer(modifier = Modifier.height(10.dp))
        AnimatedVisible()
    }
}

@Composable
fun AnimatedTransition() {
    var boxState by remember { mutableStateOf(BoxState.Small) }
    val transition = updateTransition(
        targetState = boxState, label = ""
    )
    val size by transition.animateDp(label = "") { state ->
        when (state) {
            BoxState.Small -> 64.dp
            BoxState.Large -> 128.dp
        }
    }
    Column {
        Button(
            onClick = {
                boxState = when (boxState) {
                    BoxState.Small -> BoxState.Large
                    BoxState.Large -> BoxState.Small
                }
            }
        ) {
            Text(text = "Transition")
        }
        Box(
            modifier = Modifier
                .size(size)
                .background(Color.LightGray)
        )
    }
}

@Composable
fun AnimatedVisible() {
    var visible by remember { mutableStateOf(true) }
    Column {
        Button(
            onClick = { visible = !visible }
        ) {
            Text(text = if (visible) "Hide" else "Show")
        }
        AnimatedVisibility(visible) {
            Box(
                modifier = Modifier
                    .size(128.dp)
                    .background(Color.LightGray)
            )
        }
    }
}
