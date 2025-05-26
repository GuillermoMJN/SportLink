package com.uax.androidmaster.primeraapp.ui.funciones.cargadatos

import android.net.Uri
import androidx.activity.compose.ManagedActivityResultLauncher

fun CargaImagenes(
    launcher: ManagedActivityResultLauncher<String, Uri?>,
) {
    launcher.launch("image/*")
}


















