package com.uax.androidmaster.primeraapp.ui.componentes

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.uax.androidmaster.primeraapp.ui.theme.Black
import com.uax.androidmaster.primeraapp.ui.theme.Blue100
import com.uax.androidmaster.primeraapp.ui.theme.Transparent
import com.uax.androidmaster.primeraapp.ui.theme.White

@Composable
fun IngresarTexto(texto: String, padding: Int, tipo: String? = null) {
    var valor by remember { mutableStateOf("") }
    TextField(
        value = valor,
        onValueChange = { valor = it },
        label = { Text(texto.toString(), color = Black) },
        colors = TextFieldDefaults.colors(
            White,
            focusedLabelColor = Blue100,
            focusedIndicatorColor = Transparent,
            unfocusedIndicatorColor = Transparent
        ),
        visualTransformation = when(tipo){
            "pass" -> PasswordVisualTransformation()
            else-> VisualTransformation.None
        },
        keyboardOptions = when(tipo){
            "email" -> KeyboardOptions(keyboardType = KeyboardType.Email)
            "pass" -> KeyboardOptions(keyboardType = KeyboardType.Password)
            "fecha" -> KeyboardOptions(keyboardType = KeyboardType.Number)
            else -> KeyboardOptions.Default
        },
        shape = RoundedCornerShape(16.dp),// Bordes redondeados
        modifier = Modifier
            .fillMaxWidth()
            .padding(padding.dp)
            .padding(horizontal = 20.dp)
    )
}
