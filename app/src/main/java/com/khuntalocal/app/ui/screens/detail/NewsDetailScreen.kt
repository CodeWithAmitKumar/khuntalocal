package com.khuntalocal.app.ui.screens.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.ChatBubbleOutline
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Groups
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Verified
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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.khuntalocal.app.data.model.NewsArticle
import com.khuntalocal.app.data.model.VerificationStatus
import com.khuntalocal.app.ui.components.ImageTagPill
import com.khuntalocal.app.ui.components.NewsImage
import com.khuntalocal.app.ui.components.VerificationBadge
import com.khuntalocal.app.ui.components.compact
import com.khuntalocal.app.ui.theme.BrandGreen
import com.khuntalocal.app.ui.theme.VerifiedGreen

@Composable
fun NewsDetailScreen(
    onBack: () -> Unit,
    viewModel: NewsDetailViewModel = viewModel(factory = NewsDetailViewModel.Factory),
) {
    val article = viewModel.article
    if (article == null) {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("Story not found", style = MaterialTheme.typography.titleMedium)
        }
        return
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
    ) {
        Box(modifier = Modifier.fillMaxWidth().height(240.dp)) {
            NewsImage(
                imageUrl = article.imageUrl,
                category = article.category,
                modifier = Modifier.fillMaxSize(),
            )
            CircleIconButton(
                icon = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Back",
                onClick = onBack,
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .statusBarsPadding()
                    .padding(12.dp),
            )
            CircleIconButton(
                icon = Icons.Filled.Share,
                contentDescription = "Share",
                onClick = { },
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .statusBarsPadding()
                    .padding(12.dp),
            )
            ImageTagPill(
                category = article.category,
                isBreaking = article.isBreaking,
                modifier = Modifier.align(Alignment.BottomStart).padding(12.dp),
            )
        }

        Column(
            modifier = Modifier
                .padding(16.dp)
                .navigationBarsPadding(),
        ) {
            VerificationBadge(status = article.status)
            Spacer(Modifier.height(10.dp))
            Text(
                text = article.headline,
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
            )
            Spacer(Modifier.height(8.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Filled.LocationOn,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.size(14.dp),
                )
                Spacer(Modifier.width(3.dp))
                Text(
                    text = "${article.locationName} · ${article.timeAgo} · ${article.views.compact()} views",
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }

            Spacer(Modifier.height(16.dp))
            ReporterRow(article)

            Spacer(Modifier.height(16.dp))
            Text(
                text = article.body,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface,
            )

            Spacer(Modifier.height(20.dp))
            VerificationPanel(article)

            Spacer(Modifier.height(20.dp))
            ActionBar(article)
            Spacer(Modifier.height(16.dp))
        }
    }
}

@Composable
private fun ReporterRow(article: NewsArticle) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .background(BrandGreen.copy(alpha = 0.15f), CircleShape),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = article.reporter.name.take(1).uppercase(),
                style = MaterialTheme.typography.titleMedium,
                color = BrandGreen,
            )
        }
        Spacer(Modifier.width(10.dp))
        Column {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = article.reporter.name,
                    style = MaterialTheme.typography.titleSmall,
                )
                if (article.reporter.isVerifiedReporter) {
                    Spacer(Modifier.width(4.dp))
                    Icon(
                        imageVector = Icons.Filled.Verified,
                        contentDescription = "Verified reporter",
                        tint = VerifiedGreen,
                        modifier = Modifier.size(15.dp),
                    )
                }
            }
            Text(
                text = if (article.reporter.isVerifiedReporter) {
                    "Verified Reporter · ${article.reporter.location}"
                } else {
                    "Community member · ${article.reporter.location}"
                },
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        }
    }
}

@Composable
private fun VerificationPanel(article: NewsArticle) {
    val steps: List<Pair<String, Boolean>> = when (article.status) {
        VerificationStatus.VERIFIED -> listOf(
            "Report submitted" to true,
            "Location verified" to true,
            "Media reviewed" to true,
            "Editorial review completed" to true,
        )
        VerificationStatus.COMMUNITY_REPORT -> listOf(
            "Report submitted" to true,
            "Submitted by a community member" to true,
            "Editorial verification pending" to false,
        )
        VerificationStatus.UNDER_REVIEW -> listOf(
            "Report submitted" to true,
            "Automated checks running" to true,
            "Awaiting editorial review" to false,
        )
        VerificationStatus.CORRECTION -> listOf(
            "Correction issued by editorial team" to true,
        )
        VerificationStatus.REJECTED -> listOf(
            "This report could not be verified" to false,
        )
    }

    Surface(
        shape = RoundedCornerShape(16.dp),
        color = MaterialTheme.colorScheme.surface,
        border = androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
        modifier = Modifier.fillMaxWidth(),
    ) {
        Column(Modifier.padding(16.dp)) {
            Text(
                text = "Verification",
                style = MaterialTheme.typography.titleSmall,
            )
            if (article.relatedReportsCount > 0) {
                Spacer(Modifier.height(4.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Filled.Groups,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(15.dp),
                    )
                    Spacer(Modifier.width(4.dp))
                    Text(
                        text = "${article.relatedReportsCount} community reports related to this incident",
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                }
            }
            Spacer(Modifier.height(10.dp))
            steps.forEach { (label, done) ->
                Row(
                    modifier = Modifier.padding(vertical = 3.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(
                        imageVector = Icons.Filled.CheckCircle,
                        contentDescription = null,
                        tint = if (done) VerifiedGreen else MaterialTheme.colorScheme.outline,
                        modifier = Modifier.size(16.dp),
                    )
                    Spacer(Modifier.width(8.dp))
                    Text(
                        text = label,
                        style = MaterialTheme.typography.bodyMedium,
                        color = if (done) MaterialTheme.colorScheme.onSurface
                        else MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                }
            }
        }
    }
}

@Composable
private fun ActionBar(article: NewsArticle) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        ActionButton(Icons.Filled.FavoriteBorder, "${article.likes.compact()}")
        ActionButton(Icons.Filled.ChatBubbleOutline, "${article.comments.compact()}")
        ActionButton(Icons.Filled.BookmarkBorder, "Save")
        ActionButton(Icons.Filled.Share, "Share")
    }
}

@Composable
private fun ActionButton(icon: androidx.compose.ui.graphics.vector.ImageVector, label: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            tint = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.size(20.dp),
        )
        Spacer(Modifier.width(6.dp))
        Text(
            text = label,
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
    }
}

@Composable
private fun CircleIconButton(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    contentDescription: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Surface(
        onClick = onClick,
        shape = CircleShape,
        color = Color.Black.copy(alpha = 0.4f),
        modifier = modifier.size(40.dp),
    ) {
        Box(contentAlignment = Alignment.Center) {
            Icon(icon, contentDescription = contentDescription, tint = Color.White)
        }
    }
}
