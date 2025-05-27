package com.uax.androidmaster.primeraapp

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.uax.androidmaster.primeraapp.ui.pantallas.ajustes.PantallaAjsutes
import com.uax.androidmaster.primeraapp.ui.pantallas.buscar.PantallaBuscar
import com.uax.androidmaster.primeraapp.ui.funciones.cargadatos.CargaDatos
import com.uax.androidmaster.primeraapp.ui.pantallas.initial.InitialScreen
import com.uax.androidmaster.primeraapp.ui.pantallas.principal.PantallaPrincipal
import com.uax.androidmaster.primeraapp.ui.initial.RegisterScreen
import com.uax.androidmaster.primeraapp.ui.pantallas.mensajes.PantallaMensajes
import com.uax.androidmaster.primeraapp.ui.pantallas.notificaciones.PantallaNotificaciones
import com.uax.androidmaster.primeraapp.ui.pantallas.perfil.PantallaPerfil

@Composable
fun NavigationWrapper(
    navHostController: NavHostController,
    auth: FirebaseAuth,
    db: FirebaseFirestore
) {
    val textoDescripcion = remember { mutableStateOf("Descripción inicial") }
    val textoNombre = remember {mutableStateOf("Nombre Usuario")}
    NavHost(navController = navHostController, startDestination = "initial") {
        composable("initial") {
            InitialScreen(
                auth = auth,
                navigateToLogin = { navHostController.navigate("login") },
                navigateToSignUp = { navHostController.navigate("register") },
            )
        }
        composable("login") { backStackEntry ->
            val parentEntry = remember(backStackEntry) {
                navHostController.getBackStackEntry("login")
            }
            val cargaDatosUsuario: CargaDatos = viewModel(parentEntry)
            PantallaPrincipal(
                navHostController,
                cargaDatosUsuario = cargaDatosUsuario,
                navigateToPerfil = { navHostController.navigate("perfil") },
                navigateToMensajes = { navHostController.navigate("mensajes") },
                navigateToNotificaciones = { navHostController.navigate("notificaciones") },
                navigateToBuscar = { navHostController.navigate("buscar") },
                navigateToInicio = { navHostController.navigate("initial") }
            )
        }
        composable("register") {
            RegisterScreen(auth, db)
        }
        composable("perfil") {
                backStackEntry ->
            val parentEntry = remember(backStackEntry) {
                navHostController.getBackStackEntry("login")
            }
            val cargaDatosUsuario: CargaDatos = viewModel(parentEntry)
            PantallaPerfil(
                navHostController,
                navigateToMensajes = { navHostController.navigate("mensajes") },
                navigateToNotificaciones = { navHostController.navigate("notificaciones") },
                navigateToPrincipal = { navHostController.navigate("login") },
                navigateToBuscar = { navHostController.navigate("buscar") },
                navigateToAjustes = { navHostController.navigate("ajustes") },
                textoDescripcion = textoDescripcion,
                cargaDatos = cargaDatosUsuario,
                textoNombre = textoNombre
            )
        }
        composable("mensajes") {
            PantallaMensajes(
                navHostController,
                navigateToPerfil = { navHostController.navigate("perfil") },
                navigateToNotificaciones = { navHostController.navigate("notificaciones") },
                navigateToPrincipal = { navHostController.navigate("login") },
                navigateToBuscar = { navHostController.navigate("buscar") }
            )
        }
        composable("notificaciones") {
            PantallaNotificaciones(
                navHostController,
                navigateToMensajes = { navHostController.navigate("mensajes") },
                navigateToPerfil = { navHostController.navigate("perfil") },
                navigateToPrincipal = { navHostController.navigate("login") },
                navigateToBuscar = { navHostController.navigate("buscar") }
            )
        }
        composable("buscar") {
                backStackEntry ->
            val parentEntry = remember(backStackEntry) {
                navHostController.getBackStackEntry("login")
            }
            val cargaDatosUsuario: CargaDatos = viewModel(parentEntry)
            PantallaBuscar(
                navHostController,
                navigateToPerfil = { navHostController.navigate("perfil") },
                navigateToNotificaciones = { navHostController.navigate("notificaciones") },
                navigateToPrincipal = { navHostController.navigate("login") },
                navigateToMensajes = { navHostController.navigate("mensajes") },
                cargaDatosUsuario = cargaDatosUsuario
            )
        }
        composable("ajustes") { backStackEntry ->
            val parentEntry = remember(backStackEntry) {
                navHostController.getBackStackEntry("login")
            }
            val cargaDatosUsuario: CargaDatos = viewModel(parentEntry)
            PantallaAjsutes(
                navHostController,
                texto = textoDescripcion,
                cargaDatosUsuario = cargaDatosUsuario
            )
        }
        composable("perfilClicado") {
                backStackEntry ->
            val parentEntry = remember(backStackEntry) {
                navHostController.getBackStackEntry("login")
            }
            val cargaDatosUsuario: CargaDatos = viewModel(parentEntry)
            PantallaPerfil(
                navHostController,
                navigateToMensajes = { navHostController.navigate("mensajes") },
                navigateToNotificaciones = { navHostController.navigate("notificaciones") },
                navigateToPrincipal = { navHostController.navigate("login") },
                navigateToBuscar = { navHostController.navigate("buscar") },
                navigateToAjustes = { navHostController.navigate("ajustes") },
                textoDescripcion = textoDescripcion,
                cargaDatos = cargaDatosUsuario,
                textoNombre = textoNombre
            )
        }
    }
}
