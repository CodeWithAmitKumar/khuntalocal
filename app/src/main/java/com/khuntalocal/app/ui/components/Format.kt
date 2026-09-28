package com.khuntalocal.app.ui.components

import java.util.Locale

/** Compact count formatting: 2410 -> "2.4K", 1_200_000 -> "1.2M". */
fun Int.compact(): String = when {
    this >= 1_000_000 -> String.format(Locale.US, "%.1fM", this / 1_000_000.0)
    this >= 1_000 -> String.format(Locale.US, "%.1fK", this / 1_000.0)
    else -> this.toString()
}
