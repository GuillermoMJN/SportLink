package com.uax.androidmaster.primeraapp.ui.funciones.descripcion

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

fun guardarDescripcion(db: FirebaseFirestore, nuevaDescripcion: String, onSuccess: () -> Unit, onError: (Exception) -> Unit) {
    val uid = FirebaseAuth.getInstance().currentUser?.uid ?: return

    val perfilRef = db.collection("perfiles").whereEqualTo("usuarioId", uid)

    perfilRef.get()
        .addOnSuccessListener { documents ->
            if (!documents.isEmpty) {
                // Ya existe un perfil, actualizamos el primero encontrado
                val docId = documents.first().id
                db.collection("perfiles").document(docId)
                    .update("descripcion", nuevaDescripcion)
                    .addOnSuccessListener { onSuccess() }
                    .addOnFailureListener { onError(it) }
            } else {
                // No hay perfil todavía, lo creamos
                val nuevoPerfil = mapOf(
                    "usuarioId" to uid,
                    "descripcion" to nuevaDescripcion,
                    "foto" to "" // vacío por ahora, opcional
                )
                db.collection("perfiles")
                    .add(nuevoPerfil)
                    .addOnSuccessListener { onSuccess() }
                    .addOnFailureListener { onError(it) }
            }
        }
        .addOnFailureListener { onError(it) }
}