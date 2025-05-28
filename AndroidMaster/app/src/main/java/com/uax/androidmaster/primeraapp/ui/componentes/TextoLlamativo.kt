package com.uax.androidmaster.primeraapp.ui.componentes


import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.uax.androidmaster.primeraapp.ui.theme.BlueTitle
import androidx.compose.ui.text.style.TextAlign
import com.uax.androidmaster.primeraapp.ui.theme.Grey

@Composable
fun TextoLlamativo() {
    Text(
        text = "¡Próximamente!",
        fontSize = 52.sp,
        fontWeight = FontWeight.Bold,
        color = BlueTitle, // Azul vibrante
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        textAlign = TextAlign.Center,
        style = TextStyle(
            shadow = Shadow(
                color = Grey,
                offset = Offset(2f, 2f),
                blurRadius = 4f
            ),
            letterSpacing = 2.sp
        )
    )
}
