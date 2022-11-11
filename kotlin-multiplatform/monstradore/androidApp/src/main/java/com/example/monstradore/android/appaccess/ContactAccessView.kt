package com.example.monstradore.android.appaccess

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.provider.ContactsContract
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat.startActivityForResult
import com.example.monstradore.android.hasContactPermission
import com.example.monstradore.android.requestContactPermission

@Composable
fun ContactPickerContent(
    context: Context,
    contactName: String,
    contactNumber: String,
) {
    val activity = LocalContext.current as Activity
    Column(
        modifier = Modifier.padding(10.dp)
    ) {
        Text(
            text = "Zugriff auf Kontakte Anwendung",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Button(
            onClick = {
                if (hasContactPermission(context)) {
                    val intent = Intent(Intent.ACTION_GET_CONTENT)
                    intent.type = ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE
                    startActivityForResult(activity, intent, 1, null)
                } else {
                    requestContactPermission(context, activity)
                }
            }) {
            Text("Kontakt auswählen")
        }
        Text(
            text = contactName,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = contactNumber,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
    }
}