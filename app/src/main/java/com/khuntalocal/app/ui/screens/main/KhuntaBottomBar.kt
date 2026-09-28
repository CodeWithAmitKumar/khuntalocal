package com.khuntalocal.app.ui.screens.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.semantics.Role
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.clickable
import com.khuntalocal.app.ui.navigation.TabDestination
import com.khuntalocal.app.ui.theme.BrandGreen

@Composable
fun KhuntaBottomBar(
    currentRoute: String?,
    onTabSelected: (TabDestination) -> Unit,
    onReport: () -> Unit,
) {
    Surface(
        color = MaterialTheme.colorScheme.surface,
        tonalElevation = 3.dp,
        shadowElevation = 12.dp,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .navigationBarsPadding()
                .height(64.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            BarItem(TabDestination.HOME, currentRoute, onTabSelected, Modifier.weight(1f))
            BarItem(TabDestination.DISCOVER, currentRoute, onTabSelected, Modifier.weight(1f))
            ReportButton(onReport, Modifier.weight(1f))
            BarItem(TabDestination.ALERTS, currentRoute, onTabSelected, Modifier.weight(1f), badgeCount = 3)
            BarItem(TabDestination.PROFILE, currentRoute, onTabSelected, Modifier.weight(1f))
        }
    }
}

@Composable
private fun BarItem(
    destination: TabDestination,
    currentRoute: String?,
    onTabSelected: (TabDestination) -> Unit,
    modifier: Modifier = Modifier,
    badgeCount: Int = 0,
) {
    val selected = currentRoute == destination.route
    val tint = if (selected) BrandGreen else MaterialTheme.colorScheme.onSurfaceVariant
    Column(
        modifier = modifier
            .selectable(
                selected = selected,
                role = Role.Tab,
                onClick = { onTabSelected(destination) },
            )
            .padding(vertical = 6.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        BadgedBox(
            badge = { if (badgeCount > 0) Badge { Text(badgeCount.toString()) } },
        ) {
            Icon(
                imageVector = destination.icon,
                contentDescription = destination.label,
                tint = tint,
                modifier = Modifier.size(24.dp),
            )
        }
        Spacer(Modifier.height(3.dp))
        Text(
            text = destination.label,
            style = MaterialTheme.typography.labelSmall,
            color = tint,
            fontWeight = if (selected) FontWeight.SemiBold else FontWeight.Normal,
        )
    }
}

@Composable
private fun ReportButton(onReport: () -> Unit, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Column(
            modifier = Modifier
                .offset(y = (-8).dp)
                .size(52.dp)
                .clip(CircleShape)
                .background(BrandGreen)
                .clickable(onClick = onReport),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Icon(
                imageVector = Icons.Filled.Add,
                contentDescription = "Report",
                tint = Color.White,
                modifier = Modifier.size(28.dp),
            )
        }
        Text(
            text = "Report",
            style = MaterialTheme.typography.labelSmall,
            color = BrandGreen,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.offset(y = (-6).dp),
        )
    }
}
