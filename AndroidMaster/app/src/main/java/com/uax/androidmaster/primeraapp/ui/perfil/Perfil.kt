package com.uax.androidmaster.primeraapp.ui.perfil

import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.uax.androidmaster.R
import com.uax.androidmaster.primeraapp.ui.toolBar.CustomToolBar

import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable

import androidx.compose.ui.Alignment

import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil3.compose.AsyncImage
import coil3.compose.rememberAsyncImagePainter
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.google.firebase.storage.FirebaseStorage
import com.uax.androidmaster.primeraapp.ui.componentes.BotonPrincipal
import com.uax.androidmaster.primeraapp.ui.funciones.cargadatos.CargaDatos
import com.uax.androidmaster.primeraapp.ui.funciones.cargadatos.CargaImagenes
import com.uax.androidmaster.primeraapp.ui.funciones.descripcion.cargarDescripcionPerfil
import com.uax.androidmaster.primeraapp.ui.theme.Blue100
import com.uax.androidmaster.primeraapp.ui.theme.White

@Composable
fun PantallaPerfil(
    navHostController: NavHostController,
    navigateToMensajes: () -> Unit,
    navigateToNotificaciones: () -> Unit,
    navigateToPrincipal: () -> Unit,
    navigateToBuscar: () -> Unit,
    navigateToAjustes: () -> Unit,
    texto: MutableState<String>,
    cargaDatos: CargaDatos

) {
    Scaffold(topBar = {
        CustomToolBar(
            navHostController,
            navigateToMensajes = navigateToMensajes,
            navigateToNotificaciones = navigateToNotificaciones,
            navigateToPrincipal = navigateToPrincipal,
            navigateToBuscar = navigateToBuscar
        )
    }) { innerPadding ->
        ContentPantallaPerfil(
            modifier = Modifier.padding(innerPadding),
            navController = navHostController,
            texto = texto,
            cargaDatosUsuario = cargaDatos
        )
    }
}

@Composable
fun ContentPantallaPerfil(
    modifier: Modifier,
    navController: NavHostController,
    texto: MutableState<String>,
    cargaDatosUsuario: CargaDatos
) {
    val imagenPerfilUrl = rememberSaveable { mutableStateOf<String?>(null) }

    val db = Firebase.firestore
    val context = LocalContext.current

    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        uri?.let {
            val uid = cargaDatosUsuario.uid
            if (uid != null) {
                // Crea un nombre de archivo único (por ejemplo, con timestamp)
                val fileName = "img_${System.currentTimeMillis()}.jpg"

                // Ruta: imagenesUsuarios/UID/publicaciones/img_123456789.jpg
                val storageRef = FirebaseStorage.getInstance()
                    .reference
                    .child("imagenesUsuarios/$uid/publicaciones/$fileName")

                storageRef.putFile(uri)
                    .addOnSuccessListener {
                        Toast.makeText(context, "Imagen subida correctamente", Toast.LENGTH_SHORT)
                            .show()
                    }
                    .addOnFailureListener {
                        Toast.makeText(context, "Error al subir la imagen", Toast.LENGTH_SHORT)
                            .show()
                    }
            } else {
                Toast.makeText(context, "UID no disponible", Toast.LENGTH_SHORT).show()
            }
        }
    }

    val uid = cargaDatosUsuario.uid
    Toast.makeText(context, "UID: $uid", Toast.LENGTH_SHORT).show()

    //Aqui se tiene que subir las fotos:
    val fotos = remember {
        mutableStateListOf(
            R.drawable.sportlink
        )
    }

    LaunchedEffect(Unit) {
        val uid = cargaDatosUsuario.uid
        if (uid != null) {
            FirebaseStorage.getInstance()
                .reference
                .child("imagenesUsuarios/$uid/perfil/perfil.jpg")
                .downloadUrl
                .addOnSuccessListener { uri ->
                    imagenPerfilUrl.value = uri.toString()
                    Toast.makeText(context, "URL cargada", Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener {
                    Toast.makeText(
                        context,
                        "No se pudo cargar la imagen de perfil",
                        Toast.LENGTH_SHORT
                    ).show()
                }
        }
    }

    val painter = rememberAsyncImagePainter(model = imagenPerfilUrl.value)

    LaunchedEffect(true) {
        cargarDescripcionPerfil(db) {
            texto.value = it ?: ""
        }
    }
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(1.dp)
    ) {
        Row(
            modifier = Modifier.padding(10.dp),
            verticalAlignment = Alignment.Top// para que queden alineados
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(imagenPerfilUrl.value)
                    .crossfade(true)
                    .build(),
                contentDescription = "fotoPerfil",
                modifier = Modifier
                    .height(100.dp)
                    .aspectRatio(1f)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop,
                placeholder = painterResource(id = R.drawable.sportlink), // imagen mientras carga
                error = painterResource(id = R.drawable.sportlink)        // si falla la carga
            )
            //Esta es la imagen de perfil

            Spacer(modifier = Modifier.width(8.dp)) // pequeño espacio opcional
            Column(horizontalAlignment = Alignment.Start) {
                //Descripcion
                Text(
                    text = texto.value,
                )
            }
            Column(
                modifier = Modifier,
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.End
            ) {
                IconButton(onClick = { navController.navigate("ajustes") }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ajustes),
                        contentDescription = "Ir al perfil",
                    )
                }
            }
            BotonPrincipal(
                onClick = {
                    CargaImagenes(launcher)// Lanza la galería
                },
                texto = ("Subir imagen"), colorFondo = Blue100, colorLetra = White
            )
        }

        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = Modifier
                .fillMaxSize()
                .padding(6.dp),
            contentPadding = PaddingValues(4.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            items(fotos) { fotoId ->
                Image(
                    painter = painterResource(id = fotoId),
                    contentDescription = "Foto publicada",
                    modifier = Modifier
                        .aspectRatio(1f) // Para mantener imágenes cuadradas
                        .clip(RoundedCornerShape(8.dp)),
                    contentScale = ContentScale.Crop
                )
            }
        }

    }
}
