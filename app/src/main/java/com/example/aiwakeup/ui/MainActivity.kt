package com.example.comexampleaiwakeup

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AIWakeUpTunesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AlarmScreen()
                }
            }
        }
    }
}

@Composable
fun AIWakeUpTunesTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        content = content
    )
}

@Composable
fun AlarmScreen() {
    // Состояние для текущего времени
    val currentTime = remember { mutableStateOf(getCurrentTime()) }

    // Обновляем время каждую секунду
    LaunchedEffect(Unit) {
        while (true) {
            currentTime.value = getCurrentTime()
            kotlinx.coroutines.delay(1000L) // Обновляем каждую секунду
        }
    }

    // Основной UI
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Текущее время
        Text(
            text = currentTime.value,
            fontSize = 48.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 32.dp)
        )

        // Индикатор погоды (заглушка, позже подключим OpenWeatherMap)
        Text(
            text = "Погода: Загрузка...",
            fontSize = 24.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Кнопка для установки будильника
        Button(
            onClick = { /* Позже добавим логику установки времени */ },
            modifier = Modifier.fillMaxWidth(0.8f)
        ) {
            Text(text = "Установить будильник", fontSize = 18.sp)
        }
    }
}

// Функция для получения текущего времени
fun getCurrentTime(): String {
    val sdf = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
    return sdf.format(Date())
}