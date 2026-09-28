package com.khuntalocal.app.ui.screens.report

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.khuntalocal.app.data.model.Category
import com.khuntalocal.app.data.model.Language
import com.khuntalocal.app.data.model.ReportDraft
import com.khuntalocal.app.data.model.ReportSource
import com.khuntalocal.app.data.repository.NewsRepository
import com.khuntalocal.app.di.ServiceLocator
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

sealed interface SubmitState {
    data object Idle : SubmitState
    data object Submitting : SubmitState
    data class Success(val articleId: String) : SubmitState
    data class Error(val message: String) : SubmitState
}

class ReportViewModel(private val repository: NewsRepository) : ViewModel() {

    private val _draft = MutableStateFlow(ReportDraft())
    val draft = _draft.asStateFlow()

    private val _submitState = MutableStateFlow<SubmitState>(SubmitState.Idle)
    val submitState = _submitState.asStateFlow()

    fun onHeadlineChange(value: String) = _draft.update { it.copy(headline = value) }
    fun onDescriptionChange(value: String) = _draft.update { it.copy(description = value) }
    fun onCategoryChange(value: Category) = _draft.update { it.copy(category = value) }
    fun onLocationChange(value: String) = _draft.update { it.copy(location = value) }
    fun onLanguageChange(value: Language) = _draft.update { it.copy(language = value) }
    fun onSourceChange(value: ReportSource) = _draft.update { it.copy(source = value) }
    fun togglePhoto() = _draft.update { it.copy(hasPhoto = !it.hasPhoto) }
    fun toggleVideo() = _draft.update { it.copy(hasVideo = !it.hasVideo) }

    fun onUseCurrentLocationChange(value: Boolean) =
        _draft.update { it.copy(useCurrentLocation = value) }

    fun submit() {
        val current = _draft.value
        if (!current.isValid || _submitState.value == SubmitState.Submitting) return
        _submitState.value = SubmitState.Submitting
        viewModelScope.launch {
            _submitState.value = try {
                SubmitState.Success(repository.submitReport(current))
            } catch (e: Exception) {
                SubmitState.Error(e.message ?: "Something went wrong")
            }
        }
    }

    companion object {
        val Factory = viewModelFactory {
            initializer { ReportViewModel(ServiceLocator.newsRepository) }
        }
    }
}
