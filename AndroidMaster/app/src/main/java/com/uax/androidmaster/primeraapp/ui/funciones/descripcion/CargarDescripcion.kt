package com.uax.androidmaster.primeraapp.ui.funciones.descripcion

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

fun cargarDescripcionPerfil(db: FirebaseFirestore, onResult: (String?) -> Unit) {
    val uid = FirebaseAuth.getInstance().currentUser?.uid ?: return

    db.collection("perfiles")
        .whereEqualTo("usuarioId", uid)
        .get()
        .addOnSuccessListener { result ->
            val descripcion = result.documents.firstOrNull()?.getString("descripcion")
            onResult(descripcion)
        }
}
