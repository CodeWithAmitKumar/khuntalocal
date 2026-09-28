package com.khuntalocal.app.ui.screens.alerts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.khuntalocal.app.data.repository.NewsRepository
import com.khuntalocal.app.di.ServiceLocator
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn

class AlertsViewModel(repository: NewsRepository) : ViewModel() {

    val alerts = repository.alerts.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = repository.alerts.value,
    )

    companion object {
        val Factory = viewModelFactory {
            initializer { AlertsViewModel(ServiceLocator.newsRepository) }
        }
    }
}
