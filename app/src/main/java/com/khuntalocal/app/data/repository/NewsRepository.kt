package com.khuntalocal.app.data.repository

import com.khuntalocal.app.data.model.AlertItem
import com.khuntalocal.app.data.model.Category
import com.khuntalocal.app.data.model.NewsArticle
import com.khuntalocal.app.data.model.NewsSort
import com.khuntalocal.app.data.model.ReportDraft
import com.khuntalocal.app.data.model.VerificationStatus
import kotlinx.coroutines.flow.StateFlow

/**
 * Single source of truth for news content. The mock implementation keeps data
 * in memory; a production implementation would delegate to a Retrofit service
 * hitting the Laravel API and cache to Room. Swapping the binding in
 * [com.khuntalocal.app.di.ServiceLocator] changes nothing in the UI layer.
 */
interface NewsRepository {

    /** All known articles, newest first. Emits again when a report is submitted. */
    val articles: StateFlow<List<NewsArticle>>

    /** Notification / Alerts center entries. */
    val alerts: StateFlow<List<AlertItem>>

    fun getArticle(id: String): NewsArticle?

    /** Submits a new community report; it enters the feed as [VerificationStatus.UNDER_REVIEW]. */
    suspend fun submitReport(draft: ReportDraft): String
}

/** Pure filtering + sorting so screens and previews stay deterministic. */
fun List<NewsArticle>.filtered(
    category: Category = Category.ALL,
    sort: NewsSort = NewsSort.LATEST,
): List<NewsArticle> {
    val byCategory = when (category) {
        Category.ALL -> this
        Category.BREAKING -> filter { it.isBreaking }
        else -> filter { it.category == category }
    }
    return when (sort) {
        NewsSort.LATEST -> byCategory
        NewsSort.MOST_VIEWED -> byCategory.sortedByDescending { it.views }
        NewsSort.MOST_DISCUSSED -> byCategory.sortedByDescending { it.comments }
        NewsSort.TRENDING -> byCategory.sortedByDescending { it.views + it.likes * 3 }
        NewsSort.VERIFIED -> byCategory.filter { it.status == VerificationStatus.VERIFIED }
    }
}
