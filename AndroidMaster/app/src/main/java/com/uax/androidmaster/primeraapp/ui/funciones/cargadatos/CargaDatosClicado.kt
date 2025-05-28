package com.uax.androidmaster.primeraapp.ui.funciones.cargadatos

import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore

import androidx.compose.runtime.mutableStateOf

import com.google.firebase.auth.FirebaseAuth

import androidx.compose.runtime.State
import com.uax.androidmaster.primeraapp.ui.constantes.ConstantesFirestore

class CargaDatosClicado(val uid: String) {

    private val db = FirebaseFirestore.getInstance()

    private val _nombre = mutableStateOf("")
    val nombre: State<String> = _nombre

    private val _descripcion = mutableStateOf("")
    val descripcion: State<String> = _descripcion

    private val _fotoUrl = mutableStateOf("")
    val fotoUrl: State<String> = _fotoUrl

    init {
        cargarDatos()
    }

    private fun cargarDatos() {
        db.collection(ConstantesFirestore.BBDD_USUARIOS).document(uid)
            .get()
            .addOnSuccessListener { doc ->
                _nombre.value = doc.getString(ConstantesFirestore.BBDD_NOMBRE) ?: "Sin nombre"
                _fotoUrl.value = doc.getString(ConstantesFirestore.BBDD_USUARIOID) ?: ""
            }

        db.collection(ConstantesFirestore.BBDD_PERFILES)
            .whereEqualTo(ConstantesFirestore.BBDD_USUARIOID, uid)
            .get()
            .addOnSuccessListener { documents ->
                if (!documents.isEmpty) {
                    _descripcion.value = documents.first().getString(ConstantesFirestore.BBDD_DESCRIPCION) ?: "Deportista"
                }
            }
    }
}