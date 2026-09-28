package com.khuntalocal.app.ui.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.khuntalocal.app.data.model.Category
import com.khuntalocal.app.ui.components.BrandLogo
import com.khuntalocal.app.ui.components.BreakingNewsBanner
import com.khuntalocal.app.ui.components.CategoryChip
import com.khuntalocal.app.ui.components.NewsCard
import com.khuntalocal.app.ui.theme.BrandGreen
import com.khuntalocal.app.ui.theme.BrandNavy

@Composable
fun HomeScreen(
    onOpenArticle: (String) -> Unit,
    onOpenSearch: () -> Unit,
    onOpenAlerts: () -> Unit,
    contentPadding: PaddingValues,
    viewModel: HomeViewModel = viewModel(factory = HomeViewModel.Factory),
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(
            top = 0.dp,
            bottom = contentPadding.calculateBottomPadding() + 16.dp,
        ),
    ) {
        item {
            HomeTopBar(
                location = state.location,
                unreadAlerts = state.unreadAlerts,
                onOpenSearch = onOpenSearch,
                onOpenAlerts = onOpenAlerts,
            )
        }

        item {
            BreakingNewsBanner(
                items = state.breaking,
                onClick = { onOpenArticle(it.id) },
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
            )
        }

        item {
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
            ) {
                items(Category.quickFilters) { category ->
                    CategoryChip(
                        category = category,
                        selected = state.selectedCategory == category,
                        onClick = { viewModel.onCategorySelected(category) },
                    )
                }
            }
        }

        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 4.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = "Top Stories",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.weight(1f),
                )
                Row(
                    modifier = Modifier,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = "View All",
                        style = MaterialTheme.typography.labelLarge,
                        color = BrandGreen,
                    )
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                        contentDescription = null,
                        tint = BrandGreen,
                        modifier = Modifier.size(16.dp),
                    )
                }
            }
        }

        items(state.topStories, key = { it.id }) { article ->
            NewsCard(
                article = article,
                onClick = { onOpenArticle(article.id) },
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 6.dp),
            )
        }
    }
}

@Composable
private fun HomeTopBar(
    location: String,
    unreadAlerts: Int,
    onOpenSearch: () -> Unit,
    onOpenAlerts: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        IconButton(onClick = { /* open drawer */ }) {
            Icon(Icons.Filled.Menu, contentDescription = "Menu")
        }
        BrandLogo(size = 30.dp)
        Spacer(Modifier.width(8.dp))
        Column {
            Text(
                text = buildAnnotatedString {
                    withStyle(SpanStyle(color = BrandNavy)) { append("Khunta") }
                    withStyle(SpanStyle(color = BrandGreen)) { append("Local") }
                },
                fontSize = 18.sp,
                fontWeight = FontWeight.ExtraBold,
            )
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Filled.LocationOn,
                    contentDescription = null,
                    tint = BrandGreen,
                    modifier = Modifier.size(13.dp),
                )
                Spacer(Modifier.width(2.dp))
                Text(
                    text = location,
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
                Icon(
                    imageVector = Icons.Filled.ArrowDropDown,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.size(16.dp),
                )
            }
        }
        Spacer(Modifier.weight(1f))
        IconButton(onClick = onOpenSearch) {
            Icon(Icons.Filled.Search, contentDescription = "Search")
        }
        IconButton(onClick = onOpenAlerts) {
            BadgedBox(
                badge = {
                    if (unreadAlerts > 0) {
                        Badge { Text(unreadAlerts.toString()) }
                    }
                },
            ) {
                Icon(Icons.Filled.Notifications, contentDescription = "Notifications")
            }
        }
    }
}
