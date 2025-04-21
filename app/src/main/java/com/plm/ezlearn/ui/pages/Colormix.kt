package com.plm.ezlearn.ui.pages

import androidx.activity.compose.BackHandler
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
fun ViewColormix(navController: NavController = rememberNavController()) {
    var question by remember {mutableStateOf(colormixGenerateQuestion())}
    var showExplanation by remember { mutableStateOf(false) }
    var isPaused by remember { mutableStateOf(false) }
    var explanation by remember { mutableStateOf("") }
    var lives by remember { mutableIntStateOf(3) }
    var remainingItems by remember { mutableIntStateOf(10) }
    var progress = remember { Animatable(1f) }
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
                    durationMillis = 60000, // 10 seconds
                    easing = LinearEasing
                )
            )
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.bg_colormix),
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
                    text = "Color Mix",
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
                color = Color(0xFFF38702),
                trackColor = Color(0xFF463204),
                gapSize = 0.dp,
            )
            Spacer(modifier = Modifier.width(16.dp))

            //Question  Box
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .background(Color.Transparent),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "What color is the image below?",
                    fontSize = 25.sp,
                    fontFamily = chalkboardFont,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.width(8.dp))
                Image(
                    painter = painterResource(id = question.questionImage),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxHeight()
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
                                    explanation = question.explanation
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
                    navController.navigate("colormix")
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
                    navController.navigate("colormix")
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
                    if (remainingItems > 0) question = colormixGenerateQuestion()
                } else {
                    if (--lives > 0) {
                        question = colormixGenerateQuestion()
                    }
                }
            }, explanation)
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
fun PreviewColormix() {
    EZLearnTheme {
        ViewColormix()
    }
}

private data class ColormixQuestion(
    val questionImage: Int,     // drawable ID
    val answer: String,         // correct color name
    val options: List<String>,   // multiple choice
    val explanation: String
)
data class ColorItem(
    val images: List<Int>,
    val explanation: String
)

private val colormixImageMap: Map<String, ColorItem> = mapOf(
    "Red" to ColorItem(
        images = listOf(R.drawable.red_square, R.drawable.red_square),
        explanation = "The right answer is red. You can usually see this in apples, stop signs, and strawberries."
    ),
    "Blue" to ColorItem(
        images = listOf(R.drawable.blue_diamond, R.drawable.blue_diamond),
        explanation = "The right answer is blue. You can usually see this in the sky, the ocean, and blueberries."
    ),
    "Green" to ColorItem(
        images = listOf(R.drawable.green_circle, R.drawable.green_circle),
        explanation = "The right answer is green. You can usually see this in grass, leaves, and frogs."
    ),
    "Yellow" to ColorItem(
        images = listOf(R.drawable.yellow_triangle, R.drawable.yellow_triangle),
        explanation = "The right answer is yellow. You can usually see this in the sun, bananas, and school buses."
    ),
    "Orange" to ColorItem(
        images = listOf(R.drawable.orange_rectangle, R.drawable.orange_rectangle),
        explanation = "The right answer is orange. You can usually see this in oranges, pumpkins, and traffic cones."
    ),
    "Purple" to ColorItem(
        images = listOf(R.drawable.purple_pentagon, R.drawable.purple_pentagon),
        explanation = "The right answer is purple. You can usually see this in grapes, eggplants, and lavender flowers."
    )
)



private fun colormixGenerateQuestion(): ColormixQuestion {
    // Choose correct color
    val correctColor = colormixImageMap.keys.random()
    val imageList = colormixImageMap[correctColor]!!.images
    val questionImage = imageList.random()

    // Get wrong options
    val otherColors = colormixImageMap.keys.filter { it != correctColor }.shuffled()
    val options = (listOf(correctColor) + otherColors.take(3)).shuffled()

    return ColormixQuestion(
        questionImage = questionImage,
        answer = correctColor,
        options = options,
        explanation = colormixImageMap[correctColor]!!.explanation
    )
}
