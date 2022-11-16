package com.example.monstradore.android.acceleration

import android.content.Context
import android.content.Context.SENSOR_SERVICE
import android.hardware.Sensor
import android.hardware.SensorManager
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.getSystemService

@Composable
fun AccelerationContent() {
    val sensorManager = LocalContext.current.getSystemService(SENSOR_SERVICE) as SensorManager
    val sensor: Sensor? = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
    var sensorState by remember { mutableStateOf(sensor) }
    Column(
        modifier = Modifier.padding(10.dp)
    ) {
        Text(
            text = "Beschleunigung",
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold
        )
        /*Button(onClick = {
            sensorState = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        }) {
            Text("Aktualisieren")
        }*/
        Text(sensorState.toString())
    }
}