package com.uax.androidmaster.primeraapp.ui.componentes

import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.uax.androidmaster.R
import com.uax.androidmaster.primeraapp.ui.constantes.ConstantesFirestore
import com.uax.androidmaster.primeraapp.ui.theme.Black
import kotlinx.coroutines.tasks.await

@Composable
fun PerfilPompa(nombreUsuario: String, onClick: () -> Unit = {}) {
    val imagenPerfilUrl = rememberSaveable { mutableStateOf<String?>(null) }
    val uidBuscado = remember { mutableStateOf<String?>(null) }

    LaunchedEffect(nombreUsuario) {
        try {
            // Buscar el UID del usuario por nombre en Firestore
            val db = FirebaseFirestore.getInstance()
            val snapshot = db.collection(ConstantesFirestore.BBDD_USUARIOS)
                .whereEqualTo(ConstantesFirestore.BBDD_NOMBRE, nombreUsuario)
                .get()
                .await()

            val doc = snapshot.documents.firstOrNull()
            val uid = doc?.getString("uid")

            uidBuscado.value = uid

            if (!uid.isNullOrEmpty()) {
                // Cargar imagen de perfil
                val perfilRef = FirebaseStorage.getInstance()
                    .reference
                    .child("imagenesUsuarios/$uid/perfil/perfil.jpg")

                val uri = perfilRef.downloadUrl.await()
                imagenPerfilUrl.value = uri.toString()
            }
        } catch (e: Exception) {
            Log.e("PERFIL", "Error obteniendo UID o imagen", e)
            imagenPerfilUrl.value = null
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
                contentDescription = ConstantesFirestore.BBDD_FOTOPERFIL,
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