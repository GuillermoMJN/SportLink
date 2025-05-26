package com.uax.androidmaster.primeraapp.ui.ajustes

import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.google.firebase.storage.FirebaseStorage
import com.uax.androidmaster.primeraapp.ui.componentes.BotonPrincipal
import com.uax.androidmaster.primeraapp.ui.funciones.cargadatos.CargaDatos
import com.uax.androidmaster.primeraapp.ui.funciones.cargadatos.CargaImagenes
import com.uax.androidmaster.primeraapp.ui.theme.Blue100
import com.uax.androidmaster.primeraapp.ui.theme.White
import com.uax.androidmaster.primeraapp.ui.toolBar.CustomToolBarAjustes

@Composable
fun PantallaAjsutes(
    navHostController: NavHostController,
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
        CustomToolBarAjustes(
            navHostController,
        )
    }) { innerPadding ->
        ContentPantallaAjustes(
            modifier = Modifier.padding(innerPadding),
            texto = texto,
            cargaDatosUsuario = cargaDatosUsuario,
            launcher = launcher
        )
    }
}

@Composable
fun ContentPantallaAjustes(
    modifier: Modifier,
    texto: MutableState<String>,
    cargaDatosUsuario: CargaDatos,
    launcher: ManagedActivityResultLauncher<String, Uri?>
) {
    val inputDescripcion = remember { mutableStateOf("") }
    val context = LocalContext.current

    Column(
        modifier = modifier.fillMaxSize().padding(8.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Column {
            OutlinedTextField(
                value = inputDescripcion.value,
                onValueChange = { inputDescripcion.value = it },
                label = { Text("Editar descripción") },
                modifier = Modifier.padding(16.dp)
            )
            BotonPrincipal(onClick = {
                cargaDatosUsuario.actualizarDescripcion(inputDescripcion.value)
                Toast.makeText(context, "Descripción guardada", Toast.LENGTH_SHORT).show()
            }, "Guardar Descripcion", Blue100, White)
            BotonPrincipal(
                onClick = {
                    CargaImagenes(launcher)// Lanza la galería
                },
                texto = ("Subir imagen"), colorFondo = Blue100, colorLetra = White
            )
        }
    }
}
