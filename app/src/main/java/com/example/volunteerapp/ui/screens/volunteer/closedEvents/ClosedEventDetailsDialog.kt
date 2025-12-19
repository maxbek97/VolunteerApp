package com.example.volunteerapp.ui.screens.volunteer.closedEvents

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.volunteerapp.domain.model.EventResponse
import com.example.volunteerapp.domain.model.VolunteerClosedEventResponse
import com.example.volunteerapp.ui.components.formatDate
import com.example.volunteerapp.ui.theme.BlueTextColor

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun formatDate(raw: String): String {
    val dateTime = LocalDateTime.parse(raw)
    val formatter = DateTimeFormatter.ofPattern("H:mm, dd.MM.yyyy г.")
    return dateTime.format(formatter)
}

@Composable
fun ClosedEventDetailsDialog(
    event: VolunteerClosedEventResponse,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {},
        title = null,
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {

                Text(
                    text = event.eventTitle,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = BlueTextColor,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )

                Text("Описание:")
                InfoBlock(event.eventDescription, italic = true)

                Text("Время:")
                InfoBlock(
                    "${formatDate(event.datetimeStart)}  -  ${formatDate(event.datetimeEnd)}"
                )

                Text("Присутствие:")
                if (event.attendance == "came") {
                    InfoBlock(
                        "Присутствовал", italic = true
                    )
                }
                else {
                    InfoBlock(
                        "Осутствовал", italic = true
                    )
                }

                Text("Кол-во часов за мероприятие")
                InfoBlock(
                    "${event.duracity} часов"
                )



                Spacer(Modifier.height(8.dp))

                OutlinedButton(
                    onClick = onDismiss,
                    modifier = Modifier.fillMaxWidth(),
                    border = BorderStroke(1.dp, BlueTextColor)
                ) {
                    Text("Закрыть", color = BlueTextColor)
                }
            }
        }
    )
}




@Composable
fun InfoBlock(
    text: String,
    italic: Boolean = false
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = Color(0xFFF2F2F2),
                shape = RoundedCornerShape(14.dp)
            )
            .padding(12.dp)
    ) {
        Text(
            text = text,
            color = BlueTextColor,
            fontStyle = if (italic) FontStyle.Italic else FontStyle.Normal
        )
    }
}

