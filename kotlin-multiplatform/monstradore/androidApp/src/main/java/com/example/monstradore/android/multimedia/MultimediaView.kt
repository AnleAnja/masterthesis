package com.example.monstradore.android.multimedia

import android.media.MediaPlayer
import android.net.Uri
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.PlayArrow
import androidx.compose.material.icons.outlined.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.monstradore.android.R
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.ui.StyledPlayerView
import com.google.android.exoplayer2.upstream.DefaultDataSource


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
        R.raw.sampleaudio
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
    val url = "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"
    val exoPlayer = ExoPlayer.Builder(context).build()
    val source = MediaItem.fromUri(Uri.parse(url))
    exoPlayer.setMediaItem(source)
    val playerView = StyledPlayerView(context)
    playerView.player = exoPlayer
    DisposableEffect(AndroidView(factory = {playerView})) {
        exoPlayer.prepare()
        exoPlayer.playWhenReady = true
        onDispose {
            exoPlayer.release()
        }
    }
}