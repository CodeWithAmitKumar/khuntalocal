package com.khuntalocal.app.data.model

data class Reporter(
    val id: String,
    val name: String,
    val location: String,
    val isVerifiedReporter: Boolean = false,
    val avatarUrl: String? = null,
)
