package com.uax.androidmaster.primeraapp.ui.pantallas.mensajes

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.uax.androidmaster.primeraapp.ui.componentes.TextoLlamativo
import com.uax.androidmaster.primeraapp.ui.toolBar.CustomToolBar

@Composable
fun PantallaMensajes(
    navHostController: NavHostController,
    navigateToPerfil: () -> Unit,
    navigateToNotificaciones: () -> Unit,
    navigateToPrincipal: () -> Unit,
    navigateToBuscar: () -> Unit
) {
    Scaffold(topBar = {
        CustomToolBar(
            navHostController,
            navigateToPerfil = navigateToPerfil,
            navigateToNotificaciones = navigateToNotificaciones,
            navigateToPrincipal = navigateToPrincipal,
            navigateToBuscar = navigateToBuscar
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
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextoLlamativo()
    }
}