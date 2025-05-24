package com.uax.androidmaster.primeraapp.ui.initial

import com.google.firebase.auth.FirebaseAuth
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.uax.androidmaster.R
import com.uax.androidmaster.primeraapp.ui.componentes.BotonPrincipal
import com.uax.androidmaster.primeraapp.ui.componentes.IngresarTexto
import com.uax.androidmaster.primeraapp.ui.register.crearUsuario
import com.uax.androidmaster.primeraapp.ui.theme.*
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore

@Composable
fun InitialScreen(
    auth: FirebaseAuth,
    navigateToLogin: () -> Unit = {},
    navigateToSignUp: () -> Unit = {},
) {
    Scaffold { innerPadding ->
        ContentInitialScreen(
            auth = auth,
            navigateToLogin = navigateToLogin,
            navigateToSignUp = navigateToSignUp,
            modifier = Modifier.padding(innerPadding),
        )
    }
}

@Composable
fun ContentInitialScreen(
    auth: FirebaseAuth,
    navigateToLogin: () -> Unit = {},
    navigateToSignUp: () -> Unit = {},
    modifier: Modifier = Modifier,
) {
    val usuario = remember { mutableStateOf<String?>(null) }
    val pass = remember { mutableStateOf<String?>(null) }
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
        IngresarTexto("Usuario", 8, contenido = usuario)
        IngresarTexto("Contraseña", 8, contenido = pass)
        Spacer(modifier = Modifier.weight(0.5f))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            BotonPrincipal(onClick = {
                auth.signInWithEmailAndPassword(usuario.value ?: "", pass.value ?: "")
                    .addOnCompleteListener {
                        if (it.isSuccessful) {
                            navigateToLogin()
                            Log.i("Entro", "SI")
                        } else {
                            Log.i("ERROR", "NO")
                        }
                    }
            }, "Ingresar")
            BotonPrincipal(onClick = { navigateToSignUp() }, "Registrarse")
        }
        Spacer(modifier = Modifier.weight(1f))
    }
}

