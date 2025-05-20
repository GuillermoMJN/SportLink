package com.uax.androidmaster.primeraapp

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.google.firebase.auth.FirebaseAuth
import com.uax.androidmaster.primeraapp.ui.buscar.PantallaBuscar
import com.uax.androidmaster.primeraapp.ui.initial.InitialScreen
import com.uax.androidmaster.primeraapp.ui.principal.PantallaPrincipal
import com.uax.androidmaster.primeraapp.ui.initial.RegisterScreen
import com.uax.androidmaster.primeraapp.ui.mensajes.PantallaMensajes
import com.uax.androidmaster.primeraapp.ui.notificaciones.PantallaNotificaciones
import com.uax.androidmaster.primeraapp.ui.perfil.PantallaPerfil

@Composable
fun NavigationWrapper(navHostController: NavHostController, auth: FirebaseAuth) {
    NavHost(navController = navHostController, startDestination = "initial") {
        composable("initial") {
            InitialScreen(
                auth = auth,
                navigateToLogin = { navHostController.navigate("login") },
                navigateToSignUp = { navHostController.navigate("register") }
            )
        }
        composable("login") {
            PantallaPrincipal(
                navHostController,
                navigateToPerfil = { navHostController.navigate("perfil") },
                navigateToMensajes = { navHostController.navigate("mensajes") },
                navigateToNotificaciones = { navHostController.navigate("notificaciones") },
                navigateToBuscar = {navHostController.navigate("buscar")}
            )
        }
        composable("register") {
            RegisterScreen(auth, navHostController)
        }
        composable("perfil") {
            PantallaPerfil(
                navHostController,
                navigateToMensajes = { navHostController.navigate("mensajes") },
                navigateToNotificaciones = { navHostController.navigate("notificaciones") },
                navigateToPrincipal = { navHostController.navigate("login")},
                navigateToBuscar = {navHostController.navigate("buscar")}
            )
        }
        composable("mensajes") {
            PantallaMensajes(
                navHostController,
                navigateToPerfil = { navHostController.navigate("perfil") },
                navigateToNotificaciones = { navHostController.navigate("notificaciones")},
                navigateToPrincipal = { navHostController.navigate("login")},
                navigateToBuscar = {navHostController.navigate("buscar")}
            )
        }
        composable("notificaciones") {
            PantallaNotificaciones(
                navHostController,
                navigateToMensajes = { navHostController.navigate("mensajes") },
                navigateToPerfil = { navHostController.navigate("perfil") },
                navigateToPrincipal = { navHostController.navigate("login")},
                navigateToBuscar = {navHostController.navigate("buscar")}
            )
        }
        composable("buscar") {
            PantallaBuscar(
                navHostController,
                navigateToPerfil = {navHostController.navigate("perfil")},
                navigateToNotificaciones = { navHostController.navigate("notificaciones") },
                navigateToPrincipal = { navHostController.navigate("login")},
                navigateToMensajes = { navHostController.navigate("mensajes") }
            )
        }
    }
}
