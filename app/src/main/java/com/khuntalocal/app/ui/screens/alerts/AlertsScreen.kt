package com.khuntalocal.app.ui.screens.alerts

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.ChatBubble
import androidx.compose.material.icons.filled.HourglassBottom
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Newspaper
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material.icons.filled.WorkspacePremium
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.khuntalocal.app.data.model.AlertItem
import com.khuntalocal.app.data.model.AlertType
import com.khuntalocal.app.ui.theme.BrandGreen
import com.khuntalocal.app.ui.theme.BreakingRed
import com.khuntalocal.app.ui.theme.CommunityBlue
import com.khuntalocal.app.ui.theme.RejectedRed
import com.khuntalocal.app.ui.theme.ReviewAmber
import com.khuntalocal.app.ui.theme.VerifiedGreen

@Composable
fun AlertsScreen(
    contentPadding: PaddingValues,
    viewModel: AlertsViewModel = viewModel(factory = AlertsViewModel.Factory),
) {
    val alerts by viewModel.alerts.collectAsStateWithLifecycle()

    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(
            bottom = contentPadding.calculateBottomPadding() + 16.dp,
        ),
    ) {
        item {
            Text(
                text = "Alerts",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 16.dp, top = 16.dp, bottom = 8.dp),
            )
        }
        items(alerts, key = { it.id }) { alert ->
            AlertRow(alert)
        }
    }
}

@Composable
private fun AlertRow(alert: AlertItem) {
    val (icon, color) = alert.type.style()
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                if (alert.unread) BrandGreen.copy(alpha = 0.05f) else Color.Transparent,
            )
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.Top,
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .background(color.copy(alpha = 0.14f), CircleShape),
            contentAlignment = Alignment.Center,
        ) {
            Icon(icon, contentDescription = null, tint = color, modifier = Modifier.size(20.dp))
        }
        Spacer(Modifier.width(12.dp))
        Column(Modifier.weight(1f)) {
            Text(
                text = alert.title,
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.SemiBold,
            )
            Spacer(Modifier.size(2.dp))
            Text(
                text = alert.body,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
            Spacer(Modifier.size(4.dp))
            Text(
                text = alert.timeAgo,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        }
        if (alert.unread) {
            Box(
                modifier = Modifier
                    .size(8.dp)
                    .background(BrandGreen, CircleShape),
            )
        }
    }
}

private fun AlertType.style(): Pair<ImageVector, Color> = when (this) {
    AlertType.BREAKING -> Icons.Filled.Bolt to BreakingRed
    AlertType.NEARBY -> Icons.Filled.LocationOn to CommunityBlue
    AlertType.NEW_STORY -> Icons.Filled.Newspaper to BrandGreen
    AlertType.COMMENT_REPLY -> Icons.Filled.ChatBubble to Color(0xFF7C3AED)
    AlertType.NEWS_VERIFIED -> Icons.Filled.Verified to VerifiedGreen
    AlertType.NEWS_REJECTED -> Icons.Filled.Cancel to RejectedRed
    AlertType.VERIFICATION_REQUIRED -> Icons.Filled.HourglassBottom to ReviewAmber
    AlertType.REPORTER_APPROVED -> Icons.Filled.WorkspacePremium to BrandGreen
}
