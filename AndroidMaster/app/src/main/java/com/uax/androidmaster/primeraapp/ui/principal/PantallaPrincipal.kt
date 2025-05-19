package com.uax.androidmaster.primeraapp.ui.principal

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.uax.androidmaster.primeraapp.ui.perfil.Imagen
import com.uax.androidmaster.primeraapp.ui.theme.White
import com.uax.androidmaster.primeraapp.ui.toolBar.CustomToolBar

@Composable
fun PantallaPrincipal(
    navHostController: NavHostController,
    navigateToPerfil: () -> Unit,
    navigateToMensajes: () -> Unit,
    navigateToNotificaciones: () -> Unit
) {
    Scaffold(topBar = { CustomToolBar(navHostController, navigateToPerfil, navigateToMensajes, navigateToNotificaciones) }) { innerPadding ->
        ContentPantallaPrincipal(
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun ContentPantallaPrincipal(
    modifier: Modifier
) {
    val total: Int = 3
    var i: Int = 0
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(White),
        verticalArrangement = Arrangement.Top
    ) {
        items(total){index ->
            Imagen()
        }
    }
}
