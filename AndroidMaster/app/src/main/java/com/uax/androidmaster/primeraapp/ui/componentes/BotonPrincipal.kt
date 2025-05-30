package com.uax.androidmaster.primeraapp.ui.componentes

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.uax.androidmaster.primeraapp.ui.theme.Blue100
import com.uax.androidmaster.primeraapp.ui.theme.White

@Composable
fun BotonPrincipal(onClick: () -> Unit, texto: @Composable String, colorFondo: Color, colorLetra: Color, padding: Int? = 0) {
    Button(
        onClick = onClick,
        modifier = Modifier.padding(padding?.dp ?: 0.dp),
        colors = ButtonDefaults.buttonColors(containerColor = colorFondo)
    ) {
        Text(texto, fontSize = 20.sp, color = colorLetra)
    }
}
