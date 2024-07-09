package com.example.eatnow

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.eatnow.db.Food
import com.example.eatnow.screens.AppViewModel
import com.example.eatnow.screens.CartScreen
import com.example.eatnow.screens.ProdList
import com.example.eatnow.screens.StartScreen

enum class MasterApp {
    StartScreen,
    Prod_catalog,
    CartScreen
}

lateinit var whichScreenToGo : String

@Composable
fun MasterApp(
    viewModel: AppViewModel,
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = MasterApp.StartScreen.name
    ) {
        composable(route = MasterApp.StartScreen.name , content = {
            StartScreen(navController)
        })
        composable(route = MasterApp.Prod_catalog.name , content = {
            ProdList(prod_catalog = whichScreenToGo , viewModel = viewModel , navController = navController)
        })

        composable(route = MasterApp.CartScreen.name , content = {
            CartScreen(viewModel = viewModel , navController = navController)
        })
    }
}