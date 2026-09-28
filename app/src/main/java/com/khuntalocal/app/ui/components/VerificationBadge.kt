package com.khuntalocal.app.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.Groups
import androidx.compose.material.icons.filled.HourglassBottom
import androidx.compose.material.icons.filled.ReportGmailerrorred
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.khuntalocal.app.data.model.VerificationStatus
import com.khuntalocal.app.ui.theme.CommunityBlue
import com.khuntalocal.app.ui.theme.RejectedRed
import com.khuntalocal.app.ui.theme.ReviewAmber
import com.khuntalocal.app.ui.theme.VerifiedGreen

data class StatusStyle(val color: Color, val icon: ImageVector, val label: String)

fun VerificationStatus.style(): StatusStyle = when (this) {
    VerificationStatus.VERIFIED -> StatusStyle(VerifiedGreen, Icons.Filled.Verified, label)
    VerificationStatus.UNDER_REVIEW -> StatusStyle(ReviewAmber, Icons.Filled.HourglassBottom, label)
    VerificationStatus.COMMUNITY_REPORT -> StatusStyle(CommunityBlue, Icons.Filled.Groups, label)
    VerificationStatus.REJECTED -> StatusStyle(RejectedRed, Icons.Filled.Cancel, label)
    VerificationStatus.CORRECTION -> StatusStyle(ReviewAmber, Icons.Filled.ReportGmailerrorred, label)
}

/**
 * Compact status pill shown on every card and detail so the verification state
 * of a story is never ambiguous. A community report never looks like verified news.
 */
@Composable
fun VerificationBadge(
    status: VerificationStatus,
    modifier: Modifier = Modifier,
) {
    val s = status.style()
    Row(
        modifier = modifier
            .background(s.color.copy(alpha = 0.12f), RoundedCornerShape(50))
            .padding(horizontal = 8.dp, vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        Icon(
            imageVector = s.icon,
            contentDescription = null,
            tint = s.color,
            modifier = Modifier.size(14.dp),
        )
        Text(
            text = s.label,
            style = MaterialTheme.typography.labelSmall,
            color = s.color,
        )
    }
}
