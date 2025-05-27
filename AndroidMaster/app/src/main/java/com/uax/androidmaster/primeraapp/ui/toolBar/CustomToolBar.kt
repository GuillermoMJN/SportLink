package com.uax.androidmaster.primeraapp.ui.toolBar

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.uax.androidmaster.R
import com.uax.androidmaster.primeraapp.ui.theme.Blue60
import androidx.compose.runtime.getValue
import com.uax.androidmaster.primeraapp.ui.funciones.cargadatos.CargaDatos

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomToolBar(
    navhHostController: NavHostController,
    navigateToPerfil: () -> Unit = {},
    navigateToMensajes: () -> Unit = {},
    navigateToNotificaciones: () -> Unit = {},
    navigateToPrincipal: () -> Unit = {},
    navigateToBuscar: () -> Unit = {},
    navigateToInicio:() -> Unit = {}
) {
    val navBackStackEntry by navhHostController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    TopAppBar(
        title = {
            when (currentRoute) {
                "perfil" -> Text("Perfil")
                "notificaciones" -> Text("Notificaciones")
                "mensajes" -> Text("Mensajes")
                "buscar" -> Text("Buscar")
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
                    "login" -> navigateToInicio()
                    "buscar" -> navigateToPrincipal()
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
            IconButton(onClick = {
                navigateToBuscar()
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.buscar),
                    contentDescription = "Buscar"
                )
            }
        })
}
