package com.example.volunteerapp.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color


@Composable
fun AuthField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    required: Boolean
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = {
            Row {
                Text(label)
                if (required) Text("*", color = Color.Red)
            }
        },
        singleLine = true,
        modifier = Modifier.fillMaxWidth(0.85f),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color.White,
            unfocusedBorderColor = Color.Gray,
            focusedLabelColor = Color.White,
            unfocusedLabelColor = Color.Gray,
            cursorColor = Color.White
        )

    )
}
