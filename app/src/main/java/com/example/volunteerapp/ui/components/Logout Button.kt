package com.example.volunteerapp.ui.components


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.volunteerapp.ui.theme.RedTextColor
import com.example.volunteerapp.R


@Composable
fun LogoutButton(
    onClick: () -> Unit
) {
    OutlinedButton(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        border = BorderStroke(3.dp, RedTextColor),
        colors = ButtonDefaults.outlinedButtonColors(
            contentColor = RedTextColor
        )
    ) {

        Spacer(Modifier.width(8.dp))

        Text(
            text = "Выйти из аккаунта",
            color = RedTextColor
        )
    }
}
