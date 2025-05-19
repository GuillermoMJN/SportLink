package com.uax.androidmaster.primeraapp.ui.mensajes

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
fun PantallaMensajes(
    navHostController: NavHostController,
    navigateToPerfil: () -> Unit,
    navigateToNotificaciones: () -> Unit
) {
    Scaffold(topBar = {
        CustomToolBar(
            navHostController,
            navigateToPerfil = navigateToPerfil,
            navigateToNotificaciones = navigateToNotificaciones
        )
    }) { innerPadding ->
        ContentPantallaMensajes(
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun ContentPantallaMensajes(modifier: Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.Start
    ) { Text("Mensajes") }
}