package com.khuntalocal.app.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.khuntalocal.app.data.model.Category
import com.khuntalocal.app.ui.theme.BreakingRed

/** Solid rounded pill overlaid on a story image: red "Breaking" or the category. */
@Composable
fun ImageTagPill(
    category: Category,
    isBreaking: Boolean,
    modifier: Modifier = Modifier,
) {
    val color = if (isBreaking) BreakingRed else category.style().color
    Row(
        modifier = modifier
            .background(color, RoundedCornerShape(50))
            .padding(horizontal = 8.dp, vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(3.dp),
    ) {
        if (isBreaking) {
            Icon(
                imageVector = Icons.Filled.Bolt,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(12.dp),
            )
        }
        Text(
            text = if (isBreaking) "Breaking" else category.label,
            style = MaterialTheme.typography.labelSmall,
            color = Color.White,
        )
    }
}
