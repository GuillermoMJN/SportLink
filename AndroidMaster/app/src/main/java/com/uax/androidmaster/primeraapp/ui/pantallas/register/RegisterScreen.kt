package com.uax.androidmaster.primeraapp.ui.initial

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.uax.androidmaster.primeraapp.ui.componentes.BotonPrincipal
import com.uax.androidmaster.primeraapp.ui.componentes.IngresarTexto
import com.uax.androidmaster.primeraapp.ui.componentes.IngresoFecha
import com.uax.androidmaster.primeraapp.ui.funciones.crearperfil.crearPerfil
import com.uax.androidmaster.primeraapp.ui.funciones.crearusuario.crearUsuario
import com.uax.androidmaster.primeraapp.ui.theme.Black
import com.uax.androidmaster.primeraapp.ui.theme.Blue100
import com.uax.androidmaster.primeraapp.ui.theme.White

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
            val nombreInput = usuario.value
            val apellidoInput = apellido.value
            val fechaInput = fecha.value
            val passInput = pass.value
            val correoInput = correo.value

            if (nombreInput.isNullOrBlank() ||
                apellidoInput.isNullOrBlank() ||
                fechaInput.isNullOrBlank() ||
                passInput.isNullOrBlank() ||
                correoInput.isNullOrBlank()
            ) {
                Toast.makeText(context, "Por favor, rellene todos los campos", Toast.LENGTH_SHORT).show()
            } else {
                auth.createUserWithEmailAndPassword(correoInput, passInput)
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
                            crearPerfil(
                                db = db,
                                "Esta es una prueba",
                                ""
                            )
                            Toast.makeText(context, "Usuario registrado", Toast.LENGTH_SHORT).show()
                            usuario.value = ""
                            apellido.value = ""
                            fecha.value = ""
                            pass.value = ""
                            correo.value = ""
                        } else {
                            Log.i("No registrado", "NO")
                        }
                    }
            }
        }, "Registrarse", White, Blue100)
    }
}
