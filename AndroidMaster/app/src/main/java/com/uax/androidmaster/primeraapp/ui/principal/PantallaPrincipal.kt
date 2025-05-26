package com.uax.androidmaster.primeraapp.ui.principal

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.uax.androidmaster.primeraapp.ui.componentes.Imagen
import com.uax.androidmaster.primeraapp.ui.funciones.cargadatos.CargaDatos
import com.uax.androidmaster.primeraapp.ui.theme.White
import com.uax.androidmaster.primeraapp.ui.toolBar.CustomToolBar

@Composable
fun PantallaPrincipal(
    navHostController: NavHostController,
    navigateToPerfil: () -> Unit,
    navigateToMensajes: () -> Unit,
    navigateToNotificaciones: () -> Unit,
    navigateToBuscar: () -> Unit,
    cargaDatosUsuario: CargaDatos
) {
    LaunchedEffect(Unit) {
        cargaDatosUsuario.cargarUID()
    }

    Scaffold(topBar = {
        CustomToolBar(
            navHostController,
            navigateToPerfil,
            navigateToMensajes,
            navigateToNotificaciones,
            navigateToBuscar,
            navigateToBuscar
        )
    }) { innerPadding ->
        ContentPantallaPrincipal(
            modifier = Modifier.padding(innerPadding),
            viewModel = cargaDatosUsuario // PASAMOS EL ViewModel
        )
    }
}

@Composable
fun ContentPantallaPrincipal(
    modifier: Modifier,
    viewModel: CargaDatos
) {
    // Puedes acceder a viewModel.descripcion.collectAsState() si lo necesitas aquí
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(White),
        verticalArrangement = Arrangement.Top
    ) {
        items(3) {
            Imagen()
        }
    }
}
