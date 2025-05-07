package com.uax.androidmaster.primeraapp.ui.initial

import android.R.attr.onClick
import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ComposeCompilerApi
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.uax.androidmaster.R
import com.uax.androidmaster.primeraapp.ui.componentes.BotonPrincipal
import com.uax.androidmaster.primeraapp.ui.componentes.IngresarTexto
import com.uax.androidmaster.primeraapp.ui.theme.*

@Preview
@Composable
fun InitialScreen(navigateToLogin: () -> Unit = {}, navigateToSignUp: () -> Unit = {}) {
    Scaffold { innerPadding ->
        ContentInitialScreen(
            navigateToLogin = navigateToLogin,
            navigateToSignUp = navigateToSignUp,
            modifier = Modifier.padding(innerPadding)
        ) }
}

@Composable
fun ContentInitialScreen(navigateToLogin: () -> Unit = {}, navigateToSignUp: () -> Unit = {}, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    listOf(White, Blue100), startY = 0f, endY = 2000f
                )
            ), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.weight(1f))
        Image(painter = painterResource(id = R.drawable.sportlink), contentDescription = "")
        Spacer(modifier = Modifier.weight(1f))
        Text(
            "Una nueva experiencia",
            color = White,
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold
        )
        Text("de hacer deporte", color = Blue20, fontSize = 32.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.weight(0.5f))
        IngresarTexto("Usuario", 8)
        IngresarTexto("Contraseña", 8)
        Spacer(modifier = Modifier.weight(0.5f))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            BotonPrincipal(onClick = { navigateToLogin() }, "Ingresar")
            BotonPrincipal(onClick = { navigateToSignUp() }, "Registrarse")
        }
        Spacer(modifier = Modifier.weight(1f))
    }
}

