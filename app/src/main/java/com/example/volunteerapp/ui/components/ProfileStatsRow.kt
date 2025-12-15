package com.example.volunteerapp.ui.components


import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.volunteerapp.ui.theme.BlueTextColor
import androidx.compose.material3.Text
import androidx.compose.ui.unit.sp

@Composable
fun ProfileStatsRow(
    login: String,
    role: String,
    hours: String
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        StatCard(title = "Логин", value = login, modifier = Modifier.weight(1f))
        StatCard(title = "Роль", value = role, modifier = Modifier.weight(1f))
        StatCard(title = "Часы", value = hours, modifier = Modifier.weight(1f))
    }
}

@Composable
private fun StatCard(
    title: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .height(90.dp)
            .border(1.dp, BlueTextColor, RoundedCornerShape(12.dp))
            .padding(12.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = title,
            color = BlueTextColor,
            fontSize = 13.sp,
            fontWeight = FontWeight.Medium,
            maxLines = 1
        )

        Text(
            text = value,
            color = BlueTextColor,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            maxLines = 1
        )
    }
}
