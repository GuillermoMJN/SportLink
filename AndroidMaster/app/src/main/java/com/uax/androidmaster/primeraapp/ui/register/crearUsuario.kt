package com.uax.androidmaster.primeraapp.ui.register

import androidx.compose.runtime.MutableState
import com.google.firebase.firestore.FirebaseFirestore
import com.uax.androidmaster.primeraapp.ui.constantes.ConstantesFirestore
import com.uax.androidmaster.primeraapp.ui.model.Usuario
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun crearUsuario(
    db: FirebaseFirestore,
    nombre: MutableState<String?>? = null,
    apellido: MutableState<String?>? = null,
    nacimiento: MutableState<String?>? = null,
    pass: MutableState<String?>? = null,
    correo: MutableState<String?>? = null,
) {

    val formato = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val fechaNacimiento = try {
        formato.parse(nacimiento?.value ?: "") ?: Date() // valor por defecto
    } catch (e: Exception) {
        Date() // si falla el parseo
    }

    // Validaciones básicas opcionales
    val nombre = nombre?.value ?: ""
    val apellido = apellido?.value ?: ""
    val nacimiento = nacimiento?.value ?: ""
    val contrasena = pass?.value ?: ""
    val correo = correo?.value ?: ""

    val usuario: Usuario = Usuario(
        nombre = nombre,
        apellido = apellido,
        nacimiento = fechaNacimiento,
        contrasena = contrasena,
        correo = correo
    )

    db.collection(ConstantesFirestore.BBDD_USUARIOS).add(usuario)
}