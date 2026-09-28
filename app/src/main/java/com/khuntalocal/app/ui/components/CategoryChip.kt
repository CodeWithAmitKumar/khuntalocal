package com.khuntalocal.app.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.khuntalocal.app.data.model.Category

/** A vertical icon-tile + label used as a horizontal category quick filter. */
@Composable
fun CategoryChip(
    category: Category,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val style = category.style()
    Column(
        modifier = modifier.padding(end = 14.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(6.dp),
    ) {
        Surface(
            onClick = onClick,
            shape = RoundedCornerShape(16.dp),
            color = if (selected) style.color else style.color.copy(alpha = 0.12f),
        ) {
            Icon(
                imageVector = style.icon,
                contentDescription = category.label,
                tint = if (selected) Color.White else style.color,
                modifier = Modifier
                    .background(Color.Transparent)
                    .padding(14.dp)
                    .size(24.dp),
            )
        }
        Text(
            text = category.label,
            style = MaterialTheme.typography.labelSmall,
            fontWeight = if (selected) FontWeight.Bold else FontWeight.Medium,
            color = if (selected) style.color else MaterialTheme.colorScheme.onSurfaceVariant,
        )
    }
}
