package com.khuntalocal.app.ui.screens.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.khuntalocal.app.ui.navigation.TabDestination
import com.khuntalocal.app.ui.screens.alerts.AlertsScreen
import com.khuntalocal.app.ui.screens.discover.DiscoverScreen
import com.khuntalocal.app.ui.screens.home.HomeScreen
import com.khuntalocal.app.ui.screens.profile.ProfileScreen

@Composable
fun MainScaffold(
    onOpenArticle: (String) -> Unit,
    onOpenReport: () -> Unit,
) {
    val tabNavController = rememberNavController()
    val backStackEntry by tabNavController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route

    fun switchTab(destination: TabDestination) {
        tabNavController.navigate(destination.route) {
            popUpTo(tabNavController.graph.startDestinationId) { saveState = true }
            launchSingleTop = true
            restoreState = true
        }
    }

    Scaffold(
        bottomBar = {
            KhuntaBottomBar(
                currentRoute = currentRoute,
                onTabSelected = ::switchTab,
                onReport = onOpenReport,
            )
        },
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = innerPadding.calculateTopPadding()),
        ) {
            NavHost(
                navController = tabNavController,
                startDestination = TabDestination.HOME.route,
            ) {
                composable(TabDestination.HOME.route) {
                    HomeScreen(
                        onOpenArticle = onOpenArticle,
                        onOpenSearch = { switchTab(TabDestination.DISCOVER) },
                        onOpenAlerts = { switchTab(TabDestination.ALERTS) },
                        contentPadding = innerPadding,
                    )
                }
                composable(TabDestination.DISCOVER.route) {
                    DiscoverScreen(
                        onOpenArticle = onOpenArticle,
                        contentPadding = innerPadding,
                    )
                }
                composable(TabDestination.ALERTS.route) {
                    AlertsScreen(contentPadding = innerPadding)
                }
                composable(TabDestination.PROFILE.route) {
                    ProfileScreen(
                        onOpenArticle = onOpenArticle,
                        onStartReport = onOpenReport,
                        contentPadding = innerPadding,
                    )
                }
            }
        }
    }
}
