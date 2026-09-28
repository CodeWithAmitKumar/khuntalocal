package com.khuntalocal.app.di

import com.khuntalocal.app.data.repository.MockNewsRepository
import com.khuntalocal.app.data.repository.NewsRepository

/**
 * Minimal manual dependency container. Deliberately tiny so the sample app has
 * no DI framework to configure; replace [newsRepository] with a network-backed
 * implementation (Retrofit + Room) without touching any ViewModel or screen.
 */
object ServiceLocator {
    val newsRepository: NewsRepository by lazy { MockNewsRepository() }
}
