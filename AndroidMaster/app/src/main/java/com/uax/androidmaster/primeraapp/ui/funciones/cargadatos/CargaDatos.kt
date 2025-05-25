package com.uax.androidmaster.primeraapp.ui.funciones.cargadatos

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.firestore
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import com.uax.androidmaster.primeraapp.ui.constantes.ConstantesFirestore

class CargaDatos : ViewModel() {

    private val db = FirebaseFirestore.getInstance()
    private val auth = FirebaseAuth.getInstance()

    // UID del usuario actual
    private var uid: String? = null

    // Estados individuales
    private val _nombre = mutableStateOf("")
    val nombre: State<String> = _nombre

    private val _descripcion = mutableStateOf("")
    val descripcion: State<String> = _descripcion

    private val _fotoUrl = mutableStateOf("")
    val fotoUrl: State<String> = _fotoUrl

    fun cargarUID() {
        uid = auth.currentUser?.uid
    }

    fun cargarNombre() {
        uid?.let { userId ->
            db.collection("usuarios").document(userId)
                .get()
                .addOnSuccessListener { doc ->
                    _nombre.value = doc.getString("nombre") ?: ""
                }
        }
    }

    fun cargarDescripcion() {
        uid?.let { userId ->
            db.collection("usuarios").document(userId)
                .get()
                .addOnSuccessListener { doc ->
                    _descripcion.value = doc.getString("descripcion") ?: ""
                }
        }
    }

    fun cargarFotoUrl() {
        uid?.let { userId ->
            db.collection("usuarios").document(userId)
                .get()
                .addOnSuccessListener { doc ->
                    _fotoUrl.value = doc.getString("fotoUrl") ?: ""
                }
        }
    }

    fun actualizarDescripcion(nuevaDescripcion: String) {
        uid?.let { userId ->
            db.collection("perfiles")
                .whereEqualTo("usuarioId", userId)
                .get()
                .addOnSuccessListener { documents ->
                    for (document in documents) {
                        db.collection("perfiles").document(document.id)
                            .update("descripcion", nuevaDescripcion)
                            .addOnSuccessListener {
                                _descripcion.value = nuevaDescripcion
                            }
                            .addOnFailureListener {
                                Log.e("Firestore", "Error al actualizar descripción", it)
                            }
                    }
                }
                .addOnFailureListener {
                    Log.e("Firestore", "Error al buscar perfil", it)
                }
        }
    }
}