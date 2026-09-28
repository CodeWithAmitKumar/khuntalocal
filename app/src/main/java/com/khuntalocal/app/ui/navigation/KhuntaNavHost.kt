package com.khuntalocal.app.ui.navigation

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.khuntalocal.app.ui.screens.detail.NewsDetailScreen
import com.khuntalocal.app.ui.screens.main.MainScaffold
import com.khuntalocal.app.ui.screens.onboarding.OnboardingScreen
import com.khuntalocal.app.ui.screens.report.ReportNewsScreen

@Composable
fun KhuntaApp() {
    val navController = rememberNavController()
    val context = LocalContext.current

    NavHost(
        navController = navController,
        startDestination = Routes.ONBOARDING,
    ) {
        composable(Routes.ONBOARDING) {
            OnboardingScreen(
                onGetStarted = {
                    navController.navigate(Routes.MAIN) {
                        popUpTo(Routes.ONBOARDING) { inclusive = true }
                    }
                },
                onLogin = {
                    navController.navigate(Routes.MAIN) {
                        popUpTo(Routes.ONBOARDING) { inclusive = true }
                    }
                },
            )
        }

        composable(Routes.MAIN) {
            MainScaffold(
                onOpenArticle = { id -> navController.navigate(Routes.detail(id)) },
                onOpenReport = { navController.navigate(Routes.REPORT) },
            )
        }

        composable(Routes.REPORT) {
            ReportNewsScreen(
                onBack = { navController.popBackStack() },
                onSubmitted = {
                    Toast.makeText(
                        context,
                        "Report submitted for review",
                        Toast.LENGTH_LONG,
                    ).show()
                    navController.popBackStack()
                },
            )
        }

        composable(
            route = "${Routes.DETAIL}/{id}",
            arguments = listOf(navArgument("id") { type = NavType.StringType }),
        ) {
            NewsDetailScreen(onBack = { navController.popBackStack() })
        }
    }
}
