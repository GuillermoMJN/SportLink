package com.uax.androidmaster.primeraapp.ui.ajustes

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.uax.androidmaster.primeraapp.ui.componentes.BotonPrincipal
import com.uax.androidmaster.primeraapp.ui.componentes.IngresarTexto
import com.uax.androidmaster.primeraapp.ui.perfil.ContentPantallaPerfil
import com.uax.androidmaster.primeraapp.ui.toolBar.CustomToolBar
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
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.Start
    ) {
        IngresarTexto("Editar descripcion", 10)

    }
}
