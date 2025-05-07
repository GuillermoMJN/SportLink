package com.uax.androidmaster.primeraapp.ui.initial

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.google.firebase.auth.FirebaseAuth
import com.uax.androidmaster.primeraapp.ui.theme.Blue100
import com.uax.androidmaster.primeraapp.ui.theme.Transparent
import com.uax.androidmaster.primeraapp.ui.theme.White

@Composable
fun RegisterScreen(auth: FirebaseAuth, navHostController: NavHostController) {
    Scaffold { innerPadding ->
        RegisterContent(
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun RegisterContent(modifier: Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    listOf(White, Blue100), startY = 0f, endY = 2000f
                )
            ), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Esto es una prueba")
    }

}
