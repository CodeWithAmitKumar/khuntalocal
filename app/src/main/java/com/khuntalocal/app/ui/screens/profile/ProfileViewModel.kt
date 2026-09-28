package com.khuntalocal.app.ui.screens.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.khuntalocal.app.data.model.NewsArticle
import com.khuntalocal.app.data.repository.NewsRepository
import com.khuntalocal.app.di.ServiceLocator
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class ProfileViewModel(repository: NewsRepository) : ViewModel() {

    val myReports = repository.articles
        .map { list -> list.filter { it.reporter.id == "u_me" } }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = emptyList<NewsArticle>(),
        )

    companion object {
        val Factory = viewModelFactory {
            initializer { ProfileViewModel(ServiceLocator.newsRepository) }
        }
    }
}
