package com.example.volunteerapp.ui.screens.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.volunteerapp.ui.components.SegmentedSwitch
import com.example.volunteerapp.ui.components.AuthField
import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource
import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import com.example.volunteerapp.R

@Composable
fun AuthScreen() {

    var isLogin by remember { mutableStateOf(true) }

    var login by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var middleName by remember { mutableStateOf("") }

    var isVolunteer by remember { mutableStateOf(true) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF121212))
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 40.dp, start = 16.dp, end = 16.dp),
            verticalArrangement = Arrangement.Top
        ) {

            // ---------- АНИМАЦИЯ ПОКАЗА/СКРЫТИЯ ЛОГОТИПА ----------
            AnimatedVisibility(
                visible = isLogin,
                enter = fadeIn(tween(300)) + slideInVertically { -80 },
                exit = fadeOut(tween(300)) + slideOutVertically { -80 }
            ) {
                Image(
                    painter = painterResource(R.drawable.logo),
                    contentDescription = null,
                    modifier = Modifier
                        .size(140.dp)
                        .padding(bottom = 20.dp)
                )
            }

            // ---------- КАРТОЧКА ----------
            Surface(
                modifier = Modifier
                    .wrapContentHeight()
                    .fillMaxWidth(0.9f)
                    .animateContentSize(),
                shape = RoundedCornerShape(20.dp),
                color = Color(0xFF1E1E1E),
                tonalElevation = 6.dp,
                shadowElevation = 12.dp
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(20.dp)
                ) {

                    // ---------- ЗАГОЛОВОК ----------
                    Text(
                        text = if (isLogin) "Авторизация" else "Регистрация",
                        color = Color.White,
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(Modifier.height(20.dp))

                    SegmentedSwitch(
                        option1 = "Войти",
                        option2 = "Регистрация",
                        selectedFirst = isLogin,
                        onSelect = { isLogin = it }
                    )

                    Spacer(Modifier.height(20.dp))

                    // ---------- АНИМАЦИЯ МЕЖДУ ФОРМАМИ ----------
                    // ---------- АНИМАЦИЯ СМЕНЫ ФОРМ ----------
                    AnimatedContent(
                        targetState = isLogin,
                        label = "auth_animation",
                        transitionSpec = {
                            fadeIn() togetherWith fadeOut()
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .animateContentSize()
                    ) { loginMode ->

                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(12.dp),   // ← ← ← ВАЖНО
                            modifier = Modifier.fillMaxWidth()
                        ) {

                            if (loginMode) {
                                AuthField(
                                    label = "Логин",
                                    value = login,
                                    onValueChange = { login = it },
                                    required = true
                                )

                                AuthField(
                                    label = "Пароль",
                                    value = password,
                                    onValueChange = { password = it },
                                    required = true
                                )

                            } else {

                                AuthField(
                                    label = "Имя",
                                    value = firstName,
                                    onValueChange = { firstName = it },
                                    required = true
                                )

                                AuthField(
                                    label = "Фамилия",
                                    value = lastName,
                                    onValueChange = { lastName = it },
                                    required = true
                                )

                                AuthField(
                                    label = "Отчество",
                                    value = middleName,
                                    onValueChange = { middleName = it },
                                    required = false
                                )

                                AuthField(
                                    label = "Логин",
                                    value = login,
                                    onValueChange = { login = it },
                                    required = true
                                )

                                AuthField(
                                    label = "Пароль",
                                    value = password,
                                    onValueChange = { password = it },
                                    required = true
                                )

                                Spacer(Modifier.height(8.dp))


                                SegmentedSwitch(
                                    option1 = "Волонтёр",
                                    option2 = "Организатор",
                                    selectedFirst = isVolunteer,
                                    onSelect = { isVolunteer = it }
                                )
                            }
                        }
                    }

                }
            }
            Spacer(Modifier.height(26.dp))

            Button(
                onClick = { /* TODO */ },
                modifier = Modifier.fillMaxWidth(0.7f),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF3F51B5))
            ) {
                Text(
                    text = if (isLogin) "Войти" else "Зарегистрироваться",
                    fontSize = 18.sp
                )
            }
        }
    }
}
