package com.uax.androidmaster.primeraapp.ui.pantallas.initial

import com.google.firebase.auth.FirebaseAuth
import android.util.Log
import androidx.compose.animation.core.animateOffsetAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.uax.androidmaster.R
import com.uax.androidmaster.primeraapp.ui.componentes.BotonPrincipal
import com.uax.androidmaster.primeraapp.ui.componentes.IngresarTexto
import com.uax.androidmaster.primeraapp.ui.componentes.TextoInitialScreen
import com.uax.androidmaster.primeraapp.ui.theme.*

import android.widget.Toast
import androidx.compose.ui.platform.LocalContext

@Composable
fun InitialScreen(
    auth: FirebaseAuth,
    navigateToLogin: () -> Unit = {},
    navigateToSignUp: () -> Unit = {}
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

    val context = LocalContext.current

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
        Box(
            modifier = Modifier
                .padding(16.dp)
                .shadow(
                    elevation = 12.dp,
                    shape = RoundedCornerShape(16.dp),
                    ambientColor = Black.copy(alpha = 0.2f),
                    spotColor = Black.copy(alpha = 0.3f)
                )
                .clip(RoundedCornerShape(16.dp))
                .background(White)
        ) {
            Image(
                painter = painterResource(
                    id = R.drawable.sportlink
                ),
                contentDescription = "",
                Modifier.size(350.dp)
            )
        }

        Spacer(modifier = Modifier.weight(1f))
        TextoInitialScreen("Una nueva")
        TextoInitialScreen("experiencia de")
        TextoInitialScreen("hacer deporte")
        Spacer(modifier = Modifier.weight(0.5f))
        IngresarTexto("Usuario", 8, contenido = usuario)
        IngresarTexto("Contraseña", 8, contenido = pass, tipo = "pass")
        Spacer(modifier = Modifier.weight(0.5f))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            BotonPrincipal(onClick = {
                val usuarioInput = usuario.value
                val passInput = pass.value

                if (usuarioInput.isNullOrBlank() || passInput.isNullOrBlank()) {
                    Toast.makeText(context, "Por favor, rellene todos los campos", Toast.LENGTH_SHORT).show()
                } else {
                    auth.signInWithEmailAndPassword(usuarioInput, passInput)
                        .addOnCompleteListener {
                            if (it.isSuccessful) {
                                navigateToLogin()
                                Log.i("Entro", "SI")
                            } else {
                                Log.i("ERROR", "NO")
                                Toast.makeText(context, "Error al iniciar sesión", Toast.LENGTH_SHORT).show()
                            }
                        }
                }
            }, "Ingresar", White, Blue100)
            BotonPrincipal(onClick = { navigateToSignUp() }, "Registrarse", White, Blue100)
        }
        Spacer(modifier = Modifier.weight(1f))
    }
}
