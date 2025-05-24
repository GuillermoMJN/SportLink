package com.uax.androidmaster.primeraapp.ui.componentes

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.uax.androidmaster.R
import com.uax.androidmaster.primeraapp.ui.theme.Transparent
import com.uax.androidmaster.primeraapp.ui.theme.White

@Composable
fun Imagen() {
    var liked by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .background(
                White
            )
            ,
        horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Top
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .background(Transparent).padding(horizontal = 10.dp),
            contentAlignment = Alignment.Center
        ) {
            AsyncImage(
                model="https://www.bing.com/images/search?q=imagen%20firebase&FORM=IQFRBA&id=0AA715760A142A8D125756F86BC6CFDD3194E35F",
                contentDescription = ""
            )
            Image(
                painter = painterResource(id = R.drawable.sportlink),
                contentDescription = "",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }
        Row() {
            IconButton(onClick = {
                liked = !liked
            }) {
                Icon(
                    painter = painterResource(
                        id = if (liked) R.drawable.like_blue else R.drawable.like_white
                    ),
                    contentDescription = if (liked) "Quitar like" else "Dar like"
                )
                Icon(
                    painter = painterResource(
                        id = R.drawable.like_white
                    ),
                    contentDescription = "Comentarios"
                )
            }

        }
        Text("Comentario")
    }
}