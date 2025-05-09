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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.google.firebase.auth.FirebaseAuth
import com.uax.androidmaster.primeraapp.ui.componentes.IngresarTexto
import com.uax.androidmaster.primeraapp.ui.theme.Black
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
        Spacer(modifier = Modifier.weight(0.1f))
        Text(
            "Registrate en Sportlink",
            color = Black,
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold
        )
        IngresarTexto("Nombre", 10)
        IngresarTexto("Apellido", 10)
        IngresarTexto("Fecha de nacimiento", 10)
        IngresarTexto("Contraseña", 10)
        IngresarTexto("Correo electrónico", 10)
        Spacer(modifier = Modifier.weight(1f))
    }
}
