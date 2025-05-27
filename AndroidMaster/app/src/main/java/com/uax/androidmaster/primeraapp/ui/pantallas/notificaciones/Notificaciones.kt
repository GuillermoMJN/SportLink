package com.uax.androidmaster.primeraapp.ui.pantallas.notificaciones

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.uax.androidmaster.primeraapp.ui.toolBar.CustomToolBar

@Composable
fun PantallaNotificaciones(
    navHostController: NavHostController,
    navigateToMensajes: () -> Unit,
    navigateToPerfil: () -> Unit,
    navigateToPrincipal: () -> Unit,
    navigateToBuscar: () -> Unit
) {
    Scaffold(topBar = {
        CustomToolBar(
            navHostController,
            navigateToPerfil = navigateToPerfil,
            navigateToMensajes = navigateToMensajes,
            navigateToPrincipal = navigateToPrincipal,
            navigateToBuscar = navigateToBuscar,
        )
    }) { innerPadding ->
        ContentPantallaNotificaciones(
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun ContentPantallaNotificaciones(modifier: Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.Start
    ) { Text("Notificaciones") }
}