package com.uax.androidmaster.primeraapp.ui.toolBar

import androidx.compose.foundation.background
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.uax.androidmaster.R
import com.uax.androidmaster.primeraapp.ui.theme.Blue100
import com.uax.androidmaster.primeraapp.ui.theme.Blue60
import com.uax.androidmaster.primeraapp.ui.theme.White
import androidx.compose.runtime.getValue
import java.security.Principal

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomToolBar(
    navhHostController: NavHostController,
    navigateToPerfil: () -> Unit = {},
    navigateToMensajes: () -> Unit = {},
    navigateToNotificaciones: () -> Unit = {}, navigateToPrincipal: () -> Unit = {}
) {
    val navBackStackEntry by navhHostController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    TopAppBar(
        title = {
            when (currentRoute) {
                "perfil" -> Text("Perfil")
                "notificaciones" -> Text("Notificaciones")
                "mensajes" -> Text("Mensajes")
                else -> Text("SportLink")
            }
        },
        colors = topAppBarColors(containerColor = Blue60),
        navigationIcon = {
            IconButton(onClick = {
                when (currentRoute) {
                    "perfil" -> navigateToPrincipal()
                    "notificaciones" -> navigateToPrincipal()
                    "mensajes" -> navigateToPrincipal()
                    else -> navhHostController.popBackStack()
                }
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.arrow_back),
                    contentDescription = "Atras"
                )
            }
        },
        actions = {
            IconButton(onClick = {
                navigateToNotificaciones()
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.notificaciones),
                    contentDescription = "Notificaciones"
                )
            }
            IconButton(onClick = {
                navigateToPerfil()
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.perfil),
                    contentDescription = "Perfil"
                )
            }
            IconButton(onClick = {
                navigateToMensajes()
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.mensajes),
                    contentDescription = "Mensajes"
                )
            }
        })
}