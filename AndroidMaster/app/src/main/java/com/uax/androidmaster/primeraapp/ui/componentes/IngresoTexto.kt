package com.uax.androidmaster.primeraapp.ui.componentes

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.uax.androidmaster.primeraapp.ui.theme.Blue100
import com.uax.androidmaster.primeraapp.ui.theme.Transparent
import com.uax.androidmaster.primeraapp.ui.theme.White

@Composable
fun IngresarTexto(texto: String, padding: Int) {
    TextField(
        value = "",
        onValueChange = { },
        label = { Text(texto.toString()) },
        colors = TextFieldDefaults.colors(
            White,
            focusedLabelColor = Blue100,
            focusedIndicatorColor = Transparent,
            unfocusedIndicatorColor = Transparent
        ),
        shape = RoundedCornerShape(16.dp),// Bordes redondeados
        modifier = Modifier
            .fillMaxWidth()
            .padding(padding.dp)
            .padding(horizontal = 20.dp)
    )
}