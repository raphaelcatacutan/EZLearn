package com.plm.ezlearn.ui.pages

import androidx.activity.compose.BackHandler
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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.plm.ezlearn.ui.components.ComponentThreeDContainer
import com.plm.ezlearn.ui.components.ComponentOutlinedText
import com.plm.ezlearn.ui.theme.chalkboardFont


@Composable
fun ViewMainMenu(navController: NavController = rememberNavController()) {
    var showInstructionDialog by remember { mutableStateOf(false) }
    var instructionMenu by remember {mutableStateOf(MenuItem("", 0, "", ""))}

    BackHandler(enabled = false) { }

    Image(
        painter = painterResource(id = R.drawable.bg),
        contentDescription = null,
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.Crop
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 30.dp, horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.boy), // your drawable
                    contentDescription = "Avatar",
                    modifier = Modifier
                        .size(50.dp)
                        .clip(CircleShape)
                        .border(1.dp, Color.White, CircleShape)
                )
                Spacer(modifier = Modifier.width(8.dp))
                ComponentOutlinedText(
                    "Hello, Raphael",
                    fillColor = Color.White,
                    outlineColor = Color.Black,
                    fontSize = 25.sp,
                    fontFamily = chalkboardFont,
                    randomizeColor = false,
                    outlineDrawStyle = Stroke(width = 7f),
                    letterSpacing = 3.sp
                )
            }

            ComponentThreeDContainer(
                modifier = Modifier
                    .width(90.dp)
                    .height(40.dp),
                backgroundColor = Color(0xFFD7A700),
                shadowColor = Color(0xFF5A3A00),
                cornerRadius = 10.dp
            ) {
                Text(
                    text = "Level 1",
                    style = MaterialTheme.typography.bodyLarge.copy(color = Color.White),
                    fontSize = 23.sp,
                    modifier = Modifier
                        .padding(top = 3.dp)
                )
            }
        }

        // Menu buttons in grid
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(8.dp),
            horizontalArrangement = Arrangement.spacedBy(15.dp),
            verticalArrangement = Arrangement.spacedBy(15.dp),
            modifier = Modifier
                .fillMaxSize()
                .offset(y = 150.dp)
        ) {
            items(menuItems.size) { index ->
                val item = menuItems[index]
                ComponentMenuItemCard(
                    title = item.title,
                    imageRes = item.imageRes,
                    onClick = {
                        instructionMenu = item
                        showInstructionDialog = true
                    },
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                        .height(150.dp)
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

    Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth() ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo",
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .size(260.dp)
                .offset(y = 30.dp)
        )
    }
}

@Composable
fun ComponentMenuItemCard(
    title: String,
    imageRes: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth() // Ensures the card spans the full grid cell width
            .height(150.dp), // Set a consistent height for the card
        shape = RoundedCornerShape(18.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp, pressedElevation = 0.dp, focusedElevation = 0.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        onClick = onClick
    ) {
        Box(
            modifier = Modifier.fillMaxSize(), // Fill all available space
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = title,
                contentScale = ContentScale.Crop, // Zooms in to fill all pixels and crops if needed
                modifier = Modifier.fillMaxSize() // Ensures the image fills the card entirely
            )
            Text(
                text = title,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .align(Alignment.BottomCenter) // Position the text at the bottom
                    .padding(8.dp) // Add padding around the text
            )
        }
    }
}

// Example menu items
data class MenuItem(val title: String, val imageRes: Int, val path: String, val instruction: String)

val menuItems = listOf(
    MenuItem("", R.drawable.numblast, "numblast", "Choose a category: Addition, Subtraction, Multiplication, or Division. Answer as many questions as you can!"),
    MenuItem("", R.drawable.colormix, "colormix", "Match the color, not the word! Get as many right as you can!"),
    MenuItem("", R.drawable.oddle, "oddle", "A number will appear on the screen. Choose if it’s Even or Odd."),
    MenuItem("", R.drawable.shapely, "shapely", "A shape will appear on the screen. Pick the correct name of the shape."),
    MenuItem("", R.drawable.numline, "numline", "Arrange the given numbers in ascending order. Pick the correct sequence!"),
    MenuItem("", R.drawable.ticktocky, "ticktocky", "A clock will show on the screen. Choose the correct time from the options below!")
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
            ComponentThreeDContainer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(415.dp)
                    .padding(top = 110.dp, bottom = 50.dp),
                backgroundColor = Color(0xFFADC8D9),
                shadowColor = Color(0xFF797C8C),
                cornerRadius = 15.dp,
                isPushable = false,
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 30.dp)
                        .padding(top = 25.dp)
                ) {
                    Text(
                        text = message,
                        fontSize = 25.sp,
                        textAlign = TextAlign.Center,
                        color = Color.Black
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    ComponentThreeDContainer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(55.dp),
                        backgroundColor = Color(0xFF9AF386),
                        shadowColor = Color(0xFF136B01),
                        cornerRadius = 15.dp,
                        isPushable = true,
                        onClick = onClick
                    ) {
                        Text("Play", fontSize = 22.sp, color = Color.Black)
                    }
                }
            }

            // Image floating above the dialog
            Box(
                modifier = Modifier
                    .size(120.dp)
                    .align(Alignment.TopCenter)
                    .offset(y = 15.dp)
                    .shadow(4.dp, shape = RoundedCornerShape(8.dp)) // Applies rounded shadow
                    .background(Color.White, shape = RoundedCornerShape(8.dp))
                    .padding(4.dp) // Optional padding within the container
            ) {
                Image(
                    painter = painterResource(id = imageRes),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }
        }
    }
}