package com.example.monstradore.android

import android.Manifest.permission.READ_CONTACTS
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.provider.ContactsContract
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.monstradore.android.fileaccess.FileAccessContent
import com.example.monstradore.android.inputmethods.InputMethodsContent
import com.example.monstradore.android.navigation.NavigationContent
import com.example.monstradore.android.networkcall.NetworkCallContent
import com.example.monstradore.android.persistence.PersistenceContent
import com.example.monstradore.android.ui.theme.MonstradoreTheme
import com.example.monstradore.android.uiux.AndroidElementsContent
import com.example.monstradore.android.uiux.UIElementsContent
import com.example.monstradore.android.uiux.iOSElementsContent
import com.example.monstradore.storage.UserStorage
import com.example.monstradore.structures.Category
import com.example.monstradore.structures.Features

class MainActivity : AppCompatActivity() {
    var contactName by mutableStateOf("")
    var contactNumber by mutableStateOf("")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val storage = UserStorage(this)
        setContent {
            MonstradoreTheme {
                Content(storage, contactName, contactNumber)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        // on below line we are checking if result code is ok or not.
        if (resultCode != Activity.RESULT_OK) return

        // on below line we are checking if data is not null.
        if (requestCode == 1 && data != null) {
            // on below lin we are getting contact data
            val contactData: Uri? = data.data

            // on below line we are creating a cursor
            val cursor: Cursor = managedQuery(contactData, null, null, null, null)

            // on below line we are moving cursor.
            cursor.moveToFirst()

            // on below line we are getting our
            // number and name from cursor
            val number: String =
                cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.NUMBER))
            val name: String =
                cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME))

            // on the below line we are setting values.
            contactName = name
            contactNumber = number
        }

    }
}

@Composable
fun Content(
    storage: UserStorage,
    contactName: String,
    contactNumber: String
) {
    val categories = Features.overview
    Scaffold {
        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = "categories") {
            composable("categories") { CategoryList(categories, navController) }
            composable("uielements") { UIElementsContent(navController) }
            composable("androidelements") { AndroidElementsContent() }
            composable("ioselements") { iOSElementsContent() }
            composable("navigation") { NavigationContent() }
            composable("inputmethods") { InputMethodsContent() }
            composable("networkcalls") { NetworkCallContent() }
            composable("fileaccess") { FileAccessContent() }
            composable("persistence") { PersistenceContent(storage) }
            composable("contactaccess") { ContactPicker(
                context = LocalContext.current, contactName, contactNumber
            ) }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterialApi::class)
@Composable
fun CategoryList(categories: List<Category>, navController: NavController) {
    val headerBackground = if (isSystemInDarkTheme()) Color.Black else Color.White
    val headerForeground = if (isSystemInDarkTheme()) Color.White else Color.Black
    LazyColumn(modifier = Modifier.fillMaxWidth()) {
        categories.forEach { (name, features) ->
            stickyHeader {
                ListItem(modifier = Modifier
                    .fillMaxWidth()
                    .background(headerBackground)
                ) {
                    Text(
                        text = name,
                        fontSize = 23.sp,
                        fontWeight = FontWeight.Bold,
                        color = headerForeground
                    )
                }
            }
            items(features) { feature ->
                ListItem(modifier = Modifier.clickable(onClick = {
                    when(feature) {
                        "Reichhaltige UI Elemente" -> navController.navigate("uielements")
                        "Navigation" -> navController.navigate("navigation")
                        "Eingabemethoden" -> navController.navigate("inputmethods")
                        "Netzwerkcalls" -> navController.navigate("networkcalls")
                        "Dateizugriff" -> navController.navigate("fileaccess")
                        "Persistierung" -> navController.navigate("persistence")
                        "Zugriff auf native Anwendungen" -> navController.navigate("contactaccess")
                    }
                })) {
                    Text(
                        text = feature,
                        fontSize = 20.sp
                    )
                }
            }
        }
    }
}

@Composable
fun ContactPicker(
    context: Context,
    contactName: String,
    contactNumber: String,
) {
    // on below line we are creating variable for activity.
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
}

fun hasContactPermission(context: Context): Boolean {
    // on below line checking if permission is present or not.
    return ContextCompat.checkSelfPermission(context, READ_CONTACTS) ==
            PackageManager.PERMISSION_GRANTED
}

fun requestContactPermission(context: Context, activity: Activity) {
    // on below line if permission is not granted requesting permissions.
    if (!hasContactPermission(context)) {
        ActivityCompat.requestPermissions(activity, arrayOf(READ_CONTACTS), 1)
    }
}