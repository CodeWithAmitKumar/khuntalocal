package com.khuntalocal.app.data.remote

import com.khuntalocal.app.data.model.NewsArticle

/**
 * Contract for the future Laravel REST API. The mock repository stands in for
 * this today; wiring Retrofit later means annotating these methods
 * (`@GET("news")`, `@POST("reports")`, …) and providing a network
 * [com.khuntalocal.app.data.repository.NewsRepository] that delegates here.
 *
 * Planned endpoints (see the backend design):
 *   GET  /api/news?category=&sort=&location=   -> feed
 *   GET  /api/news/{id}                         -> detail
 *   GET  /api/news/breaking                     -> breaking carousel
 *   POST /api/reports                           -> submit a report
 *   GET  /api/alerts                            -> notifications
 */
interface KhuntaApi {
    suspend fun feed(category: String?, sort: String?, location: String?): List<NewsArticle>
    suspend fun article(id: String): NewsArticle
    suspend fun breaking(): List<NewsArticle>
    suspend fun submitReport(request: SubmitReportRequest): SubmitReportResponse
}

data class SubmitReportRequest(
    val headline: String,
    val description: String,
    val category: String,
    val location: String,
    val language: String,
    val source: String?,
    val hasPhoto: Boolean,
    val hasVideo: Boolean,
)

data class SubmitReportResponse(
    val id: String,
    val status: String,
)
