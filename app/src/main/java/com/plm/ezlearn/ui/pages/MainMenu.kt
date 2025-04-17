package com.plm.ezlearn.ui.pages

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController


@Composable
fun ViewMainMenu(navController: NavController = rememberNavController()) {

}

@Preview(showBackground = true)
@Composable
fun PreviewMainMenu() {
    ViewMainMenu()
}