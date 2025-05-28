package com.uax.androidmaster.primeraapp.ui.funciones.crearperfil

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.uax.androidmaster.primeraapp.ui.constantes.ConstantesFirestore

fun crearPerfil(db: FirebaseFirestore, descripcion: String, urlFoto: String) {
    val uid = FirebaseAuth.getInstance().currentUser?.uid ?: return

    val perfilData = mapOf(
        ConstantesFirestore.BBDD_DESCRIPCION to descripcion,
        ConstantesFirestore.BBDD_FOTO to urlFoto,
        ConstantesFirestore.BBDD_USUARIOID to uid // ← vinculación con el documento de usuario
    )

    db.collection(ConstantesFirestore.BBDD_PERFILES).add(perfilData)
}