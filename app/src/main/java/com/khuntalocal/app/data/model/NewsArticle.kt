package com.khuntalocal.app.data.model

/**
 * A single news story as shown in the feed and detail screens.
 *
 * [timeAgo] is a pre-formatted relative label ("18 min ago") kept simple for the
 * mock layer; a real implementation would carry a timestamp and format on device.
 */
data class NewsArticle(
    val id: String,
    val headline: String,
    val summary: String,
    val body: String,
    val category: Category,
    val status: VerificationStatus,
    val isBreaking: Boolean,
    val locationName: String,
    val imageUrl: String?,
    val timeAgo: String,
    val reporter: Reporter,
    val views: Int,
    val likes: Int,
    val comments: Int,
    /** Other reports the editorial system grouped into this same incident. */
    val relatedReportsCount: Int = 0,
    val language: Language = Language.ENGLISH,
) {
    val isVerified: Boolean get() = status == VerificationStatus.VERIFIED
}
