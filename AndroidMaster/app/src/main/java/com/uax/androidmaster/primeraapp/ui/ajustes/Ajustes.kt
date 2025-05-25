package com.uax.androidmaster.primeraapp.ui.ajustes

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
import com.uax.androidmaster.primeraapp.ui.toolBar.CustomToolBarAjustes

@Composable
fun PantallaAjsutes(
    navHostController: NavHostController,
) {
    Scaffold(topBar = {
        CustomToolBarAjustes(
            navHostController,
        )
    }) { innerPadding ->
        ContentPantallaAjustes(
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun ContentPantallaAjustes(modifier: Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.Start
    ) { Text("Ajustes") }
}