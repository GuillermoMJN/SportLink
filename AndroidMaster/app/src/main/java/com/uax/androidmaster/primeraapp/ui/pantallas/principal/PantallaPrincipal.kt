package com.uax.androidmaster.primeraapp.ui.pantallas.principal

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.google.firebase.storage.FirebaseStorage
import com.uax.androidmaster.R
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

import com.uax.androidmaster.primeraapp.ui.funciones.cargadatos.CargaDatos
import com.uax.androidmaster.primeraapp.ui.theme.White
import com.uax.androidmaster.primeraapp.ui.toolBar.CustomToolBar
import kotlinx.coroutines.tasks.await
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.mutableStateOf

@Composable
fun PantallaPrincipal(
    navHostController: NavHostController,
    cargaDatosUsuario: CargaDatos,
    navigateToPerfil: () -> Unit,
    navigateToMensajes: () -> Unit,
    navigateToNotificaciones: () -> Unit,
    navigateToBuscar: () -> Unit,
    navigateToInicio: () -> Unit
) {
    Scaffold(topBar = {
        CustomToolBar(
            navHostController,
            navigateToPerfil = navigateToPerfil,
            navigateToMensajes = navigateToMensajes,
            navigateToNotificaciones = navigateToNotificaciones,
            navigateToBuscar = navigateToBuscar,
            navigateToInicio = navigateToInicio
        )
    }) { innerPadding ->
        ContentPantallaPrincipal(
            modifier = Modifier.padding(innerPadding),
            viewModel = cargaDatosUsuario // PASAMOS EL ViewModel
        )
    }
}

@Composable
fun ContentPantallaPrincipal(
    modifier: Modifier,
    viewModel: CargaDatos
) {
    val context = LocalContext.current
    val imagenesUrls = remember { mutableStateListOf<String>() }
    var liked by remember { mutableStateOf(false) }

    // Cargar imágenes al iniciar
    LaunchedEffect(Unit) {
        val storageRef = FirebaseStorage.getInstance().reference.child("imagenesUsuarios")

        try {
            val usuarios = storageRef.listAll().await()

            val todasLasUrls = mutableListOf<String>()

            for (usuarioFolder in usuarios.prefixes) {
                val publicacionesRef = usuarioFolder.child("publicaciones")
                try {
                    val imagenes = publicacionesRef.listAll().await()
                    val urls = imagenes.items.map { it.downloadUrl.await().toString() }
                    todasLasUrls.addAll(urls)
                } catch (e: Exception) {
                    Log.w("CargaImagenes", "Error al cargar publicaciones de ${usuarioFolder.name}: ${e.message}")
                }
            }

            imagenesUrls.clear()
            imagenesUrls.addAll(todasLasUrls.shuffled().take(10)) // aleatorio y máximo 10

        } catch (e: Exception) {
            Toast.makeText(context, "Error cargando imágenes", Toast.LENGTH_SHORT).show()
            Log.e("IMAGENES", "Error: ${e.localizedMessage}", e)
        }
    }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(White),
        verticalArrangement = Arrangement.Top
    ) {
        items(imagenesUrls) { url ->
            AsyncImage(
                model = url,
                contentDescription = "Imagen publicada",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
                    .padding(6.dp)
                    .clip(RoundedCornerShape(12.dp)),
                placeholder = painterResource(id = R.drawable.sportlink),
                error = painterResource(id = R.drawable.sportlink)
            )
            IconButton(
                modifier = Modifier.padding(10.dp),
                onClick = { liked = !liked }
            ) {
                Icon(
                    painter = if (liked) painterResource(R.drawable.like_white) else painterResource(R.drawable.like_blue),
                    contentDescription = "Boton de like"
                )
            }
        }
    }
}