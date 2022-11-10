package com.example.monstradore.android

import android.Manifest
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.monstradore.android.hardwarefunctions.CameraContent
import com.example.monstradore.android.inputmethods.InputMethodsContent
import com.example.monstradore.android.navigation.NavigationContent
import com.example.monstradore.android.performance.PerformanceContent
import com.example.monstradore.android.ui.theme.MonstradoreTheme
import com.example.monstradore.android.uiux.AndroidElementsContent
import com.example.monstradore.android.uiux.UIElementsContent
import com.example.monstradore.android.uiux.iOSElementsContent
import com.example.monstradore.structures.Category
import com.example.monstradore.structures.Features
import java.io.File
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class OverviewActivity : ComponentActivity() {

    private lateinit var outputDirectory: File
    private lateinit var cameraExecutor: ExecutorService
    private var shouldShowCamera: MutableState<Boolean> = mutableStateOf(false)
    private var shouldShowPhoto: MutableState<Boolean> = mutableStateOf(false)

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        shouldShowCamera.value = isGranted
        Log.d("anja", isGranted.toString())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MonstradoreTheme {
                Content(
                    outputDirectory,
                    cameraExecutor,
                    ::handleImageCapture,
                    shouldShowCamera,
                    shouldShowPhoto
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

}

@Composable
fun Content(
    outputDirectory: File,
    cameraExecutor: ExecutorService,
    handleImageCapture: (Uri) -> Unit,
    shouldShowCamera: MutableState<Boolean>,
    shouldShowPhoto: MutableState<Boolean>
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
                        "Navigation" -> navController.navigate("navigation")
                        "Eingabemethoden" -> navController.navigate("inputmethods")
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