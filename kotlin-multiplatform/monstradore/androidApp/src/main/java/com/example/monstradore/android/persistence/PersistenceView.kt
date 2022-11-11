package com.example.monstradore.android.persistence

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.monstradore.storage.*

@Composable
fun PersistenceContent(
    storage: UserStorage
) {
    var text by remember { mutableStateOf("") }
    var users by remember { mutableStateOf(storage.getUsers().toSet())}
    Column(
        modifier = Modifier.padding(10.dp)
    ) {
        Text(
            text = "Persistenz",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Text("Username eingeben")
        TextField(
            value = text,
            onValueChange = { text = it },
            label = { Text("Name") }
        )
        Row {
            Button(
                modifier = Modifier.padding(0.dp, 0.dp, 5.dp, 0.dp),
                onClick = {
                    users += text
                    storage.saveUsers(users.toList())
                }
            ) {
                Text("User speichern")
            }
            Button(
                onClick = { }
            ) {
                Text("User laden")
            }
        }
        users.forEach { user ->
            Text(user + "\n")
        }
    }
}