package com.example.monstradore.android.gps

import android.app.Activity
import android.content.Context
import android.location.Location
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.monstradore.android.requestLocationPermission

@Composable
fun GPSContent(context: Context, location: Location?) {
    val activity = LocalContext.current as Activity
    requestLocationPermission(context, activity)
    Column(
        modifier = Modifier.padding(10.dp)
    ) {
        Text(
            text = "Location",
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold
        )
        if (location != null) {
            Text("Latitude: ${location.latitude}")
            Text("Longitude: ${location.longitude}")
        } else {
            Text("Loading")
        }
    }
}