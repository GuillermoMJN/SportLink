package com.uax.androidmaster.primeraapp.ui.perfil

import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.ManagedActivityResultLauncher
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

import androidx.compose.ui.Alignment

import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.google.firebase.storage.FirebaseStorage
import com.uax.androidmaster.primeraapp.ui.componentes.BotonPrincipal
import com.uax.androidmaster.primeraapp.ui.funciones.cargadatos.CargaDatos
import com.uax.androidmaster.primeraapp.ui.funciones.cargadatos.CargaImagenes
import com.uax.androidmaster.primeraapp.ui.funciones.descripcion.cargarDescripcionPerfil

@Composable
fun PantallaPerfil(
    navHostController: NavHostController,
    navigateToMensajes: () -> Unit,
    navigateToNotificaciones: () -> Unit,
    navigateToPrincipal: () -> Unit,
    navigateToBuscar: () -> Unit,
    navigateToAjustes: () -> Unit,
    texto: MutableState<String>,
    cargaDatosUsuario: CargaDatos
) {
    val context = LocalContext.current

    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        uri?.let {
            val uid = cargaDatosUsuario.uid
            if (uid != null) {
                // Subir a imagenesUsuarios/UID/perfil/perfil.jpg
                val storageRef = FirebaseStorage.getInstance()
                    .reference
                    .child("imagenesUsuarios/$uid/perfil/perfil.jpg")

                storageRef.putFile(uri)
                    .addOnSuccessListener {
                        Toast.makeText(context, "Imagen de perfil subida correctamente", Toast.LENGTH_SHORT).show()
                    }
                    .addOnFailureListener {
                        Toast.makeText(context, "Error al subir la imagen de perfil", Toast.LENGTH_SHORT).show()
                    }
            } else {
                Toast.makeText(context, "UID no disponible", Toast.LENGTH_SHORT).show()
            }
        }
    }
    Scaffold(topBar = {
        CustomToolBar(
            navHostController,
            navigateToMensajes = navigateToMensajes,
            navigateToNotificaciones = navigateToNotificaciones,
            navigateToPrincipal = navigateToPrincipal,
            navigateToBuscar = navigateToBuscar,
        )
    }) { innerPadding ->
        ContentPantallaPerfil(
            modifier = Modifier.padding(innerPadding),
            navController = navHostController,
            texto = texto,
            cargaDatosUsuario = cargaDatosUsuario,
            launcher = launcher
        )
    }
}

@Composable
fun ContentPantallaPerfil(
    modifier: Modifier,
    navController: NavHostController,
    texto: MutableState<String>,
    cargaDatosUsuario: CargaDatos,
    launcher: ManagedActivityResultLauncher<String, Uri?>
) {

    val db = Firebase.firestore

    //Aqui se tiene que subir las fotos:
    val fotos = remember {
        mutableStateListOf(
            R.drawable.sportlink
        )
    }
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
            Image(
                painter = painterResource(id = R.drawable.sportlink),
                contentDescription = "fotoPerfil",
                modifier = Modifier
                    .height(100.dp) // altura fija más controlada
                    .clip(RoundedCornerShape(8.dp)) // opcional: darle forma
            )

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
        }
        BotonPrincipal(
            onClick = {
                CargaImagenes(launcher)// Lanza la galería
            },
            texto = ("Subir imagen")
        )
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
