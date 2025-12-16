package com.example.volunteerapp.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.volunteerapp.domain.model.EventResponse
import com.example.volunteerapp.ui.theme.BlueTextColor

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun formatDate(raw: String): String {
    val dateTime = LocalDateTime.parse(raw)
    val formatter = DateTimeFormatter.ofPattern("H:mm, dd.MM.yyyy г.")
    return dateTime.format(formatter)
}

@Composable
fun EventCard(
    event: EventResponse,
    onClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(1.dp, BlueTextColor),
        color = Color.White
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Text(
                text = event.eventTitle,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = BlueTextColor
            )

            Text(
                text = "${formatDate(event.datetimeStart)} – ${formatDate(event.datetimeEnd)}",
                fontSize = 12.sp,
                color = BlueTextColor
            )

            Text(
                text = event.eventDescription,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                color = BlueTextColor
            )
        }
    }
}
