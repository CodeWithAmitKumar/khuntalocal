package com.khuntalocal.app.ui.screens.report

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.MyLocation
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.Videocam
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.khuntalocal.app.data.model.Category
import com.khuntalocal.app.data.model.Language
import com.khuntalocal.app.data.model.ReportSource
import com.khuntalocal.app.ui.theme.BrandGreen

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun ReportNewsScreen(
    onBack: () -> Unit,
    onSubmitted: () -> Unit,
    viewModel: ReportViewModel = viewModel(factory = ReportViewModel.Factory),
) {
    val draft by viewModel.draft.collectAsStateWithLifecycle()
    val submitState by viewModel.submitState.collectAsStateWithLifecycle()

    androidx.compose.runtime.LaunchedEffect(submitState) {
        if (submitState is SubmitState.Success) onSubmitted()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Report News", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background,
                ),
            )
        },
        containerColor = MaterialTheme.colorScheme.background,
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp),
        ) {
            Text(
                text = "What's happening around you?",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
            Spacer(Modifier.height(16.dp))

            // Content type
            SectionLabel("Add content")
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                MediaToggle(
                    icon = Icons.Filled.Image,
                    label = "Photo",
                    selected = draft.hasPhoto,
                    onClick = viewModel::togglePhoto,
                    modifier = Modifier.weight(1f),
                )
                MediaToggle(
                    icon = Icons.Filled.Videocam,
                    label = "Video",
                    selected = draft.hasVideo,
                    onClick = viewModel::toggleVideo,
                    modifier = Modifier.weight(1f),
                )
            }

            Spacer(Modifier.height(20.dp))
            SectionLabel("Headline")
            OutlinedTextField(
                value = draft.headline,
                onValueChange = viewModel::onHeadlineChange,
                placeholder = { Text("Write a short headline") },
                singleLine = true,
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.fillMaxWidth(),
            )

            Spacer(Modifier.height(16.dp))
            SectionLabel("Description")
            OutlinedTextField(
                value = draft.description,
                onValueChange = viewModel::onDescriptionChange,
                placeholder = { Text("Tell people what happened…") },
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp),
            )

            Spacer(Modifier.height(20.dp))
            SectionLabel("Category")
            FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Category.selectable.forEach { category ->
                    FilterChip(
                        selected = draft.category == category,
                        onClick = { viewModel.onCategoryChange(category) },
                        label = { Text(category.label) },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = BrandGreen.copy(alpha = 0.15f),
                            selectedLabelColor = BrandGreen,
                        ),
                    )
                }
            }

            Spacer(Modifier.height(20.dp))
            SectionLabel("Location")
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    imageVector = Icons.Filled.MyLocation,
                    contentDescription = null,
                    tint = BrandGreen,
                    modifier = Modifier.size(18.dp),
                )
                Spacer(Modifier.width(8.dp))
                Text(
                    text = "Use current location",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.weight(1f),
                )
                Switch(
                    checked = draft.useCurrentLocation,
                    onCheckedChange = viewModel::onUseCurrentLocationChange,
                )
            }
            if (!draft.useCurrentLocation) {
                Spacer(Modifier.height(8.dp))
                OutlinedTextField(
                    value = draft.location,
                    onValueChange = viewModel::onLocationChange,
                    placeholder = { Text("Enter location (village / panchayat)") },
                    singleLine = true,
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.fillMaxWidth(),
                )
            }

            Spacer(Modifier.height(20.dp))
            SectionLabel("Language")
            FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Language.entries.forEach { language ->
                    FilterChip(
                        selected = draft.language == language,
                        onClick = { viewModel.onLanguageChange(language) },
                        label = { Text(language.label) },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = BrandGreen.copy(alpha = 0.15f),
                            selectedLabelColor = BrandGreen,
                        ),
                    )
                }
            }

            Spacer(Modifier.height(20.dp))
            SectionLabel("How did you learn about this?")
            ReportSource.entries.forEach { source ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .selectable(
                            selected = draft.source == source,
                            onClick = { viewModel.onSourceChange(source) },
                        )
                        .padding(vertical = 2.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    RadioButton(
                        selected = draft.source == source,
                        onClick = { viewModel.onSourceChange(source) },
                    )
                    Text(source.label, style = MaterialTheme.typography.bodyMedium)
                }
            }

            if (submitState is SubmitState.Error) {
                Spacer(Modifier.height(8.dp))
                Text(
                    text = (submitState as SubmitState.Error).message,
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall,
                )
            }

            Spacer(Modifier.height(24.dp))
            Button(
                onClick = viewModel::submit,
                enabled = draft.isValid && submitState !is SubmitState.Submitting,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(54.dp),
                shape = RoundedCornerShape(27.dp),
                colors = ButtonDefaults.buttonColors(containerColor = BrandGreen),
            ) {
                if (submitState is SubmitState.Submitting) {
                    CircularProgressIndicator(
                        color = Color.White,
                        strokeWidth = 2.dp,
                        modifier = Modifier.size(20.dp),
                    )
                } else {
                    Icon(Icons.Filled.Send, contentDescription = null, tint = Color.White, modifier = Modifier.size(18.dp))
                    Spacer(Modifier.width(8.dp))
                    Text("Submit Report", color = Color.White, fontWeight = FontWeight.SemiBold)
                }
            }
            Spacer(Modifier.height(8.dp))
            Text(
                text = "Your report will be reviewed by the editorial team before it is published as verified news.",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
            Spacer(Modifier.height(24.dp))
        }
    }
}

@Composable
private fun SectionLabel(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.titleSmall,
        modifier = Modifier.padding(bottom = 8.dp),
    )
}

@Composable
private fun MediaToggle(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    label: String,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val borderColor = if (selected) BrandGreen else MaterialTheme.colorScheme.outline
    val bg = if (selected) BrandGreen.copy(alpha = 0.10f) else MaterialTheme.colorScheme.surface
    val shape = RoundedCornerShape(14.dp)
    Column(
        modifier = modifier
            .clip(shape)
            .border(1.dp, borderColor, shape)
            .background(bg)
            .clickable(onClick = onClick)
            .padding(vertical = 18.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            tint = if (selected) BrandGreen else MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.size(24.dp),
        )
        Spacer(Modifier.height(6.dp))
        Text(
            text = label,
            style = MaterialTheme.typography.labelMedium,
            color = if (selected) BrandGreen else MaterialTheme.colorScheme.onSurface,
        )
    }
}
