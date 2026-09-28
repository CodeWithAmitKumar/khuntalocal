package com.khuntalocal.app.data.model

/**
 * Content categories used for filtering and tagging news.
 * [ALL] is a UI-only pseudo-category for the "show everything" filter.
 */
enum class Category(val label: String) {
    ALL("All"),
    BREAKING("Breaking"),
    LOCAL("Local"),
    ACCIDENT("Accident"),
    EDUCATION("Education"),
    SPORTS("Sports"),
    WEATHER("Weather"),
    CRIME("Crime"),
    GOVERNMENT("Government"),
    EVENTS("Events"),
    TRAFFIC("Traffic"),
    HEALTH("Health"),
    AGRICULTURE("Agriculture"),
    JOBS("Jobs"),
    COMMUNITY("Community"),
    BUSINESS("Business"),
    POLITICS("Politics");

    /** Categories offered as horizontal quick filters on the Home screen. */
    companion object {
        val quickFilters: List<Category> =
            listOf(ALL, BREAKING, LOCAL, ACCIDENT, EDUCATION, SPORTS)

        /** Categories a reporter can choose when submitting (excludes [ALL]). */
        val selectable: List<Category> = Category.entries.filter { it != ALL }
    }
}
