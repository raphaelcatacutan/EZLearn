package com.plm.ezlearn.ui.pages

import com.plm.ezlearn.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
    // Add states for dialogs visibility
    var showNumblastDialog by remember { mutableStateOf(false) }
    var showColormixDialog by remember { mutableStateOf(false) }
    var showTickTockyDialog by remember { mutableStateOf(false) }
    var showOddleDialog by remember { mutableStateOf(false) }
    var showShapelyDialog by remember { mutableStateOf(false) }
    var showNumlineDialog by remember { mutableStateOf(false) }

    // Show dialogs if their respective states are true
    if (showNumblastDialog) {
        NumblastDialog(
            onDismiss = { showNumblastDialog = false },
            onPlayClick = { category ->
                navController.navigate("numblast/$category")
                showNumblastDialog = false
            }
        )
    }

    if (showColormixDialog) {
        ColormixDialog(
            onDismiss = { showColormixDialog = false },
            onPlayClick = {
                navController.navigate("colormix")
                showColormixDialog = false
            }
        )
    }

    if (showTickTockyDialog) {
        TickTockyDialog(
            onDismiss = { showTickTockyDialog = false },
            onPlayClick = {
                navController.navigate("ticktocky")
                showTickTockyDialog = false
            }
        )
    }

    if (showOddleDialog) {
        OddleDialog(
            onDismiss = { showOddleDialog = false },
            onPlayClick = {
                navController.navigate("oddle")
                showOddleDialog = false
            }
        )
    }

    if (showShapelyDialog) {
        ShapelyDialog(
            onDismiss = { showShapelyDialog = false },
            onPlayClick = {
                navController.navigate("shapely")
                showShapelyDialog = false
            }
        )
    }

    if (showNumlineDialog) {
        NumlineDialog(
            onDismiss = { showNumlineDialog = false },
            onPlayClick = {
                navController.navigate("numline")
                showNumlineDialog = false
            }
        )
    }

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
                Text("Let's play\nwith us!", fontSize = 24.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center)
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
                        when (item.title) {
                            "Numblast" -> showNumblastDialog = true
                            "Colormix" -> showColormixDialog = true
                            "TickTocky" -> showTickTockyDialog = true
                            "Oddle" -> showOddleDialog = true
                            "Shapely" -> showShapelyDialog = true
                            "Numline" -> showNumlineDialog = true
                            else -> navController.navigate(item.path)
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun ComponentMenuItemCard(title: String, imageRes: Int, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .height(120.dp)
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(24.dp),
        elevation = CardDefaults.cardElevation(6.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
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

@Composable
fun NumblastDialog(
    onDismiss: () -> Unit,
    onPlayClick: (String) -> Unit
) {
    Dialog(onDismissRequest = onDismiss) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFF2196F3)) // Bright blue background
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Title
                Text(
                    text = "NUMBLAST",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )

                // Instructions
                Text(
                    text = "Choose a category: Addition, Subtraction, Multiplication " +
                            "or Division. Answer the questions by selecting the correct " +
                            "choice from four options as many right as you can!",
                    fontSize = 14.sp,
                    color = Color.White,
                    textAlign = TextAlign.Center
                )

                // Category buttons in grid
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    contentPadding = PaddingValues(4.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                ) {
                    item {
                        OperationButton(
                            operation = "ADDITION",
                            bgColor = Color(0xFF42A5F5), // Purple
                            icon = "+",
                            onClick = { onPlayClick("addition") }
                        )
                    }
                    item {
                        OperationButton(
                            operation = "SUBTRACTION",
                            bgColor = Color(0xFF42A5F5), // Blue
                            icon = "-",
                            onClick = { onPlayClick("subtraction") }
                        )
                    }
                    item {
                        OperationButton(
                            operation = "MULTIPLICATION",
                            bgColor = Color(0xFF42A5F5), // Purple
                            icon = "×",
                            onClick = { onPlayClick("multiplication") }
                        )
                    }
                    item {
                        OperationButton(
                            operation = "DIVISION",
                            bgColor = Color(0xFF42A5F5), // Blue
                            icon = "÷",
                            onClick = { onPlayClick("division") }
                        )
                    }
                }

                // Play button
                Button(
                    onClick = { onPlayClick("random") },
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .width(120.dp)
                        .height(48.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2979FF))
                ) {
                    Text(
                        text = "PLAY",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
            }
        }
    }
}

@Composable
fun OperationButton(
    operation: String,
    bgColor: Color,
    icon: String,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = bgColor)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(12.dp)
        ) {
            // Icon
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .background(Color.White.copy(alpha = 0.3f), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = icon,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }

            Spacer(modifier = Modifier.height(4.dp))

            // Operation name
            Text(
                text = operation,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
    }
}

// Colormix Dialog
@Composable
fun ColormixDialog(
    onDismiss: () -> Unit,
    onPlayClick: () -> Unit
) {
    Dialog(onDismissRequest = onDismiss) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFFF9800)) // Orange background
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Title
                Text(
                    text = "COLORMIX",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )

                // Instructions
                Text(
                    text = "A color will show on top. Pick the correct color name from the choices below. " +
                            "Match the color, not the word! " +
                            "Get as many right as you can!",
                    fontSize = 14.sp,
                    color = Color.White,
                    textAlign = TextAlign.Center
                )

                // Image placeholder
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp)
                        .background(Color.White.copy(alpha = 0.2f), RoundedCornerShape(12.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Game Preview",
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                }

                // Play button
                Button(
                    onClick = { onPlayClick() },
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .width(120.dp)
                        .height(48.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF57C00))
                ) {
                    Text(
                        text = "PLAY",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
            }
        }
    }
}

