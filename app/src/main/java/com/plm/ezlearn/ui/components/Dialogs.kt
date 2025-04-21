package com.plm.ezlearn.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.plm.ezlearn.R

@Composable
fun DialogPaused(onResume: () -> Unit, onExit: () -> Unit) {
    Dialog(
        onDismissRequest = onResume,
        properties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = false
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .wrapContentHeight()
                .graphicsLayer(clip = false)
        ) {
            ComponentThreeDContainer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(430.dp)
                    .padding(top = 160.dp, bottom = 50.dp),
                backgroundColor = Color(0xFFFA9E56),
                shadowColor = Color(0xFF883104),
                cornerRadius = 15.dp,
                isPushable = false,
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 30.dp)
                        .padding(top = 20.dp)
                ) {
                    ComponentThreeDContainer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(55.dp),
                        backgroundColor = Color(0xFF77F533),
                        shadowColor = Color(0xFF236409),
                        cornerRadius = 15.dp,
                        isPushable = true,
                        onClick = onResume
                    ) {
                        Text("Resume", color = Color.Black, fontSize = 25.sp)
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    ComponentThreeDContainer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(55.dp),
                        backgroundColor = Color(0xFFFF4A47),
                        shadowColor = Color(0xFFC62828),
                        cornerRadius = 15.dp,
                        isPushable = true,
                        onClick = onExit
                    ) {
                        Text("Exit", color = Color.Black, fontSize = 25.sp)
                    }
                }
            }


            // Logo "floating" above dialog content
            Image(
                painter = painterResource(id = R.drawable.pause),
                contentDescription = "Logo",
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .offset(y = (-20).dp)
                    .size(280.dp)
            )
        }
    }
}

@Composable
fun DialogWin(onTryAgain: () -> Unit, onExit: () -> Unit) {
    Dialog(
        onDismissRequest = onTryAgain,
        properties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = false
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .wrapContentHeight()
                .graphicsLayer(clip = false)
        ) {
            ComponentThreeDContainer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(430.dp)
                    .padding(top = 160.dp, bottom = 50.dp),
                backgroundColor = Color(0xFFEFD070),
                shadowColor = Color(0xFF94871A),
                cornerRadius = 15.dp,
                isPushable = false,
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 30.dp)
                        .padding(top = 20.dp)
                ) {
                    ComponentThreeDContainer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(55.dp),
                        backgroundColor = Color(0xFF77F533),
                        shadowColor = Color(0xFF236409),
                        cornerRadius = 15.dp,
                        isPushable = true,
                        onClick = onTryAgain
                    ) {
                        Text("Try Again", color = Color.Black, fontSize = 25.sp)
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    ComponentThreeDContainer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(55.dp),
                        backgroundColor = Color(0xFFFF4A47),
                        shadowColor = Color(0xFFC62828),
                        cornerRadius = 15.dp,
                        isPushable = true,
                        onClick = onExit
                    ) {
                        Text("Exit", color = Color.Black, fontSize = 25.sp)
                    }
                }
            }


            // Logo "floating" above dialog content
            Image(
                painter = painterResource(id = R.drawable.win3),
                contentDescription = "Logo",
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .offset(y = (-20).dp)
                    .size(280.dp)
            )
        }
    }
}

@Composable
fun DialogLost(onTryAgain: () -> Unit, onExit: () -> Unit) {
    Dialog(
        onDismissRequest = onTryAgain,
        properties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = false
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .wrapContentHeight()
                .graphicsLayer(clip = false)
        ) {
            ComponentThreeDContainer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(430.dp)
                    .padding(top = 160.dp, bottom = 50.dp),
                backgroundColor = Color(0xFFA15906),
                shadowColor = Color(0xFF49210F),
                cornerRadius = 15.dp,
                isPushable = false,
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 30.dp)
                        .padding(top = 20.dp)
                ) {
                    ComponentThreeDContainer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(55.dp),
                        backgroundColor = Color(0xFF77F533),
                        shadowColor = Color(0xFF236409),
                        cornerRadius = 15.dp,
                        isPushable = true,
                        onClick = onTryAgain
                    ) {
                        Text("Try Again", color = Color.Black, fontSize = 25.sp)
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    ComponentThreeDContainer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(55.dp),
                        backgroundColor = Color(0xFFFF4A47),
                        shadowColor = Color(0xFFC62828),
                        cornerRadius = 15.dp,
                        isPushable = true,
                        onClick = onExit
                    ) {
                        Text("Exit", color = Color.Black, fontSize = 25.sp)
                    }
                }
            }


            // Logo "floating" above dialog content
            Image(
                painter = painterResource(id = R.drawable.paused),
                contentDescription = "Logo",
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .offset(y = (-20).dp)
                    .size(280.dp)
            )
        }
    }
}

@Composable
fun DialogExplanation(onContinue: () -> Unit, explanation: String) {
    Dialog(
        onDismissRequest = onContinue,
        properties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = false
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .wrapContentHeight()
                .graphicsLayer(clip = false)
        ) {
            ComponentThreeDContainer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(470.dp)
                    .padding(top = 150.dp, bottom = 50.dp),
                backgroundColor = Color(0xFF4F96DC),
                shadowColor = Color(0xFF221A94),
                cornerRadius = 15.dp,
                isPushable = false,
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 30.dp)
                        .padding(top = 30.dp)
                ) {

                    Text(
                        explanation,
                        color = Color.Black,
                        fontSize = 25.sp,
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    ComponentThreeDContainer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(55.dp),
                        backgroundColor = Color(0xFF88F84F),
                        shadowColor = Color(0xFF236409),
                        cornerRadius = 15.dp,
                        isPushable = true,
                        onClick = onContinue
                    ) {
                        Text("Continue", color = Color.Black, fontSize = 25.sp)
                    }
                }
            }

            Image(
                painter = painterResource(id = R.drawable.explanation),
                contentDescription = "Logo",
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .offset(y = (-20).dp)
                    .size(280.dp)
            )
        }
    }
}