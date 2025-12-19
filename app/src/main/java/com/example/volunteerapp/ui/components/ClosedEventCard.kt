package com.example.volunteerapp.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import com.example.volunteerapp.domain.model.VolunteerClosedEventResponse
import com.example.volunteerapp.ui.theme.BlueTextColor

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Composable
fun ClosedEventCard(
    event: VolunteerClosedEventResponse,
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

            // ===== Название =====
            Text(
                text = event.eventTitle,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = BlueTextColor
            )

            // ===== Дата =====
            Text(
                text = "${formatDate(event.datetimeStart)} – ${formatDate(event.datetimeEnd)}",
                fontSize = 12.sp,
                color = BlueTextColor
            )

            // ===== Описание =====
            Text(
                text = event.eventDescription,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                color = BlueTextColor
            )

            Spacer(Modifier.height(8.dp))
        }
    }
}
