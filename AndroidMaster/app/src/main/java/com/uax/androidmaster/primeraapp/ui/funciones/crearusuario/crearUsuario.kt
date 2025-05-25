package com.uax.androidmaster.primeraapp.ui.funciones.crearusuario

import androidx.compose.runtime.MutableState
import com.google.firebase.auth.FirebaseAuth
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
    onSuccess: () -> Unit = {},
    onError: (Exception) -> Unit = {}
) {
    val uid = FirebaseAuth.getInstance().currentUser?.uid ?: return

    val formato = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val fechaNacimiento = try {
        formato.parse(nacimiento?.value ?: "") ?: Date()
    } catch (e: Exception) {
        Date()
    }

    val usuario = Usuario(
        nombre = nombre?.value ?: "",
        apellido = apellido?.value ?: "",
        nacimiento = fechaNacimiento,
        contrasena = pass?.value ?: "",
        correo = correo?.value ?: ""
    )

    db.collection(ConstantesFirestore.BBDD_USUARIOS)
        .document(uid) // ← usamos UID como ID del documento
        .set(usuario)
        .addOnSuccessListener { onSuccess() }
        .addOnFailureListener { onError(it) }
}