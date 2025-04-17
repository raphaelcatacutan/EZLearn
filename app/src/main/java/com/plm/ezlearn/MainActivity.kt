package com.plm.ezlearn

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.plm.ezlearn.ui.pages.ViewColormix
import com.plm.ezlearn.ui.pages.ViewMainMenu
import com.plm.ezlearn.ui.pages.ViewNumblast
import com.plm.ezlearn.ui.pages.ViewNumline
import com.plm.ezlearn.ui.pages.ViewOddle
import com.plm.ezlearn.ui.pages.ViewShapely
import com.plm.ezlearn.ui.pages.ViewTicktoky
import com.plm.ezlearn.ui.theme.EZLearnTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EZLearnTheme {
                AppNavigation()
            }
        }
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "main-menu") {
        composable("main-menu") { ViewMainMenu(navController) }
        // Mini Games
        composable("colormix") { ViewColormix(navController) }
        composable("numblast") { ViewNumblast(navController) }
        composable("numline") { ViewNumline(navController) }
        composable("oddle") { ViewOddle(navController) }
        composable("shapely") { ViewShapely(navController) }
        composable("ticktocky") { ViewTicktoky(navController) }
    }

    AnimatedContent(targetState = navController.currentBackStackEntry) { _ ->
        // Pang animate ng pages
        AnimatedVisibility(visible = true, enter = fadeIn(), exit = fadeOut()) {
            //customize animations for each composable
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    EZLearnTheme {
        AppNavigation()
    }
}