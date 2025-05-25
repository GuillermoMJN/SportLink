package com.uax.androidmaster.primeraapp.ui.funciones.crearperfil

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.uax.androidmaster.primeraapp.ui.constantes.ConstantesFirestore

fun crearPerfil(db: FirebaseFirestore, descripcion: String, urlFoto: String) {
    val uid = FirebaseAuth.getInstance().currentUser?.uid ?: return

    val perfilData = mapOf(
        "descripcion" to descripcion,
        "foto" to urlFoto,
        "usuarioId" to uid // ← vinculación con el documento de usuario
    )

    db.collection(ConstantesFirestore.BBDD_PERFILES).add(perfilData)
}