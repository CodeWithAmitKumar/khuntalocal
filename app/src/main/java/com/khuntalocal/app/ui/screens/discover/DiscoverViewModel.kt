package com.khuntalocal.app.ui.screens.discover

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.khuntalocal.app.data.model.Category
import com.khuntalocal.app.data.model.NewsArticle
import com.khuntalocal.app.data.model.NewsSort
import com.khuntalocal.app.data.repository.NewsRepository
import com.khuntalocal.app.data.repository.filtered
import com.khuntalocal.app.di.ServiceLocator
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn

data class DiscoverUiState(
    val query: String = "",
    val category: Category = Category.ALL,
    val sort: NewsSort = NewsSort.LATEST,
    val results: List<NewsArticle> = emptyList(),
)

class DiscoverViewModel(repository: NewsRepository) : ViewModel() {

    private val query = MutableStateFlow("")
    private val category = MutableStateFlow(Category.ALL)
    private val sort = MutableStateFlow(NewsSort.LATEST)

    val uiState = combine(
        repository.articles, query, category, sort,
    ) { articles, q, cat, s ->
        val results = articles.filtered(cat, s).filter { article ->
            q.isBlank() ||
                article.headline.contains(q, ignoreCase = true) ||
                article.summary.contains(q, ignoreCase = true) ||
                article.locationName.contains(q, ignoreCase = true)
        }
        DiscoverUiState(query = q, category = cat, sort = s, results = results)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = DiscoverUiState(),
    )

    fun onQueryChange(value: String) { query.value = value }
    fun onCategoryChange(value: Category) { category.value = value }
    fun onSortChange(value: NewsSort) { sort.value = value }

    companion object {
        val Factory = viewModelFactory {
            initializer { DiscoverViewModel(ServiceLocator.newsRepository) }
        }
    }
}
