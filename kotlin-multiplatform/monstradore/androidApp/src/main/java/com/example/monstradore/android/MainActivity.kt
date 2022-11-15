package com.example.monstradore.android

import android.Manifest
import android.Manifest.permission.READ_CONTACTS
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.monstradore.android.animations.AnimationsContent
import com.example.monstradore.android.appaccess.ContactPickerContent
import com.example.monstradore.android.fileaccess.FileAccessContent
import com.example.monstradore.android.gestures.GesturesContent
import com.example.monstradore.android.hardwarefunctions.CameraContent
import com.example.monstradore.android.inputmethods.InputMethodsContent
import com.example.monstradore.android.interactiondesign.InteractionDesignContent
import com.example.monstradore.android.multimedia.MultimediaContent
import com.example.monstradore.android.navigation.NavigationContent
import com.example.monstradore.android.networkcall.NetworkCallContent
import com.example.monstradore.android.performance.PerformanceContent
import com.example.monstradore.android.persistence.PersistenceContent
import com.example.monstradore.android.ui.theme.MonstradoreTheme
import com.example.monstradore.android.uiux.AndroidElementsContent
import com.example.monstradore.android.uiux.UIElementsContent
import com.example.monstradore.android.uiux.iOSElementsContent
import com.example.monstradore.storage.UserStorage
import com.example.monstradore.structures.Category
import com.example.monstradore.structures.Features
import java.io.File
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class MainActivity : AppCompatActivity() {
    var contactName by mutableStateOf("")
    var contactNumber by mutableStateOf("")

    private lateinit var outputDirectory: File
    private lateinit var cameraExecutor: ExecutorService
    private var shouldShowCamera: MutableState<Boolean> = mutableStateOf(false)
    private var shouldShowPhoto: MutableState<Boolean> = mutableStateOf(false)

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        shouldShowCamera.value = isGranted
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val storage = UserStorage(this)
        setContent {
            MonstradoreTheme {
                Content(
                    outputDirectory,
                    cameraExecutor,
                    ::handleImageCapture,
                    shouldShowCamera,
                    shouldShowPhoto,
                    storage,
                    contactName,
                    contactNumber
                )
            }
        }
        requestCameraPermission()
        outputDirectory = getOutputDirectory()
        cameraExecutor = Executors.newSingleThreadExecutor()
    }

    private fun requestCameraPermission() {
        when {
            ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED -> {
                shouldShowCamera.value = true
                Log.d("anja", "Permission granted")
            }

            ActivityCompat.shouldShowRequestPermissionRationale(
                this,
                Manifest.permission.CAMERA
            ) -> requestPermissionLauncher.launch(Manifest.permission.CAMERA)

            else -> requestPermissionLauncher.launch(Manifest.permission.CAMERA)
        }
    }

    private fun handleImageCapture(uri: Uri) {
        shouldShowCamera.value = false
        shouldShowPhoto.value = true
        Log.d("anja", "camera: ${shouldShowCamera.value}, photo: ${shouldShowPhoto.value} ")
    }

    private fun getOutputDirectory(): File {
        val mediaDir = externalMediaDirs.firstOrNull()?.let {
            File(it, resources.getString(R.string.app_name)).apply { mkdirs() }
        }
        return if(mediaDir != null && mediaDir.exists()) mediaDir else filesDir
    }

    override fun onDestroy() {
        super.onDestroy()
        cameraExecutor.shutdown()
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
    outputDirectory: File,
    cameraExecutor: ExecutorService,
    handleImageCapture: (Uri) -> Unit,
    shouldShowCamera: MutableState<Boolean>,
    shouldShowPhoto: MutableState<Boolean>,
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
            composable("interactiondesign") { InteractionDesignContent() }
            composable("gestures") { GesturesContent() }
            composable("navigation") { NavigationContent() }
            composable("inputmethods") { InputMethodsContent() }
            composable("multimedia") { MultimediaContent() }
            composable("animations") { AnimationsContent() }
            composable("networkcalls") { NetworkCallContent() }
            composable("fileaccess") { FileAccessContent() }
            composable("persistence") { PersistenceContent(storage) }
            composable("contactaccess") { ContactPickerContent(
                context = LocalContext.current,
                contactName,
                contactNumber
            ) }
            composable("camera") {
                CameraContent(
                    outputDirectory = outputDirectory,
                    executor = cameraExecutor,
                    onImageCaptured = handleImageCapture,
                    onError = { print("View error: $it") },
                    showCamera = shouldShowCamera,
                    showPhoto = shouldShowPhoto
                )
            }
            composable("performance") { PerformanceContent() }
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
                        "Interaktionsdesign" -> navController.navigate("interactiondesign")
                        "Gesten" -> navController.navigate("gestures")
                        "Navigation" -> navController.navigate("navigation")
                        "Eingabemethoden" -> navController.navigate("inputmethods")
                        "Multimedia" -> navController.navigate("multimedia")
                        "Animationen" -> navController.navigate("animations")
                        "Netzwerkcalls" -> navController.navigate("networkcalls")
                        "Dateizugriff" -> navController.navigate("fileaccess")
                        "Persistierung" -> navController.navigate("persistence")
                        "Zugriff auf native Anwendungen" -> navController.navigate("contactaccess")
                        "Kamera" -> navController.navigate("camera")
                        "Primzahlberechnung" -> navController.navigate("performance")
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