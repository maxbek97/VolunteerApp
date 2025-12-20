package com.example.volunteerapp.ui.components

import android.app.TimePickerDialog
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.example.volunteerapp.ui.theme.BlueTextColor
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Composable
fun DateTimePickerBlock(
    value: LocalDateTime?,
    onValueChange: (LocalDateTime) -> Unit
) {
    val context = LocalContext.current

    Button(
        onClick = {
            val now = LocalDateTime.now()

            android.app.DatePickerDialog(
                context,
                { _, y, m, d ->
                    TimePickerDialog(
                        context,
                        { _, h, min ->
                            onValueChange(
                                LocalDateTime.of(y, m + 1, d, h, min)
                            )
                        },
                        now.hour,
                        now.minute,
                        true
                    ).show()
                },
                now.year,
                now.monthValue - 1,
                now.dayOfMonth
            ).show()
        },
        modifier = Modifier.fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFFEAEAEA),
            contentColor = BlueTextColor
        )
    ) {
        Text(
            text = value?.format(
                DateTimeFormatter.ofPattern("H:mm, dd.MM.yyyy")
            ) ?: "Выбрать дату и время"
        )
    }
}
