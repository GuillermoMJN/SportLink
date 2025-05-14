package com.uax.androidmaster.primeraapp.ui.perfil

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import com.uax.androidmaster.R
import com.uax.androidmaster.primeraapp.ui.theme.White
import com.uax.androidmaster.primeraapp.ui.toolBar.CustomToolBar

@Composable
fun PantallaPerfil(
    navHostController: NavHostController
) {
    Scaffold(topBar = { CustomToolBar(navHostController) }) { innerPadding ->
        ContentPantallaPerfil(
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun ContentPantallaPerfil(
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
