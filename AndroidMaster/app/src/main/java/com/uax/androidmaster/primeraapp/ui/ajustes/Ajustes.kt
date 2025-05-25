package com.uax.androidmaster.primeraapp.ui.ajustes

import android.widget.Toast
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
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.uax.androidmaster.primeraapp.ui.funciones.descripcion.guardarDescripcion
import com.uax.androidmaster.primeraapp.ui.toolBar.CustomToolBarAjustes

@Composable
fun PantallaAjsutes(
    navHostController: NavHostController,
    texto: MutableState<String>
) {
    Scaffold(topBar = {
        CustomToolBarAjustes(
            navHostController,
        )
    }) { innerPadding ->
        ContentPantallaAjustes(
            modifier = Modifier.padding(innerPadding),
            texto = texto
        )
    }
}
@Composable
fun ContentPantallaAjustes(modifier: Modifier, texto: MutableState<String>) {
    val inputDescripcion = remember { mutableStateOf("") }
    val db = Firebase.firestore
    val context = LocalContext.current

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.Start
    ) {
        OutlinedTextField(
            value = inputDescripcion.value,
            onValueChange = { inputDescripcion.value = it },
            label = { Text("Editar descripción") },
            modifier = Modifier.padding(16.dp)
        )

        Button(
            onClick = {
                guardarDescripcion(
                    db,
                    inputDescripcion.value,
                    onSuccess = {
                        texto.value = inputDescripcion.value
                        Toast.makeText(context, "Descripción guardada", Toast.LENGTH_SHORT).show()
                    },
                    onError = {
                        Toast.makeText(context, "Error al guardar", Toast.LENGTH_SHORT).show()
                    }
                )
            },
            modifier = Modifier.padding(16.dp)
        ) {
            Text("Guardar")
        }
    }
}
