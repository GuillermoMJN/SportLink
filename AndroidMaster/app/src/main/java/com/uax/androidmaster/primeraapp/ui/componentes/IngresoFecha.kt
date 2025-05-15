package com.uax.androidmaster.primeraapp.ui.componentes

import android.R
import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.uax.androidmaster.primeraapp.ui.theme.Black
import com.uax.androidmaster.primeraapp.ui.theme.Blue100
import com.uax.androidmaster.primeraapp.ui.theme.Transparent
import com.uax.androidmaster.primeraapp.ui.theme.White
import java.util.*

@Composable
fun IngresoFecha(texto: String, paddingH: Int, paddingV: Int) {
    val context = LocalContext.current
    val calendar = Calendar.getInstance()

    var selectedDate by remember { mutableStateOf("") }

    // Crear el diálogo DatePicker
    val datePickerDialog = DatePickerDialog(
        context,
        { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
            selectedDate = "%02d/%02d/%04d".format(dayOfMonth, month + 1, year)
        },
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH)
    )

    // UI con TextField (no editable, pero visualmente activo)
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = paddingH.dp, vertical = paddingV.dp)
            .clickable { datePickerDialog.show() }
    ) {
        TextField(
            value = selectedDate,
            onValueChange = {},
            label = { Text(texto.toString(), color = Black) },
            colors = TextFieldDefaults.colors(
                Black,
                focusedLabelColor = Blue100,
                focusedIndicatorColor = Transparent,
                unfocusedIndicatorColor = Transparent
            ),
            enabled = false, // desactiva el teclado
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp) // 👈 Borde redondeado
        )
    }
}