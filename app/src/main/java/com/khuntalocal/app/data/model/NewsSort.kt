package com.khuntalocal.app.data.model

/** Feed sort options exposed by the Discover / filter UI. */
enum class NewsSort(val label: String) {
    LATEST("Latest"),
    MOST_VIEWED("Most viewed"),
    MOST_DISCUSSED("Most discussed"),
    TRENDING("Trending"),
    VERIFIED("Verified only"),
}
