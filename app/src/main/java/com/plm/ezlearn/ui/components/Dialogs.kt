package com.plm.ezlearn.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.plm.ezlearn.R

@Composable
fun DialogPaused(onResume: () -> Unit, onExit: () -> Unit) {
    AlertDialog(
        onDismissRequest = { /* Prevent dismiss */ },
        title = {
            Text("Game Paused", fontSize = 24.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center)
        },
        confirmButton = {
            Button(onClick = onResume) {
                Text("Resume")
            }
        },
        dismissButton = {
            OutlinedButton(onClick = onExit) {
                Text("EXIT")
            }
        },
        containerColor = Color.Gray,
        shape = RoundedCornerShape(12.dp)
    )
}

@Composable
fun DialogWin(onTryAgain: () -> Unit, onExit: () -> Unit) {
    AlertDialog(
        onDismissRequest = { /* Prevent dismiss */ },
        title = {
            Text("You win", fontSize = 24.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center)
        },
        confirmButton = {
            Button(onClick = onTryAgain) {
                Text("TRY AGAIN")
            }
        },
        dismissButton = {

            OutlinedButton(onClick = onExit) {
                Text("EXIT")
            }
        },
        containerColor = Color.Gray,
        shape = RoundedCornerShape(12.dp)
    )
}

@Composable
fun DialogLost(onTryAgain: () -> Unit, onExit: () -> Unit) {
    AlertDialog(
        onDismissRequest = { /* Prevent dismiss */ },
        title = {
            Text("GAME OVER", fontSize = 24.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center)
        },
        confirmButton = {
            Button(onClick = onTryAgain) {
                Text("TRY AGAIN")
            }
        },
        dismissButton = {
            OutlinedButton(onClick = onExit) {
                Text("EXIT")
            }
        },
        containerColor = Color.Gray,
        shape = RoundedCornerShape(12.dp)
    )
}

@Composable
fun DialogExplanation(onContinue: () -> Unit) {
    AlertDialog(
        onDismissRequest = onContinue,
        title = {
            Text("Explanation", fontWeight = FontWeight.Bold, color = Color.DarkGray)
        },
        text = {
            Text(
                "When you divide 69 by 69 you only get 1 that is why 1 is the correct answer.",
                color = Color.Black
            )
        },
        confirmButton = {
            Button(onClick = onContinue, colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1B5E20))) {
                Text("Continue", color = Color.White)
            }
        },
        containerColor = Color.Green,
        shape = RoundedCornerShape(12.dp)
    )
}