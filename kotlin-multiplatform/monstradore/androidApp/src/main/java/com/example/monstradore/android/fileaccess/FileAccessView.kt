package com.example.monstradore.android.fileaccess

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.json.JSONObject
import java.io.File

@Composable
fun FileAccessContent() {
    val context = LocalContext.current
    var text by remember { mutableStateOf("") }
    var filename = ""
    var filecontent by remember { mutableStateOf("") }
    Column(
        modifier = Modifier.padding(10.dp)
    ) {
        Text(
            text = "Dateizugriff",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Text("Text in Datei schreiben")
        TextField(
            value = text,
            onValueChange = { text = it },
            label = { Text("Text") }
        )
        Row {
            Button(
                modifier = Modifier.padding(0.dp, 0.dp, 5.dp, 0.dp),
                onClick = {
                    val jsonObject = JSONObject()
                    jsonObject.put("content",text)
                    val samplefile = File(context.filesDir.path,"samplefile.txt")
                    samplefile.writeText(text)
                    filename = samplefile.absolutePath
                }
            ) {
                Text("Datei speichern")
            }
            Button(
                onClick = {
                    filecontent = File(filename).readText()
                }
            ) {
                Text("Datei laden")
            }
        }
        Text(filecontent)
    }
}