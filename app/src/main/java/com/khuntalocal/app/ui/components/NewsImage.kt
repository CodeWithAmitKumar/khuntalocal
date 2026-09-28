package com.khuntalocal.app.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Alignment
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.khuntalocal.app.data.model.Category

/**
 * Loads a story image with Coil, falling back to a category-tinted gradient with
 * the category glyph while loading or when offline / URL is null. This keeps the
 * feed visually intact without any network.
 */
@Composable
fun NewsImage(
    imageUrl: String?,
    category: Category,
    modifier: Modifier = Modifier,
    cornerRadius: Int = 0,
) {
    val style = category.style()
    val placeholder: @Composable () -> Unit = {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.linearGradient(
                        listOf(style.color.copy(alpha = 0.85f), style.color.copy(alpha = 0.55f)),
                    ),
                ),
            contentAlignment = Alignment.Center,
        ) {
            Icon(
                imageVector = style.icon,
                contentDescription = null,
                tint = Color.White.copy(alpha = 0.9f),
                modifier = Modifier.size(40.dp),
            )
        }
    }

    val shaped = if (cornerRadius > 0) modifier.clip(RoundedCornerShape(cornerRadius.dp)) else modifier

    if (imageUrl == null) {
        Box(shaped) { placeholder() }
    } else {
        SubcomposeAsyncImage(
            model = imageUrl,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = shaped,
            loading = { placeholder() },
            error = { placeholder() },
        )
    }
}
