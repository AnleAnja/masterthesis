package com.example.monstradore.android.appaccess

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.provider.ContactsContract
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat.startActivityForResult

/*
@Composable
fun ContactAccessContent(
    context: Context,
    contactName: String,
    contactNumber: String
) {
    val activity = LocalContext.current as Activity

    // on below line we are creating a column,
    Column(
        // on below line we are adding a modifier to it,
        modifier = Modifier
            .fillMaxSize()
            // on below line we are adding a padding.
            .padding(all = 30.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        // on below line we are adding a text for heading.
        Text(
            // on below line we are specifying text
            text = "Contact in Android",
            // on below line we are specifying
            // text color, font size and font weight
            fontSize = 20.sp, fontWeight = FontWeight.Bold
        )

        // on below line adding a spacer
        Spacer(modifier = Modifier.height(20.dp))

        // on below line creating a text for contact name
        Text(text = contactName, fontSize = 18.sp, fontWeight = FontWeight.Bold)

        // on below line adding a spacer
        Spacer(modifier = Modifier.height(20.dp))

        // on below line creating a text for contact number.
        Text(text = contactNumber, fontSize = 18.sp, fontWeight = FontWeight.Bold)

        // on below line adding a spacer
        Spacer(modifier = Modifier.height(20.dp))

        // on below line creating a button to pick contact.
        Button(
            // on below line adding on click for button.
            onClick = {
                // on below line checking if permission is granted.
                if (hasContactPermission(context)) {
                    // if permission granted open intent to pick contact/
                    val intent = Intent(Intent.ACTION_GET_CONTENT)
                    intent.type = ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE
                    startActivityForResult(activity, intent, 1, null)
                } else {
                    // if permission not granted requesting permission .
                    requestContactPermission(context, activity)
                }
            },
            // adding padding to button.
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            // displaying text in our button.
            Text(text = "Pick Contact")

        }
    }
}*/
