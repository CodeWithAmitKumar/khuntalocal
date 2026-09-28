package com.khuntalocal.app.data.model

/**
 * The editorial state of a report. Every story surfaces its status so that
 * verified news is always visually distinct from unverified community reports.
 */
enum class VerificationStatus(val label: String) {
    UNDER_REVIEW("Under Review"),
    VERIFIED("Verified"),
    COMMUNITY_REPORT("Community Report"),
    REJECTED("Rejected"),
    CORRECTION("Correction"),
}
