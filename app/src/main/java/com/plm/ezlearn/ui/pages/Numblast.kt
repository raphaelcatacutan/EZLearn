package com.plm.ezlearn.ui.pages

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.plm.ezlearn.ui.theme.EZLearnTheme
import com.plm.ezlearn.ui.components.*

@Composable
fun ViewNumblast(navController: NavController = rememberNavController()) {
    val question: String = "69 + 69"
    val options: List<String> = listOf("69", "69", "69", "69")
    var isGameWon by remember { mutableStateOf(false) }
    var isGameLost by remember { mutableStateOf(false) }
    var showExplanation by remember { mutableStateOf(false) }
    var isPaused by remember { mutableStateOf(false) }
    val onOptionClick: (String) -> Unit = {isGameWon = true}
    val lives: Int = 3
    val timerProgress: Float = 0.7f // value between 0 and 1
    val onBackClick: () -> Unit = {showExplanation = true}

    BackHandler(enabled = true) {
        isPaused = true
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF0288D1)) // blue background
                .padding(16.dp)
        ) {
            // Top Bar
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                IconButton(onClick = onBackClick) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.White
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "MATH GAME",
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            // Timer bar
            LinearProgressIndicator(
                progress = { timerProgress },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp)
                    .padding(vertical = 8.dp)
            )
            // Question Box
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .background(Color(0xFF7E57C2))
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = question,
                    fontSize = 36.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Mode title
            Text(
                text = "ADDITION",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Options
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                for (row in options.chunked(2)) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        row.forEachIndexed { index, option ->
                            val bgColor = when (index) {
                                0 -> Color(0xFFFF9800) // Orange
                                1 -> Color.LightGray
                                else -> listOf(Color(0xFFAB47BC), Color(0xFF1565C0)).random()
                            }
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .height(80.dp)
                                    .clip(RoundedCornerShape(12.dp))
                                    .background(bgColor)
                                    .clickable { onOptionClick(option) },
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = option,
                                    fontSize = 24.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White
                                )
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            // Lives
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 12.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                repeat(3) { index ->
                    Icon(
                        imageVector = Icons.Default.Favorite,
                        contentDescription = "Life",
                        tint = if (index < lives) Color.Red else Color.LightGray,
                        modifier = Modifier.size(32.dp)
                    )
                }
            }
        }
        if (isGameWon) {
            DialogWin(onTryAgain = { isGameLost = false }, onExit = { isGameLost = false })
        }
        if (isGameLost) {
            DialogLost(onTryAgain = { isGameLost = false }, onExit = { isGameLost = false })
        }
        if (showExplanation) {
            DialogExplanation(onContinue = { showExplanation = false })
        }
        if (isPaused) {
            DialogPaused(onResume = { isPaused = false }, onExit = {})
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewNumblast() {
    EZLearnTheme {
        ViewNumblast()
    }
}

data class MathQuestion(
    val question: String,
    val answer: String,
    val options: List<String>
)

fun numblastGenerateQuestion(): MathQuestion {
    val operations = listOf("+", "-", "×", "÷")
    val operation = operations.random()

    val (num1, num2) = when (operation) {
        "+" -> Pair((1..99).random(), (1..99).random())
        "-" -> {
            val a = (1..99).random()
            val b = (1..a).random() // ensures result is not negative
            Pair(a, b)
        }
        "×" -> Pair((1..12).random(), (1..12).random()) // smaller for young kids
        "÷" -> {
            val b = (1..12).random()
            val a = b * (1..12).random()
            Pair(a, b) // ensures divisible
        }
        else -> Pair(0, 0)
    }

    val result = when (operation) {
        "+" -> num1 + num2
        "-" -> num1 - num2
        "×" -> num1 * num2
        "÷" -> num1 / num2
        else -> 0
    }

    val correctAnswer = result.toString()
    val options = mutableSetOf(correctAnswer)

    // Generate 3 unique incorrect options
    while (options.size < 4) {
        val wrong = (result + (-10..10).random()).coerceAtLeast(0).toString()
        options.add(wrong)
    }

    return MathQuestion(
        question = "$num1 $operation $num2",
        answer = correctAnswer,
        options = options.shuffled()
    )
}
