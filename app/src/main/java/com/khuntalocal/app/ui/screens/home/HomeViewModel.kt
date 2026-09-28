package com.khuntalocal.app.ui.screens.home

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

data class HomeUiState(
    val breaking: List<NewsArticle> = emptyList(),
    val topStories: List<NewsArticle> = emptyList(),
    val selectedCategory: Category = Category.ALL,
    val location: String = "Khunta, Mayurbhanj",
    val unreadAlerts: Int = 3,
)

class HomeViewModel(repository: NewsRepository) : ViewModel() {

    private val selectedCategory = MutableStateFlow(Category.ALL)

    val uiState = combine(repository.articles, selectedCategory) { articles, category ->
        HomeUiState(
            breaking = articles.filter { it.isBreaking },
            topStories = articles.filtered(category, NewsSort.LATEST),
            selectedCategory = category,
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = HomeUiState(),
    )

    fun onCategorySelected(category: Category) {
        selectedCategory.value = category
    }

    companion object {
        val Factory = viewModelFactory {
            initializer { HomeViewModel(ServiceLocator.newsRepository) }
        }
    }
}
