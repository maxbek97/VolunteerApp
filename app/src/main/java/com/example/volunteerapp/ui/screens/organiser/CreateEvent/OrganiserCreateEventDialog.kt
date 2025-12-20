package com.example.volunteerapp.ui.screens.organiser.CreateEvent

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.volunteerapp.ui.components.DateTimePickerBlock
import com.example.volunteerapp.ui.components.TopMessageBar
import com.example.volunteerapp.ui.theme.BlueTextColor
import java.time.LocalDateTime

@Composable
fun OrganiserCreateEventDialog(
    viewModel: OrganiserCreateEventViewModel,
    onDismiss: () -> Unit,
    onSuccess: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()

    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    var startDateTime by remember { mutableStateOf<LocalDateTime?>(null) }
    var endDateTime by remember { mutableStateOf<LocalDateTime?>(null) }

    // ===== TopMessageBar state =====
    var message by remember { mutableStateOf("") }
    var isError by remember { mutableStateOf(false) }
    var showMessage by remember { mutableStateOf(false) }

    Box {
        AlertDialog(
            onDismissRequest = onDismiss,
            confirmButton = {},
            title = null,
            text = {
                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {

                    Text(
                        text = "Новое мероприятие",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = BlueTextColor,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )

                    // ===== Название =====
                    Text("Название:")
                    InputBlock(
                        value = title,
                        onValueChange = {
                            if (it.length <= 300) title = it
                        },
                        placeholder = "Введите название"
                    )
                    Text(
                        text = "${title.length}/300",
                        fontSize = 12.sp,
                        color = Color.Gray,
                        modifier = Modifier.align(Alignment.End)
                    )

                    // ===== Описание =====
                    Text("Описание:")
                    InputBlock(
                        value = description,
                        onValueChange = {
                            if (it.length <= 500) description = it
                        },
                        placeholder = "Введите описание",
                        minLines = 3
                    )
                    Text(
                        text = "${description.length}/500",
                        fontSize = 12.sp,
                        color = Color.Gray,
                        modifier = Modifier.align(Alignment.End)
                    )

                    // ===== Даты =====
                    Text("Начало:")
                    DateTimePickerBlock(
                        value = startDateTime,
                        onValueChange = { startDateTime = it }
                    )

                    Text("Окончание:")
                    DateTimePickerBlock(
                        value = endDateTime,
                        onValueChange = { endDateTime = it }
                    )

                    Spacer(Modifier.height(8.dp))

                    // ===== Создать =====
                    Button(
                        onClick = {
                            val isValid =
                                title.isNotBlank() &&
                                        description.isNotBlank() &&
                                        startDateTime != null &&
                                        endDateTime != null &&
                                        startDateTime!!.isBefore(endDateTime!!)

                            if (!isValid) {
                                message = "Некорректно заполнены данные"
                                isError = true
                                showMessage = true
                                return@Button
                            }

                            viewModel.createEvent(
                                title = title,
                                description = description,
                                start = startDateTime.toString(),
                                end = endDateTime.toString()
                            )
                        },
                        modifier = Modifier.fillMaxWidth(),
                        enabled = uiState != CreateEventUiState.Loading,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = BlueTextColor
                        )
                    ) {
                        Text("Создать мероприятие", color = Color.White)
                    }

                    // ===== Закрыть =====
                    OutlinedButton(
                        onClick = onDismiss,
                        modifier = Modifier.fillMaxWidth(),
                        border = BorderStroke(1.dp, BlueTextColor)
                    ) {
                        Text("Закрыть", color = BlueTextColor)
                    }
                }
                TopMessageBar(
                    message = message,
                    isError = isError,
                    visible = showMessage,
                    onAutoHide = { showMessage = false },
                    modifier = Modifier.align(Alignment.TopCenter)
                )
            }
        )
    }

    // ===== Реакция на результат =====
    LaunchedEffect(uiState) {
        when (uiState) {
            is CreateEventUiState.Success -> {
                message = "Мероприятие создано"
                isError = false
                showMessage = true
                viewModel.resetState()
                onSuccess()
            }

            is CreateEventUiState.Error -> {
                message = "Ошибка сервера"
                isError = true
                showMessage = true
                viewModel.resetState()
            }

            else -> Unit
        }
    }
}


@Composable
fun InputBlock(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    minLines: Int = 1
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = { Text(placeholder) },
        modifier = Modifier.fillMaxWidth(),
        minLines = minLines,
        shape = RoundedCornerShape(14.dp),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color(0xFFF2F2F2),
            unfocusedContainerColor = Color(0xFFF2F2F2),
            disabledContainerColor = Color(0xFFF2F2F2),

            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        )
    )
}
