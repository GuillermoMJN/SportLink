package com.uax.androidmaster.primeraapp.ui.componentes

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.uax.androidmaster.primeraapp.ui.constantes.FuenteSport
import com.uax.androidmaster.primeraapp.ui.theme.Black
import com.uax.androidmaster.primeraapp.ui.theme.White

@Composable
fun TextoInitialScreen(texto: String){
    Text(
        texto,
        color = White,
        fontSize = 80.sp,
        fontWeight = FontWeight.Normal,
        style = TextStyle(
            shadow = Shadow(
                color = Black,
                offset = Offset(2f,2f),
                blurRadius = 4f
            )
        ),
        fontFamily = FuenteSport
    )
}
