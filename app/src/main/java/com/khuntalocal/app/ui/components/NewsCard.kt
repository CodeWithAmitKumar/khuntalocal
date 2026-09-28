package com.khuntalocal.app.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChatBubbleOutline
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.RemoveRedEye
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.khuntalocal.app.data.model.NewsArticle

/** Horizontal story card used in the Top Stories / feed lists. */
@Composable
fun NewsCard(
    article: NewsArticle,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(
        onClick = onClick,
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
        border = androidx.compose.foundation.BorderStroke(
            1.dp, MaterialTheme.colorScheme.outline,
        ),
    ) {
        Row(modifier = Modifier.padding(10.dp)) {
            Box(
                modifier = Modifier
                    .size(width = 112.dp, height = 112.dp),
            ) {
                NewsImage(
                    imageUrl = article.imageUrl,
                    category = article.category,
                    modifier = Modifier.size(width = 112.dp, height = 112.dp),
                    cornerRadius = 14,
                )
                ImageTagPill(
                    category = article.category,
                    isBreaking = article.isBreaking,
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(6.dp),
                )
            }

            Spacer(Modifier.width(12.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = article.category.label,
                    style = MaterialTheme.typography.labelMedium,
                    color = article.category.style().color,
                )
                Spacer(Modifier.height(2.dp))
                Text(
                    text = article.headline,
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onSurface,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                )
                Spacer(Modifier.height(6.dp))
                VerificationBadge(status = article.status)
                Spacer(Modifier.height(6.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Filled.LocationOn,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.size(13.dp),
                    )
                    Spacer(Modifier.width(2.dp))
                    Text(
                        text = "${article.locationName} · ${article.timeAgo}",
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                }
                Spacer(Modifier.height(8.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(14.dp)) {
                    StatItem(Icons.Filled.RemoveRedEye, article.views)
                    StatItem(Icons.Filled.FavoriteBorder, article.likes)
                    StatItem(Icons.Filled.ChatBubbleOutline, article.comments)
                }
            }
        }
    }
}
