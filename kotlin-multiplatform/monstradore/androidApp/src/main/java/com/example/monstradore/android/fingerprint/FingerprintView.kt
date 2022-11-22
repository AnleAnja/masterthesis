package com.example.monstradore.android.fingerprint

import android.app.Activity
import android.hardware.biometrics.BiometricManager
import android.hardware.biometrics.BiometricPrompt
import android.os.Build
import android.os.CancellationSignal
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat

@RequiresApi(Build.VERSION_CODES.R)
@Composable
fun FingerprintContent(activity: Activity) {
    val success = remember { mutableStateOf("") }
    val cancellationSignal = CancellationSignal()
    cancellationSignal.setOnCancelListener {
        //doesn't get called after cancel is clicked on the biometrics prompt
    }

    val context = LocalContext.current
    val executor = remember { ContextCompat.getMainExecutor(activity) }
    val biometricPrompt = BiometricPrompt.Builder(context)
        .setTitle("Biometrische Authentisierung")
        .setSubtitle("Fingerprint")
        .setAllowedAuthenticators(BiometricManager.Authenticators.BIOMETRIC_STRONG or BiometricManager.Authenticators.DEVICE_CREDENTIAL)
        .build()
    biometricPrompt.authenticate(cancellationSignal, executor, object :
        BiometricPrompt.AuthenticationCallback() {
        override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult?) {
            success.value = "Fingerabdruck akzeptiert"
        }
    })

    Column(
        modifier = Modifier.padding(10.dp)
    ) {
        Text(
            text = "Fingerabdruck",
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold
        )
        Text(success.value)
    }

}