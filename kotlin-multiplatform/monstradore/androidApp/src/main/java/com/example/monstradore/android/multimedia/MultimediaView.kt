package com.example.monstradore.android.multimedia

import android.media.MediaPlayer
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.PlayArrow
import androidx.compose.material.icons.outlined.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.monstradore.android.R

@Composable
fun MultimediaContent() {
    Column(
        modifier = Modifier.padding(10.dp)
    ) {
        Text(
            text = "Audio",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        AudioContent()
        Text(
            text = "Video",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        VideoContent()
    }
}

@Composable
fun AudioContent() {
    val context = LocalContext.current
    val mediaPlayer = MediaPlayer.create(
        context,
        R.raw.sample
    )
    Row {
        IconButton(onClick = { mediaPlayer.start() }) {
            Icon(Icons.Outlined.PlayArrow, contentDescription = "")
        }
        IconButton(onClick = { mediaPlayer.pause() }) {
            Icon(Icons.Outlined.Star, contentDescription = "")
        }
    }
}

@Composable
fun VideoContent() {
    val context = LocalContext.current
    val mediaPlayer = MediaPlayer.create(
        context,

    )
    Column(modifier = Modifier.fillMaxSize()) {
        // video player
        VideoPlayer(
            modifier =
            Modifier.fillMaxWidth()
                .weight(1f, fill = true)
                .background(Color.Black)
        )

        // video playlist
        VideoPlayList(
            Modifier.fillMaxWidth()
                .weight(1f, fill = true)
                .background(Color.Gray)
        )
    }
}