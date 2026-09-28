package com.khuntalocal.app.ui.screens.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.khuntalocal.app.data.model.NewsArticle
import com.khuntalocal.app.data.repository.NewsRepository
import com.khuntalocal.app.di.ServiceLocator

class NewsDetailViewModel(
    repository: NewsRepository,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val articleId: String = savedStateHandle.get<String>("id").orEmpty()

    val article: NewsArticle? = repository.getArticle(articleId)

    companion object {
        val Factory = viewModelFactory {
            initializer {
                NewsDetailViewModel(
                    repository = ServiceLocator.newsRepository,
                    savedStateHandle = createSavedStateHandle(),
                )
            }
        }
    }
}
