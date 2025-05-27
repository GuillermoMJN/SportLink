package com.uax.androidmaster.primeraapp.ui.perfil

import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.runtime.snapshots.SnapshotStateList

import androidx.compose.ui.Alignment

import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import coil3.compose.AsyncImage
import com.google.firebase.storage.FirebaseStorage
import com.uax.androidmaster.primeraapp.ui.componentes.BotonPrincipal
import com.uax.androidmaster.primeraapp.ui.funciones.cargadatos.CargaDatos
import com.uax.androidmaster.primeraapp.ui.theme.Black
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
    textoDescripcion: MutableState<String>,
    cargaDatos: CargaDatos,
    textoNombre: MutableState<String>

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
            textoDescripcion = textoDescripcion,
            textoNombre = textoNombre,
            cargaDatosUsuario = cargaDatos
        )
    }
}

@Composable
fun ContentPantallaPerfil(
    modifier: Modifier,
    navController: NavHostController,
    textoDescripcion: MutableState<String>,
    cargaDatosUsuario: CargaDatos,
    textoNombre: MutableState<String>
) {
    val context = LocalContext.current
    val uid = cargaDatosUsuario.uid

    val imagenPerfilUrl = rememberSaveable { mutableStateOf<String?>(null) }
    val fotosUrls = remember { mutableStateListOf<String>() }

    LaunchedEffect(cargaDatosUsuario.descripcion.value) {
        textoDescripcion.value = cargaDatosUsuario.descripcion.value
    }

    LaunchedEffect(cargaDatosUsuario.nombre.value) {
        textoNombre.value = cargaDatosUsuario.nombre.value
    }

    fun cargarFotosPublicaciones(uid: String, fotosList: SnapshotStateList<String>) {
        val publicacionesRef = FirebaseStorage.getInstance()
            .reference
            .child("imagenesUsuarios/$uid/publicaciones")

        publicacionesRef.listAll()
            .addOnSuccessListener { listResult ->
                fotosList.clear()
                listResult.items.forEach { storageRef ->
                    storageRef.downloadUrl
                        .addOnSuccessListener { uri ->
                            Log.d("FOTOS", "Foto URL: $uri")
                            fotosList.add(uri.toString())
                        }
                        .addOnFailureListener { e ->
                            Log.e("FOTOS", "Error al obtener downloadUrl", e)
                        }
                }
            }
            .addOnFailureListener { e ->
                Toast.makeText(context, "Error cargando publicaciones", Toast.LENGTH_SHORT).show()
                Log.e("FOTOS", "Error listAll publicaciones", e)
                fotosList.clear()
            }
    }

    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        uri?.let {
            if (uid != null) {
                val fileName = "img_${System.currentTimeMillis()}.jpg"
                val storageRef = FirebaseStorage.getInstance()
                    .reference
                    .child("imagenesUsuarios/$uid/publicaciones/$fileName")

                storageRef.putFile(uri)
                    .addOnSuccessListener {
                        Toast.makeText(context, "Imagen subida correctamente", Toast.LENGTH_SHORT).show()
                        cargarFotosPublicaciones(uid, fotosUrls)
                    }
                    .addOnFailureListener {
                        Toast.makeText(context, "Error al subir la imagen", Toast.LENGTH_SHORT).show()
                    }
            } else {
                Toast.makeText(context, "UID no disponible", Toast.LENGTH_SHORT).show()
            }
        }
    }

    LaunchedEffect(uid) {
        if (!uid.isNullOrEmpty()) {
            val perfilRef = FirebaseStorage.getInstance()
                .reference
                .child("imagenesUsuarios/$uid/perfil/perfil.jpg")

            perfilRef.downloadUrl
                .addOnSuccessListener { uri ->
                    Log.d("PERFIL", "URL imagen perfil: $uri")
                    imagenPerfilUrl.value = uri.toString()
                }
                .addOnFailureListener { e ->
                    Log.e("PERFIL", "Error cargando imagen perfil", e)
                    imagenPerfilUrl.value = null
                }

            cargarFotosPublicaciones(uid, fotosUrls)
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(1.dp)
    ) {
        Row(
            modifier = Modifier.padding(10.dp),
            verticalAlignment = Alignment.Top
        ) {
            AsyncImage(
                model = imagenPerfilUrl.value ?: R.drawable.sportlink,
                contentDescription = "fotoPerfil",
                modifier = Modifier
                    .height(100.dp)
                    .aspectRatio(1f)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop,
                placeholder = painterResource(id = R.drawable.sportlink),
                error = painterResource(id = R.drawable.sportlink)
            )

            Spacer(modifier = Modifier.width(8.dp))

            Column(horizontalAlignment = Alignment.Start) {
                Text(text = textoNombre.value, fontWeight = FontWeight.Bold, color = Black)
                Text(text = textoDescripcion.value)
            }

            Column(
                modifier = Modifier.weight(1f), // para que ocupe el espacio y empuje el icono a la derecha
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
                    launcher.launch("image/*")
                },
                texto = "Subir imagen",
                colorFondo = Blue100,
                colorLetra = White
            )
        }

        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = Modifier
                .weight(1f)
                .padding(6.dp),
            contentPadding = PaddingValues(4.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            items(fotosUrls) { url ->
                AsyncImage(
                    model = url,
                    contentDescription = "Foto publicada",
                    modifier = Modifier
                        .aspectRatio(1f)
                        .clip(RoundedCornerShape(8.dp)),
                    contentScale = ContentScale.Crop,
                    placeholder = painterResource(id = R.drawable.sportlink),
                    error = painterResource(id = R.drawable.sportlink)
                )
            }
        }
    }
}
