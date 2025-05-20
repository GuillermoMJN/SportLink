package com.uax.androidmaster.primeraapp.ui.perfil

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.uax.androidmaster.R
import com.uax.androidmaster.primeraapp.ui.toolBar.CustomToolBar

import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.ui.Alignment

import androidx.compose.ui.layout.ContentScale

@Composable
fun PantallaPerfil(
    navHostController: NavHostController,
    navigateToMensajes: () -> Unit,
    navigateToNotificaciones: () -> Unit,
    navigateToPrincipal: () -> Unit,
    navigateToBuscar: () -> Unit
) {
    Scaffold(topBar = {
        CustomToolBar(
            navHostController,
            navigateToMensajes = navigateToMensajes,
            navigateToNotificaciones = navigateToNotificaciones,
            navigateToPrincipal = navigateToPrincipal,
            navigateToBuscar = navigateToBuscar
        )
    }) { innerPadding ->
        ContentPantallaPerfil(
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun ContentPantallaPerfil(modifier: Modifier) {
    val fotos = remember {
        mutableStateListOf(
            R.drawable.sportlink,
            R.drawable.sportlink,
            R.drawable.sportlink,
            R.drawable.sportlink,
            R.drawable.sportlink,
            R.drawable.like_blue
        )
    }
    Column(modifier = modifier
        .fillMaxSize()
        .padding(1.dp)) {
        Row(modifier = Modifier.padding(10.dp),
            verticalAlignment = Alignment.CenterVertically// para que queden alineados
        ) {
            Image(
                painter = painterResource(id = R.drawable.sportlink),
                contentDescription = "fotoPerfil",
                modifier = Modifier
                    .height(100.dp) // altura fija más controlada
                    .clip(RoundedCornerShape(8.dp)) // opcional: darle forma
            )

            Spacer(modifier = Modifier.width(8.dp)) // pequeño espacio opcional
            Column(horizontalAlignment = Alignment.Start) {
                Text(
                    text = "Aquí iría el perfil",
                )
                Text(
                    text = "Aquí iría el perfil",
                )
            }

        }
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = Modifier
                .fillMaxSize()
                .padding(6.dp),
            contentPadding = PaddingValues(4.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            items(fotos) { fotoId ->
                Image(
                    painter = painterResource(id = fotoId),
                    contentDescription = "Foto publicada",
                    modifier = Modifier
                        .aspectRatio(1f) // Para mantener imágenes cuadradas
                        .clip(RoundedCornerShape(8.dp)),
                    contentScale = ContentScale.Crop
                )
            }
        }
    }
}
