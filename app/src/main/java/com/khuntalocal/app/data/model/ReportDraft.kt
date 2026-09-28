package com.khuntalocal.app.data.model

/** Mutable form state captured on the Report News screen. */
data class ReportDraft(
    val headline: String = "",
    val description: String = "",
    val category: Category? = null,
    val location: String = "",
    val useCurrentLocation: Boolean = true,
    val language: Language = Language.ODIA,
    val source: ReportSource? = null,
    val hasPhoto: Boolean = false,
    val hasVideo: Boolean = false,
) {
    /** Minimum a report needs before it can be submitted for review. */
    val isValid: Boolean
        get() = headline.isNotBlank() &&
            description.isNotBlank() &&
            category != null &&
            (useCurrentLocation || location.isNotBlank())
}
