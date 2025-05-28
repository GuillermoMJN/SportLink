package com.uax.androidmaster.primeraapp.ui.funciones.cargadatos

import androidx.compose.runtime.mutableStateOf

import androidx.lifecycle.ViewModel

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

import androidx.compose.runtime.State
import com.uax.androidmaster.primeraapp.ui.constantes.ConstantesFirestore

class CargaDatos : ViewModel() {

    private val db = FirebaseFirestore.getInstance()
    private val auth = FirebaseAuth.getInstance()

    var uid: String? = null
        private set

    private val _nombre = mutableStateOf("")
    val nombre: State<String> = _nombre

    private val _descripcion = mutableStateOf("")
    val descripcion: State<String> = _descripcion

    private val _fotoUrl = mutableStateOf("")
    val fotoUrl: State<String> = _fotoUrl

    init {
        cargarUID()
        cargarNombre()
        cargarDescripcion()
        cargarFotoUrl()
    }

    fun cargarUID() {
        uid = auth.currentUser?.uid
    }

    fun cargarNombre() {
        uid?.let { userId ->
            db.collection(ConstantesFirestore.BBDD_USUARIOS).document(userId)
                .get()
                .addOnSuccessListener { doc ->
                    _nombre.value = doc.getString(ConstantesFirestore.BBDD_NOMBRE) ?: ""
                }
        }
    }

    fun cargarDescripcion() {
        uid?.let { userId ->
            db.collection(ConstantesFirestore.BBDD_PERFILES)
                .whereEqualTo(ConstantesFirestore.BBDD_USUARIOID, userId)
                .get()
                .addOnSuccessListener { documents ->
                    if (!documents.isEmpty) {
                        _descripcion.value = documents.first().getString("descripcion") ?: ""
                    }
                }
        }
    }

    fun cargarFotoUrl() {
        uid?.let { userId ->
            db.collection(ConstantesFirestore.BBDD_USUARIOS).document(userId)
                .get()
                .addOnSuccessListener { doc ->
                    _fotoUrl.value = doc.getString("fotoUrl") ?: ""
                }
        }
    }

    fun actualizarDescripcion(nuevaDescripcion: String) {
        uid?.let { userId ->
            db.collection(ConstantesFirestore.BBDD_PERFILES)
                .whereEqualTo(ConstantesFirestore.BBDD_USUARIOID, userId)
                .get()
                .addOnSuccessListener { documents ->
                    for (document in documents) {
                        db.collection(ConstantesFirestore.BBDD_PERFILES).document(document.id)
                            .update(ConstantesFirestore.BBDD_DESCRIPCION, nuevaDescripcion)
                            .addOnSuccessListener {
                                _descripcion.value = nuevaDescripcion
                            }
                    }
                }
        }
    }

    fun actualizarNombre(nuevoNombre: String) {
        uid?.let { userId ->
            db.collection("usuarios").document(userId)
                .update("nombre", nuevoNombre)
                .addOnSuccessListener {
                    _nombre.value = nuevoNombre
                }
        }
    }
}
