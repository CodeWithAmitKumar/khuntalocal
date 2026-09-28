package com.khuntalocal.app.data.model

/** An entry in the notifications / Alerts center. */
data class AlertItem(
    val id: String,
    val type: AlertType,
    val title: String,
    val body: String,
    val timeAgo: String,
    val unread: Boolean,
)

enum class AlertType {
    BREAKING,
    NEARBY,
    NEW_STORY,
    COMMENT_REPLY,
    NEWS_VERIFIED,
    NEWS_REJECTED,
    VERIFICATION_REQUIRED,
    REPORTER_APPROVED,
}
