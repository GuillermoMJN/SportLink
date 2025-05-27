package com.uax.androidmaster.primeraapp.ui.componentes

import android.R.attr.onClick
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.uax.androidmaster.R
import com.uax.androidmaster.primeraapp.ui.theme.Black

@Composable
fun PerfilPompa(nombreUsuario: String,  onClick: () -> Unit = {}) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(1.dp)
            .border(2.dp, color = Black, shape = RoundedCornerShape(8.dp))
            .padding(10.dp)
            .clickable { onClick() } // Espacio interno dentro del borde
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