package com.khuntalocal.app.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Explore
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector

/** Root-level routes. */
object Routes {
    const val ONBOARDING = "onboarding"
    const val MAIN = "main"
    const val REPORT = "report"
    const val DETAIL = "detail" // detail/{id}

    fun detail(id: String) = "$DETAIL/$id"
}

/**
 * The four bottom-navigation tabs (Report is a center action, not a tab).
 * A single icon per tab; the bar shows selection through color and label weight.
 */
enum class TabDestination(
    val route: String,
    val label: String,
    val icon: ImageVector,
) {
    HOME("tab_home", "Home", Icons.Filled.Home),
    DISCOVER("tab_discover", "Discover", Icons.Filled.Explore),
    ALERTS("tab_alerts", "Alerts", Icons.Filled.Notifications),
    PROFILE("tab_profile", "Profile", Icons.Filled.Person),
}
