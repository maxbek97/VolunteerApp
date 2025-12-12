package com.example.volunteerapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.ui.text.style.TextAlign

private fun formatTitle(title: String): String {
    val parts = title.trim().split(" ")
    return if (parts.size == 2) {
        parts[0] + "\n" + parts[1]
    } else {
        title
    }
}
@Composable
fun VolunteerBottomNavigation(
    items: List<BottomButton>,
    selectedRoute: String,
    onItemClick: (BottomButton) -> Unit
) {
    val darkBlue = Color(0xFF111845)
    val lightBlue = Color(0xFF1B266B)
    val gray = Color(0xFFB0B0B0)
    val green = Color(0xFF4CAF50)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(darkBlue)
            .windowInsetsPadding(WindowInsets.navigationBars)
            .padding(vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        items.forEach { item ->
            val isSelected = item.route == selectedRoute

            val bgColor by animateColorAsState(
                targetValue = if (isSelected) lightBlue else Color.Transparent,
                animationSpec = tween(250)
            )

            val contentColor by animateColorAsState(
                targetValue = if (isSelected) green else gray,
                animationSpec = tween(250)
            )

            Column(
                modifier = Modifier
                    .weight(1f)
                    .height(64.dp) // 🔥 одинаковая высота
                    .padding(horizontal = 6.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(bgColor)
                    .clickable { onItemClick(item) }
                    .padding(vertical = 6.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                Icon(
                    painter = item.icon,
                    contentDescription = item.title,
                    tint = contentColor,
                    modifier = Modifier.size(22.dp)
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = formatTitle(item.title),   // 🔥 перенос слов
                    color = contentColor,
                    fontSize = 11.sp,
                    lineHeight = 12.sp,                // 🔥 плотные строки
                    maxLines = 2,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}



