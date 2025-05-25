package com.uax.androidmaster.primeraapp.ui.model

import android.media.Image
import androidx.compose.ui.graphics.ImageBitmap
import coil3.compose.AsyncImage
import coil3.compose.AsyncImagePainter

data class Perfil(
    val descripcion: String,
    val fotoPerfil: Image
) {
}