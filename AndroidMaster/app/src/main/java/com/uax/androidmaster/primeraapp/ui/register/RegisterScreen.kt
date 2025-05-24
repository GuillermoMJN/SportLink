package com.uax.androidmaster.primeraapp.ui.initial

import android.util.Log
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.uax.androidmaster.primeraapp.ui.componentes.BotonPrincipal
import com.uax.androidmaster.primeraapp.ui.componentes.IngresarTexto
import com.uax.androidmaster.primeraapp.ui.componentes.IngresoFecha
import com.uax.androidmaster.primeraapp.ui.register.crearUsuario
import com.uax.androidmaster.primeraapp.ui.theme.Black
import com.uax.androidmaster.primeraapp.ui.theme.Blue100
import com.uax.androidmaster.primeraapp.ui.theme.Transparent
import com.uax.androidmaster.primeraapp.ui.theme.White
import java.util.Date

@Composable
fun RegisterScreen(auth: FirebaseAuth, db: FirebaseFirestore) {
    Scaffold { innerPadding ->
        RegisterContent(
            modifier = Modifier.padding(innerPadding),
            auth = auth,
            db = db
        )
    }
}

@Composable
fun RegisterContent(modifier: Modifier, auth: FirebaseAuth, db: FirebaseFirestore) {
    val usuario = remember { mutableStateOf<String?>(null) }
    val pass = remember { mutableStateOf<String?>(null) }
    val apellido = remember { mutableStateOf<String?>(null) }
    val fecha = remember { mutableStateOf<String?>(null) }
    val correo = remember { mutableStateOf<String?>(null) }

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
        IngresarTexto("Nombre", 10, contenido = usuario)
        IngresarTexto("Apellido", 10, contenido = apellido)
        IngresoFecha("Fecha Nacimineto", 30, 10, contenido = fecha)
        IngresarTexto("Contraseña", 10, "pass", contenido = pass)
        IngresarTexto("Correo electrónico", 10, contenido = correo)
        Spacer(modifier = Modifier.weight(1f))

        BotonPrincipal(onClick = {
            auth.createUserWithEmailAndPassword(correo.value ?: "", pass.value ?: "")
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.i("Registrado", "SI")
                        crearUsuario(
                            db = db,
                            nombre = usuario,
                            apellido = apellido,
                            nacimiento = fecha,
                            pass = pass,
                            correo = correo
                        )
                    } else {
                        Log.i("No registrado", "NO")
                    }
                }
        }, "Registrarse")
    }
}
