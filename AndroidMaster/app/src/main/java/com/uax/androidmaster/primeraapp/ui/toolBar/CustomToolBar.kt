package com.uax.androidmaster.primeraapp.ui.toolBar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.uax.androidmaster.R
import com.uax.androidmaster.primeraapp.ui.theme.Blue60

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomToolBar(navhHostController: NavHostController) {
    TopAppBar(
        title = { Text(text = "SportLink") },
        colors = topAppBarColors(containerColor = Blue60),
        navigationIcon = {
            IconButton(onClick = { navhHostController.popBackStack() }) {
                Icon(
                    painter = painterResource(id = R.drawable.arrow_back),
                    contentDescription = "Atras"
                )
            }
        })
}