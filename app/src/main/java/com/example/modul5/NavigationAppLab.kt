package com.example.modul5

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.modul5.screen.AboutScreen
import com.example.modul5.screen.DetailScreen
import com.example.modul5.screen.HomeScreen
import com.example.modul5.screen.ProfileScreen

@Composable
fun NavigationLabApp() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.HOME
    ) {
        composable(Routes.HOME) {
            HomeScreen(
                onOpenDetail = { id ->
                    navController.navigate(Routes.detail(id))
                },
                onOpenProfile = {
                    navController.navigate(Routes.PROFILE)
                },
                onOpenAbout = {
                    navController.navigate(Routes.ABOUT) // 3. Navigasi menuju About
                }
            )
        }
        composable(
            route = Routes.DETAIL,
            arguments = listOf(
                navArgument("studentId") {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->
            val studentId = backStackEntry.arguments?.getInt("studentId") ?: 0
            DetailScreen(
                studentId = studentId,
                onBack = { navController.popBackStack() },
                onOpenProfile = { navController.navigate(Routes.PROFILE) }
            )
        }
        // Destination Profile
        composable(Routes.PROFILE) {
            ProfileScreen(
                onBack = { navController.popBackStack() }
            )
        }
        // 3. Destination About pada NavHost
        composable(Routes.ABOUT) {
            AboutScreen(
                onBack = { navController.popBackStack() }
            )
        }
    }
}