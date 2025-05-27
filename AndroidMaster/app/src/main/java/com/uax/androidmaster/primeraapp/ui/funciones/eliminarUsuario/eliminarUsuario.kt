package com.uax.androidmaster.primeraapp.ui.funciones.eliminarUsuario

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

fun eliminarUsuario(
    onSuccess: () -> Unit,
    onError: (String) -> Unit
) {
    val user = FirebaseAuth.getInstance().currentUser
    val db = FirebaseFirestore.getInstance()

    if (user != null) {
        val userId = user.uid

        // 1. Eliminar documentos en Firestore
        val batch = db.batch()

        val perfilRef = db.collection("perfiles").document(userId)
        val usuarioRef = db.collection("usuarios").document(userId)

        batch.delete(perfilRef)
        batch.delete(usuarioRef)

        batch.commit().addOnSuccessListener {
            // 2. Eliminar el usuario de Firebase Auth
            user.delete()
                .addOnSuccessListener {
                    onSuccess()
                }
                .addOnFailureListener { e ->
                    onError("Error al eliminar usuario de Auth: ${e.message}")
                }
        }.addOnFailureListener { e ->
            onError("Error al borrar datos de Firestore: ${e.message}")
        }
    } else {
        onError("No hay un usuario autenticado.")
    }
}