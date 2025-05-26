package com.uax.androidmaster.primeraapp.ui.componentes

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.uax.androidmaster.primeraapp.ui.theme.Blue100
import com.uax.androidmaster.primeraapp.ui.theme.White

@Composable
fun BotonPrincipal(onClick: () -> Unit, texto: @Composable String) {
    Button(
        onClick = onClick,
        modifier = Modifier.padding(horizontal = 10.dp),
        colors = ButtonDefaults.buttonColors(containerColor = White)
    ) {
        Text(texto, fontSize = 20.sp, color = Blue100)
    }
}