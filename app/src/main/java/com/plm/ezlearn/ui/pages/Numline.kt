package com.plm.ezlearn.ui.pages

import androidx.activity.compose.BackHandler
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.plm.ezlearn.R
import com.plm.ezlearn.ui.components.ComponentOutlinedText
import com.plm.ezlearn.ui.components.ComponentThreeDContainer
import com.plm.ezlearn.ui.components.DialogExplanation
import com.plm.ezlearn.ui.components.DialogLost
import com.plm.ezlearn.ui.components.DialogPaused
import com.plm.ezlearn.ui.components.DialogWin
import com.plm.ezlearn.ui.theme.EZLearnTheme
import com.plm.ezlearn.ui.theme.chalkboardFont
import com.plm.ezlearn.ui.theme.shootingStarFont

@Composable

fun ViewNumline(navController: NavController = rememberNavController()) {
    var question by remember {mutableStateOf(numlineGenerateQuestion())}
    var showExplanation by remember { mutableStateOf(false) }
    var isPaused by remember { mutableStateOf(false) }
    var lives by remember { mutableIntStateOf(3) }
    var remainingItems by remember { mutableIntStateOf(10) }
    var progress = remember { Animatable(1f) }
    val onBackClick: () -> Unit = {isPaused = true}
    var isGameLost = progress.value <= 0 || lives <= 0
    var isGameWon = remainingItems <= 0
    var isCorrect by remember { mutableStateOf(false) }
    var correctAnswers by remember { mutableIntStateOf(0) }

    BackHandler(enabled = true) {
        isPaused = true
    }
    LaunchedEffect(isPaused, isGameWon, isGameLost, showExplanation) {
        if (!isPaused && !isGameWon && !isGameLost && !showExplanation) {
            progress.animateTo(
                targetValue = 0f,
                animationSpec = tween(
                    durationMillis = 10000, // 10 seconds
                    easing = LinearEasing
                )
            )
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.bg_numline),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 30.dp, horizontal = 30.dp)
        ) {
            // Top Bar
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                ComponentThreeDContainer(
                    modifier = Modifier
                        .width(55.dp)
                        .height(55.dp),
                    backgroundColor = Color(0xFF78909C),
                    shadowColor = Color(0xFF546E7A),
                    cornerRadius = 15.dp,
                    isPushable = true,
                    onClick = { isPaused = true }
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Pause",
                        tint = Color.White,
                        modifier = Modifier.size(24.dp)
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
                ComponentOutlinedText(
                    text = "Numline",
                    fillColor = Color.Yellow,
                    fontSize = 30.sp,
                    fontFamily = shootingStarFont,
                    outlineColor = Color.Black,
                    outlineDrawStyle = Stroke(10f)
                )
            }

            LinearProgressIndicator(
                progress = { progress.value },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(35.dp)
                    .padding(vertical = 8.dp),
                strokeCap = StrokeCap.Round,
                color = Color(0xFFE1CD83),
                trackColor = Color(0xFF463F04),
                gapSize = 0.dp,
            )
            Spacer(modifier = Modifier.width(50.dp))

            //Question  Box
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .background(Color.Transparent),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Sort the following in increasing order: ",
                    fontSize = 25.sp,
                    fontFamily = chalkboardFont,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.width(8.dp))
                ComponentOutlinedText(
                    text = question.question,
                    outlineColor = Color.Blue,
                    randomizeColor = true,
                    fontWeight = FontWeight.Bold,
                    fontSize = 45.sp,
                    fontFamily = shootingStarFont,
                    outlineDrawStyle = Stroke(10f),
                    letterSpacing = 5.sp
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Options
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                for (row in question.options.chunked(1)) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        row.forEachIndexed { index, option ->
                            val bgColor = if (question.answer == option && showExplanation) Color(
                                0xFFB6F596
                            ) else Color(0xFFFCFF96)
                            ComponentThreeDContainer(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(80.dp),
                                backgroundColor = bgColor,
                                shadowColor = Color(0xFF1F331F),
                                cornerRadius = 15.dp,
                                onClick = {
                                    showExplanation = true
                                    isCorrect = option == question.answer
                                },
                                isPushable = true
                            ) {
                                Text(
                                    text = option,
                                    fontSize = 26.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.Black,
                                    letterSpacing = 5.sp,
                                    modifier = Modifier
                                        .padding(top = 5.dp)
                                )
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {// Lives
                ComponentThreeDContainer(
                    modifier = Modifier
                        .width(180.dp)
                        .height(90.dp),
                    backgroundColor = Color(0xFFFFB4B4),
                    shadowColor = Color(0xFF6B1520),
                    cornerRadius = 15.dp
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(0.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
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
                        Text(
                            text = "Correct Answers: $correctAnswers / 10",
                            color = Color.Black,
                            fontSize = 20.sp,
                            modifier = Modifier
                                .padding(top = 5.dp)
                        )
                    }
                }
            }
        }
        if (isGameWon) {
            DialogWin(
                onTryAgain = {
                    isGameWon = false
                    navController.navigate("numline")
                },
                onExit = {
                    isGameWon = false
                    navController.navigate("main-menu")
                }
            )
        }
        if (isGameLost) {
            DialogLost(
                onTryAgain = {
                    isGameLost = false
                    navController.navigate("numline")
                },
                onExit = {
                    isGameLost = false
                    navController.navigate("main-menu")
                }
            )
        }
        if (showExplanation) {
            DialogExplanation(onContinue = {
                showExplanation = false
                if (isCorrect) {
                    correctAnswers++
                    remainingItems--
                    if (remainingItems > 0) question = numlineGenerateQuestion()
                } else {
                    if (--lives > 0) {
                        question = numlineGenerateQuestion()
                    }
                }
            })
        }
        if (isPaused) {
            DialogPaused(onResume = { isPaused = false }, onExit = {
                isPaused = false
                navController.navigate("main-menu")
            })
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewNumline() {
    EZLearnTheme {
        ViewNumline()
    }
}

private data class NumlineQuestion(
    val question: String,        // e.g. "7 2 9 1"
    val answer: String,          // e.g. "1 2 7 9"
    val options: List<String>    // shuffled variations, one correct
)

private fun numlineGenerateQuestion(): NumlineQuestion {
    val numbers = List(4) { (1..20).random() }
    val sorted = numbers.sorted()

    val question = numbers.joinToString(", ")
    val correctAnswer = sorted.joinToString(", ")

    val options = mutableSetOf(correctAnswer)

    // Generate 3 wrong options by shuffling the original list until unique
    while (options.size < 4) {
        val shuffled = numbers.shuffled().joinToString(" ")
        if (shuffled != correctAnswer) {
            options.add(shuffled)
        }
    }

    return NumlineQuestion(
        question = question,
        answer = correctAnswer,
        options = options.shuffled()
    )
}
