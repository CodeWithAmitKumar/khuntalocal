package com.khuntalocal.app.data.repository

import com.khuntalocal.app.data.SampleData
import com.khuntalocal.app.data.model.AlertItem
import com.khuntalocal.app.data.model.NewsArticle
import com.khuntalocal.app.data.model.ReportDraft
import com.khuntalocal.app.data.model.Reporter
import com.khuntalocal.app.data.model.VerificationStatus
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * In-memory [NewsRepository] backed by [SampleData]. Lets the whole app be
 * navigated and demoed without a backend. Submitted reports are prepended to
 * the feed with [VerificationStatus.UNDER_REVIEW] to illustrate the
 * submit → review → publish lifecycle.
 */
class MockNewsRepository : NewsRepository {

    private val currentUser = Reporter(
        id = "u_me",
        name = "You",
        location = "Khunta, Mayurbhanj",
        isVerifiedReporter = false,
    )

    private val _articles = MutableStateFlow(SampleData.articles)
    override val articles: StateFlow<List<NewsArticle>> = _articles.asStateFlow()

    private val _alerts = MutableStateFlow(SampleData.alerts)
    override val alerts: StateFlow<List<AlertItem>> = _alerts.asStateFlow()

    override fun getArticle(id: String): NewsArticle? =
        _articles.value.firstOrNull { it.id == id }

    override suspend fun submitReport(draft: ReportDraft): String {
        // Simulate a network round-trip and automated checks.
        delay(900)
        val id = "n_local_${System.currentTimeMillis()}"
        val article = NewsArticle(
            id = id,
            headline = draft.headline.trim(),
            summary = draft.description.trim().take(120),
            body = draft.description.trim(),
            category = draft.category ?: com.khuntalocal.app.data.model.Category.LOCAL,
            status = VerificationStatus.UNDER_REVIEW,
            isBreaking = false,
            locationName = if (draft.useCurrentLocation) currentUser.location
            else draft.location.ifBlank { currentUser.location },
            imageUrl = null,
            timeAgo = "Just now",
            reporter = currentUser,
            views = 0,
            likes = 0,
            comments = 0,
            language = draft.language,
        )
        _articles.value = listOf(article) + _articles.value
        return id
    }
}
