package com.uax.androidmaster.primeraapp

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.google.firebase.auth.FirebaseAuth
import com.uax.androidmaster.primeraapp.ui.initial.InitialScreen
import com.uax.androidmaster.primeraapp.ui.perfil.PantallaPerfil
import com.uax.androidmaster.primeraapp.ui.initial.RegisterScreen
import com.uax.androidmaster.primeraapp.ui.toolBar.CustomToolBar

@Composable
fun NavigationWrapper(navHostController: NavHostController, auth: FirebaseAuth) {
    NavHost(navController = navHostController, startDestination = "initial") {
        composable("initial") {
            InitialScreen(
                navigateToLogin = { navHostController.navigate("login") },
                navigateToSignUp = { navHostController.navigate("register") }
            )
        }
        composable("login") {
            PantallaPerfil(
                navHostController
            )
        }
        composable("register") {
            RegisterScreen(auth, navHostController)
        }
    }
}
