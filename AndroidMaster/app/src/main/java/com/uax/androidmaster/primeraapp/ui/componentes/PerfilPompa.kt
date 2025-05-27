package com.uax.androidmaster.primeraapp.ui.componentes

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.uax.androidmaster.R
import com.uax.androidmaster.primeraapp.ui.theme.Black
import com.uax.androidmaster.primeraapp.ui.theme.Transparent

@Composable
fun PerfilPompa(nombreUsuario: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(1.dp)
            .border(2.dp, color = Black, shape = RoundedCornerShape(8.dp))
            .padding(10.dp) // Espacio interno dentro del borde
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Image(
                painter = painterResource(id = R.drawable.sportlink),
                contentDescription = "Pompa perfil",
                modifier = Modifier
                    .size(64.dp) // Tamaño fijo para la imagen
            )

            Spacer(modifier = Modifier.width(12.dp)) // Espacio entre imagen y texto

            Text(
                text = nombreUsuario,
                modifier = Modifier.align(Alignment.CenterVertically)
            )
        }
    }
}