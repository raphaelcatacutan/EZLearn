package com.plm.ezlearn.ui.pages

import com.plm.ezlearn.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController


@Composable
fun ViewMainMenu(navController: NavController = rememberNavController()) {
    var showInstructionDialog by remember { mutableStateOf(false) }
    var instructionMenu by remember {mutableStateOf(MenuItem("", 0, "", ""))}

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFB3E5FC)) // Light blue background
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        // Top bar: profile + level
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.bg), // your drawable
                    contentDescription = "Avatar",
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .border(2.dp, Color.White, CircleShape)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text("Grace", fontWeight = FontWeight.Bold, fontSize = 18.sp)
            }

            Box(
                modifier = Modifier
                    .background(Color(0xFFFFD54F), RoundedCornerShape(12.dp))
                    .padding(horizontal = 12.dp, vertical = 4.dp)
            ) {
                Text("Level 1", fontWeight = FontWeight.Bold, fontSize = 14.sp)
            }
        }

        // Banner
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(160.dp),
            shape = RoundedCornerShape(24.dp),
            elevation = CardDefaults.cardElevation(8.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFFFFFFF))
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                Text("Let’s play\nwith us!", fontSize = 24.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center)
                // Add background image here if needed
            }
        }

        // Menu buttons in grid
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(4.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxHeight()
        ) {
            items(menuItems.size) { index ->
                val item = menuItems[index]
                ComponentMenuItemCard(
                    title = item.title,
                    imageRes = item.imageRes,
                    onClick = {
                        instructionMenu = item
                        showInstructionDialog = true
                    }
                )
            }
        }
        if (showInstructionDialog) {
            DialogGameInstruction(
                message = instructionMenu.instruction,
                imageRes = instructionMenu.imageRes, // Replace with the correct resource
                onClick = {
                    navController.navigate(instructionMenu.path)
                    showInstructionDialog = false
                },
                onDismiss = { showInstructionDialog = false }
            )
        }
    }
}

@Composable
fun ComponentMenuItemCard(title: String, imageRes: Int, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .height(120.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        elevation = CardDefaults.cardElevation(6.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        onClick = onClick
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(8.dp)
        ) {
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = title,
                modifier = Modifier.size(48.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(title, fontWeight = FontWeight.Bold, fontSize = 14.sp, textAlign = TextAlign.Center)
        }
    }
}

// Example menu items
data class MenuItem(val title: String, val imageRes: Int, val path: String, val instruction: String)

val menuItems = listOf(
    MenuItem("Numblast", R.drawable.bg, "numblast", "Choose a category: Addition, Subtraction, Multiplication, or Division. Answer as many questions as you can!"),
    MenuItem("Colormix", R.drawable.bg, "colormix", "Match the color, not the word! Get as many right as you can!"),
    MenuItem("Oddle", R.drawable.bg, "oddle", "A number will appear on the screen. Choose if it’s Even or Odd."),
    MenuItem("Shapely", R.drawable.bg, "shapely", "A shape will appear on the screen. Pick the correct name of the shape."),
    MenuItem("Numline", R.drawable.bg, "numline", "Arrange the given numbers in ascending order. Pick the correct sequence!"),
    MenuItem("TickTocky", R.drawable.bg, "ticktocky", "A clock will show on the screen. Choose the correct time from the options below!")
)


@Preview(showBackground = true)
@Composable
fun PreviewMainMenu() {
    ViewMainMenu()
}


@Composable
fun DialogGameInstruction(
    message: String,
    imageRes: Int,
    onClick: () -> Unit,
    onDismiss: () -> Unit
) {
    Dialog(onDismissRequest = onDismiss) {
        Box(
            modifier = Modifier
                .wrapContentSize()
                .padding(16.dp)
        ) {
            // Main card background
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 40.dp), // Space for image
                shape = RoundedCornerShape(20.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
            ) {
                Column(
                    modifier = Modifier
                        .background(Color.White)
                        .padding(top = 56.dp, start = 24.dp, end = 24.dp, bottom = 24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = message,
                        fontSize = 18.sp,
                        color = Color.Black,
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    Button(
                        onClick = onClick,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(text = "Play")
                    }
                }
            }

            // Image floating above the dialog
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = null,
                modifier = Modifier
                    .size(80.dp)
                    .align(Alignment.TopCenter)
                    .offset(y = (-10).dp) // Overflow effect
                    .shadow(4.dp, shape = CircleShape)
                    .background(Color.White, shape = CircleShape)
                    .padding(4.dp) // Optional image padding
            )
        }
    }
}