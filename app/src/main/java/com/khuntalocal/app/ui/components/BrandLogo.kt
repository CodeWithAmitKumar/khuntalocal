package com.khuntalocal.app.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.khuntalocal.app.R

/**
 * The KhuntaLocal mark: green disc with a white badge, mountain and sun,
 * composed from the same vector assets as the launcher icon.
 */
@Composable
fun BrandLogo(size: Dp = 72.dp, modifier: Modifier = Modifier) {
    Box(modifier = modifier.size(size)) {
        Image(
            painter = painterResource(R.drawable.ic_launcher_background),
            contentDescription = null,
            modifier = Modifier.size(size).clip(CircleShape),
        )
        Image(
            painter = painterResource(R.drawable.ic_launcher_foreground),
            contentDescription = "KhuntaLocal",
            modifier = Modifier.size(size),
        )
    }
}
