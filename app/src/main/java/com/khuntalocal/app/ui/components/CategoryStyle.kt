package com.khuntalocal.app.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Agriculture
import androidx.compose.material.icons.filled.AccountBalance
import androidx.compose.material.icons.filled.Apps
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.filled.Campaign
import androidx.compose.material.icons.filled.Celebration
import androidx.compose.material.icons.filled.Cloud
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material.icons.filled.Gavel
import androidx.compose.material.icons.filled.Groups
import androidx.compose.material.icons.filled.LocalHospital
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.SportsSoccer
import androidx.compose.material.icons.filled.Storefront
import androidx.compose.material.icons.filled.Traffic
import androidx.compose.material.icons.filled.Work
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.khuntalocal.app.data.model.Category

/** Visual treatment (accent color + glyph) for a [Category]. */
data class CategoryStyle(val color: Color, val icon: ImageVector)

fun Category.style(): CategoryStyle = when (this) {
    Category.ALL -> CategoryStyle(Color(0xFF12A150), Icons.Filled.Apps)
    Category.BREAKING -> CategoryStyle(Color(0xFFE23744), Icons.Filled.Bolt)
    Category.LOCAL -> CategoryStyle(Color(0xFF2563EB), Icons.Filled.LocationOn)
    Category.ACCIDENT -> CategoryStyle(Color(0xFFF97316), Icons.Filled.DirectionsCar)
    Category.EDUCATION -> CategoryStyle(Color(0xFF7C3AED), Icons.Filled.School)
    Category.SPORTS -> CategoryStyle(Color(0xFF10B981), Icons.Filled.SportsSoccer)
    Category.WEATHER -> CategoryStyle(Color(0xFF0EA5E9), Icons.Filled.Cloud)
    Category.CRIME -> CategoryStyle(Color(0xFFB91C1C), Icons.Filled.Gavel)
    Category.GOVERNMENT -> CategoryStyle(Color(0xFF475569), Icons.Filled.AccountBalance)
    Category.EVENTS -> CategoryStyle(Color(0xFFDB2777), Icons.Filled.Celebration)
    Category.TRAFFIC -> CategoryStyle(Color(0xFFF59E0B), Icons.Filled.Traffic)
    Category.HEALTH -> CategoryStyle(Color(0xFFEF4444), Icons.Filled.LocalHospital)
    Category.AGRICULTURE -> CategoryStyle(Color(0xFF65A30D), Icons.Filled.Agriculture)
    Category.JOBS -> CategoryStyle(Color(0xFF0891B2), Icons.Filled.Work)
    Category.COMMUNITY -> CategoryStyle(Color(0xFF7C3AED), Icons.Filled.Groups)
    Category.BUSINESS -> CategoryStyle(Color(0xFF9333EA), Icons.Filled.Storefront)
    Category.POLITICS -> CategoryStyle(Color(0xFF4F46E5), Icons.Filled.Campaign)
}
