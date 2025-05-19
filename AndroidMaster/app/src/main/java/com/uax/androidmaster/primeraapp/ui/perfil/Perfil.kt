package com.uax.androidmaster.primeraapp.ui.perfil

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.uax.androidmaster.primeraapp.ui.principal.ContentPantallaPrincipal
import com.uax.androidmaster.primeraapp.ui.toolBar.CustomToolBar

@Composable
fun PantallaPerfil(
    navHostController: NavHostController,
    navigateToMensajes: () -> Unit,
    navigateToNotificaciones: () -> Unit,
    navigateToPrincipal: () -> Unit
) {
    Scaffold(topBar = {
        CustomToolBar(
            navHostController,
            navigateToMensajes = navigateToMensajes,
            navigateToNotificaciones
                     = navigateToNotificaciones,
            navigateToPrincipal = navigateToPrincipal
        )
    }) { innerPadding ->
        ContentPantallaPerfil(
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun ContentPantallaPerfil(modifier: Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.Start
    ) { Text("Perfil") }
}