// TickTocky Dialog
@Composable
fun TickTockyDialog(
    onDismiss: () -> Unit,
    onPlayClick: () -> Unit
) {
    Dialog(onDismissRequest = onDismiss) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFF4CAF50)) // Green background
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Title
                Text(
                    text = "TICKTOCKY",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )

                // Instructions
                Text(
                    text = "A clock will show on the screen. Choose the correct time from the options below." +
                            " Look carefully at the hour and minute hands!" +
                            "Try to get as many correct as you can!",
                    fontSize = 14.sp,
                    color = Color.White,
                    textAlign = TextAlign.Center
                )

                // Image placeholder
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp)
                        .background(Color.White.copy(alpha = 0.2f), RoundedCornerShape(12.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Game Preview",
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                }

                // Play button
                Button(
                    onClick = { onPlayClick() },
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .width(120.dp)
                        .height(48.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF388E3C))
                ) {
                    Text(
                        text = "PLAY",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
            }
        }
    }
}

// Oddle Dialog
@Composable
fun OddleDialog(
    onDismiss: () -> Unit,
    onPlayClick: () -> Unit
) {
    Dialog(onDismissRequest = onDismiss) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFF9C27B0)) // Purple background
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Title
                Text(
                    text = "ODDLE",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )

                // Instructions
                Text(
                    text = "A number will appear on the screen. Choose if it’s Even or Odd." + "Tap the correct answer! See how many you can get right!",
                    fontSize = 14.sp,
                    color = Color.White,
                    textAlign = TextAlign.Center
                )

                // Image placeholder
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp)
                        .background(Color.White.copy(alpha = 0.2f), RoundedCornerShape(12.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Game Preview",
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                }

                // Play button
                Button(
                    onClick = { onPlayClick() },
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .width(120.dp)
                        .height(48.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF7B1FA2))
                ) {
                    Text(
                        text = "PLAY",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
            }
        }
    }
}

// Shapely Dialog
@Composable
fun ShapelyDialog(
    onDismiss: () -> Unit,
    onPlayClick: () -> Unit
) {
    Dialog(onDismissRequest = onDismiss) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFE91E63)) // Pink background
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Title
                Text(
                    text = "SHAPELY",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )

                // Instructions
                Text(
                    text = "A shape will appear on the screen. Pick the correct name of the shape from the choices below.",
                    fontSize = 14.sp,
                    color = Color.White,
                    textAlign = TextAlign.Center
                )

                Text(
                    text = "Tap the right answer! Try to get as many correct as you can!",
                    fontSize = 14.sp,
                    color = Color.White,
                    textAlign = TextAlign.Center
                )

                // Image placeholder
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp)
                        .background(Color.White.copy(alpha = 0.2f), RoundedCornerShape(12.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Game Preview",
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                }

                // Play button
                Button(
                    onClick = { onPlayClick() },
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .width(120.dp)
                        .height(48.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFC2185B))
                ) {
                    Text(
                        text = "PLAY",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
            }
        }
    }
}

// Numline Dialog
@Composable
fun NumlineDialog(
    onDismiss: () -> Unit,
    onPlayClick: () -> Unit
) {
    Dialog(onDismissRequest = onDismiss) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFF3F51B5)) // Indigo background
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Title
                Text(
                    text = "NUMLINE",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )

                // Instructions
                Text(
                    text = "A series of numbers will be given. " +
                            "Your goal is to arrange them in ascending order by picking the correct order from the choices below. " + "" +
                            "CHOOSE WISELY!",
                    fontSize = 14.sp,
                    color = Color.White,
                    textAlign = TextAlign.Center
                )

                // Image placeholder
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp)
                        .background(Color.White.copy(alpha = 0.2f), RoundedCornerShape(12.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Game Preview",
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                }

                // Play button
                Button(
                    onClick = { onPlayClick() },
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .width(120.dp)
                        .height(48.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF303F9F))
                ) {
                    Text(
                        text = "PLAY",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
            }
        }
    }
}

// Example menu items
data class MenuItem(val title: String, val imageRes: Int, val path: String)

val menuItems = listOf(
    MenuItem("Numblast", R.drawable.bg, "numblast"),
    MenuItem("Colormix", R.drawable.bg, "colormix"),
    MenuItem("TickTocky", R.drawable.bg, "ticktocky"),
    MenuItem("Oddle", R.drawable.bg, "oddle"),
    MenuItem("Shapely", R.drawable.bg, "shapely"),
    MenuItem("Numline", R.drawable.bg, "numline")
)

@Preview(showBackground = true)
@Composable
fun PreviewMainMenu() {
    ViewMainMenu()
}

@Preview(showBackground = true)
@Composable
fun PreviewNumblastDialog() {
    NumblastDialog(
        onDismiss = { },
        onPlayClick = { }
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewColormixDialog() {
    ColormixDialog(
        onDismiss = { },
        onPlayClick = { }
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewTickTockyDialog() {
    TickTockyDialog(
        onDismiss = { },
        onPlayClick = { }
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewOddleDialog() {
    OddleDialog(
        onDismiss = { },
        onPlayClick = { }
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewShapelyDialog() {
    ShapelyDialog(
        onDismiss = { },
        onPlayClick = { }
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewNumlineDialog() {
    NumlineDialog(
        onDismiss = { },
        onPlayClick = { }
    )
}