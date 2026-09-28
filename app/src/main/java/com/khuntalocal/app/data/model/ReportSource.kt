package com.khuntalocal.app.data.model

/** How the reporter learned about the event. Retained for moderators as
 *  part of the evidence trail; not shown publicly. */
enum class ReportSource(val label: String) {
    WITNESSED("I witnessed it"),
    TOLD_BY_SOMEONE("Someone told me"),
    OFFICIAL_ANNOUNCEMENT("Official announcement"),
    SOCIAL_MEDIA("Social media"),
    OTHER("Other"),
}
