package com.uax.androidmaster.primeraapp.ui.pantallas.buscar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.uax.androidmaster.primeraapp.ui.componentes.BotonPrincipal
import com.uax.androidmaster.primeraapp.ui.componentes.PerfilPompa
import com.uax.androidmaster.primeraapp.ui.funciones.cargadatos.CargaDatos
import com.uax.androidmaster.primeraapp.ui.model.UsuarioClicado
import com.uax.androidmaster.primeraapp.ui.theme.Blue100
import com.uax.androidmaster.primeraapp.ui.theme.White
import com.uax.androidmaster.primeraapp.ui.toolBar.CustomToolBar
import kotlinx.coroutines.tasks.await

@Composable
fun PantallaBuscar(
    navHostController: NavHostController,
    navigateToPerfil: () -> Unit,
    navigateToNotificaciones: () -> Unit,
    navigateToPrincipal: () -> Unit,
    navigateToMensajes: () -> Unit,
    cargaDatosUsuario: CargaDatos
) {
    val userId = FirebaseAuth.getInstance().currentUser?.uid ?: ""
    Scaffold(topBar = {
        CustomToolBar(
            navHostController,
            navigateToPerfil = navigateToPerfil,
            navigateToMensajes = navigateToMensajes,
            navigateToNotificaciones = navigateToNotificaciones,
            navigateToPrincipal = navigateToPrincipal
        )
    }) { innerPadding ->
        ContentPantallaBuscar(
            modifier = Modifier.padding(innerPadding),
            userId = userId,
            navHostController = navHostController,
            cargaDatosUsuario = cargaDatosUsuario
        )
    }
}

@Composable
fun ContentPantallaBuscar(
    userId: String,
    modifier: Modifier = Modifier,
    cargaDatosUsuario: CargaDatos,
    navHostController: NavHostController
) {
    var searchQuery by remember { mutableStateOf("") }
    var perfiles by remember { mutableStateOf<List<UsuarioClicado>>(emptyList()) }
    var resultados by remember { mutableStateOf<List<UsuarioClicado>>(emptyList()) }
    val db = FirebaseFirestore.getInstance()

    LaunchedEffect(Unit) {
        try {
            val result = db.collection("usuarios").get().await()
            val lista = result.documents.mapNotNull {
                val nombre = it.getString("nombre")
                val uid = it.id
                if (nombre != null) UsuarioClicado(uid, nombre) else null
            }
            perfiles = lista
            resultados = lista
        } catch (e: Exception) {
            perfiles = emptyList()
            resultados = emptyList()
        }
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        horizontalAlignment = Alignment.Start
    ) {
        TextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            label = { Text("Buscar") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        BotonPrincipal(
            onClick = {
                resultados = perfiles.filter {
                    it.nombre.startsWith(searchQuery, ignoreCase = true) ||
                            it.nombre.contains(searchQuery, ignoreCase = true)
                }
            },
            texto = "Buscar",
            colorFondo = Blue100,
            colorLetra = White
        )

        Spacer(modifier = Modifier.height(8.dp))

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .background(White),
            verticalArrangement = Arrangement.Top
        ) {
            items(resultados) { usuario ->
                PerfilPompa(nombreUsuario = usuario.nombre, cargaDatosUsuario = cargaDatosUsuario) {
                    navHostController.navigate("perfilClicado/${usuario.uid}")
                }
            }
        }
    }
}
