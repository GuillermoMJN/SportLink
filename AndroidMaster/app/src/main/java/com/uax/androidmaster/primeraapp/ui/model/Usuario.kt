package com.uax.androidmaster.primeraapp.ui.model

import java.util.Date

data class Usuario (
    val nombre: String,
    val apellido: String,
    val nacimiento: Date,
    val contrasena: String,
    val correo: String
){

}