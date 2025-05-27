package com.uax.androidmaster.primeraapp.ui.componentes

import android.R.attr.onClick
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.google.firebase.storage.FirebaseStorage

import com.uax.androidmaster.R
import com.uax.androidmaster.primeraapp.ui.funciones.cargadatos.CargaDatos
import com.uax.androidmaster.primeraapp.ui.theme.Black
import kotlinx.coroutines.tasks.await

@Composable
fun PerfilPompa(nombreUsuario: String,cargaDatosUsuario: CargaDatos,  onClick: () -> Unit = {}) {
    val imagenPerfilUrl = rememberSaveable { mutableStateOf<String?>(null) }
    val uid = cargaDatosUsuario.uid
    val fotosUrls = remember { mutableStateListOf<String>() }
    val context = LocalContext.current

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

    LaunchedEffect(uid) {
        if (!uid.isNullOrEmpty()) {
            try {
                val perfilRef = FirebaseStorage.getInstance()
                    .reference
                    .child("imagenesUsuarios/$uid/perfil/perfil.jpg")

                val uri = perfilRef.downloadUrl.await()
                Log.d("PERFIL", "URL imagen perfil: $uri")
                imagenPerfilUrl.value = uri.toString()

                cargarFotosPublicaciones(uid, fotosUrls)
            } catch (e: Exception) {
                Log.e("PERFIL", "Error cargando imagen perfil", e)
                imagenPerfilUrl.value = null
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(1.dp)
            .border(2.dp, color = Black, shape = RoundedCornerShape(8.dp))
            .padding(10.dp)
            .clickable { onClick() } // Espacio interno dentro del borde
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            AsyncImage(
                model = imagenPerfilUrl.value ?: R.drawable.sportlink,
                contentDescription = "fotoPerfil",
                modifier = Modifier
                    .size(64.dp)
                    .aspectRatio(1f)
                    .clip(RoundedCornerShape(16.dp)),
                contentScale = ContentScale.Crop,
                placeholder = painterResource(id = R.drawable.sportlink),
                error = painterResource(id = R.drawable.sportlink)
            )

            Spacer(modifier = Modifier.width(12.dp)) // Espacio entre imagen y texto

            Text(
                text = nombreUsuario,
                modifier = Modifier.align(Alignment.CenterVertically)
            )
        }
    }
}