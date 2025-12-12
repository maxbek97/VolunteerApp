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
import com.example.volunteerapp.ui.components.*

import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource
import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import com.example.volunteerapp.R
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModel
import com.example.volunteerapp.data.remote.AuthRepository
import com.example.volunteerapp.data.remote.RetrofitClient
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester


@Composable
fun AuthScreen() {

    // 1. **ПОДГОТОВКА ViewModel**

    // **Создаем необходимые зависимости (Репозиторий)**
    val repository = remember { AuthRepository(RetrofitClient.apiService) }

    // **Создаем ViewModel, используя фабрику для передачи repository**
    val viewModel: AuthViewModel = viewModel(
        factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                if (modelClass.isAssignableFrom(AuthViewModel::class.java)) {
                    @Suppress("UNCHECKED_CAST")
                    return AuthViewModel(repository) as T
                }
                throw IllegalArgumentException("Unknown ViewModel class")
            }
        }
    )

    val uiState by viewModel.uiState.collectAsState()

    var topMessage by remember { mutableStateOf("") }
    var isError by remember { mutableStateOf(false) }

    // **Собираем состояние из ViewModel (AuthState)**
    val authState by viewModel.uiState.collectAsState()

    var isLogin by remember { mutableStateOf(true) }

    var login by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var middleName by remember { mutableStateOf("") }

    var isVolunteer by remember { mutableStateOf(true) }

    val loginFocus = remember { FocusRequester() }
    val passwordFocus = remember { FocusRequester() }
    val lastnameFocus = remember { FocusRequester() }
    val firstnameFocus = remember { FocusRequester() }
    val middlenameFocus = remember { FocusRequester() }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF111845))
    ) {

        TopMessageBar(
            message = topMessage,
            isError = isError,
            onDismiss = {
                topMessage = ""
            }
        )
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
                color = Color.White,
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
                        color = Color(0xFF2139d1
                    ),
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
                                    required = true,
                                    focusRequester = loginFocus,
                                    nextFocusRequester = passwordFocus
                                )

                                AuthField(
                                    label = "Пароль",
                                    value = password,
                                    onValueChange = { password = it },
                                    required = true,
                                    focusRequester = passwordFocus,
                                    isLast = true
                                )

                            } else {

                                AuthField(
                                    label = "Фамилия",
                                    value = lastName,
                                    onValueChange = { lastName = it },
                                    required = true,
                                    focusRequester = lastnameFocus,
                                    nextFocusRequester = firstnameFocus
                                )

                                AuthField(
                                    label = "Имя",
                                    value = firstName,
                                    onValueChange = { firstName = it },
                                    required = true,
                                    focusRequester = firstnameFocus,
                                    nextFocusRequester = middlenameFocus
                                )


                                AuthField(
                                    label = "Отчество",
                                    value = middleName,
                                    onValueChange = { middleName = it },
                                    required = false,
                                    focusRequester = middlenameFocus,
                                    nextFocusRequester = loginFocus
                                )

                                AuthField(
                                    label = "Логин",
                                    value = login,
                                    onValueChange = { login = it },
                                    required = true,
                                    focusRequester = loginFocus,
                                    nextFocusRequester = passwordFocus
                                )

                                AuthField(
                                    label = "Пароль",
                                    value = password,
                                    onValueChange = { password = it },
                                    required = true,
                                    focusRequester = passwordFocus,
                                    isLast = true
                                )

                                Spacer(Modifier.height(8.dp))


                                SegmentedSwitch(
                                    option1 = "Организатор",
                                    option2 = "Волонтёр",
                                    selectedFirst = !isVolunteer,
                                    onSelect = { isVolunteer = !it }
                                )

                            }
                        }
                    }

                }
            }
            Spacer(Modifier.height(26.dp))

            Button(
                onClick = {
                    if (isLogin) {
                        viewModel.login(login, password)
                    } else {
                        val role = if (isVolunteer) "volunteer" else "organizer"
                        viewModel.register(login, password, role, firstName, lastName, middleName)
                    }
                },
                enabled = authState != AuthUiState.Loading,
                modifier = Modifier.fillMaxWidth(0.7f),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF3F51B5))
            ) {
                if (authState == AuthUiState.Loading) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(24.dp),
                        strokeWidth = 3.dp,
                        color = Color.White
                    )
                } else {
                    Text(
                        text = if (isLogin) "Войти" else "Зарегистрироваться",
                        fontSize = 18.sp
                    )
                }
            }
            // 3. **ОБРАБОТКА СОСТОЯНИЯ UI (ОШИБКИ/УСПЕХ)**
            when (val state = authState) {
                is AuthUiState.Error -> {
                    topMessage = "Произошла ошибка"
                    isError = true
                }
                is AuthUiState.LoginSuccess -> {
                    topMessage = "С возвращением!"
                    isError = false
                }
                is AuthUiState.RegistrationSuccess -> {
                    topMessage = "Регистрация прошла успешно"
                    isError = false
                    // TODO: Возможно, переключить isLogin = true
                }
                else -> Unit // AuthUiState.Idle или AuthUiState.Loading (уже показано в кнопке)
            }
        }
    }
}
