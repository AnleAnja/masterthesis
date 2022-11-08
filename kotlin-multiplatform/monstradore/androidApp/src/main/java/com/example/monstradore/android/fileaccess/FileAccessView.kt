package com.example.monstradore.android.fileaccess

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import java.io.File

@Composable
fun FileAccessContent() {
    val context = LocalContext.current
    Button(
        modifier = Modifier
            .padding(20.dp,10.dp,20.dp,10.dp),
        shape = RoundedCornerShape(100.dp),
        onClick = {
            File(context.filesDir, "samplefile") // unresolved reference to context als
        }
    ) {
        Text("Datei speichern")
    }
}