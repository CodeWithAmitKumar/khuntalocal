package com.khuntalocal.app.data.model

/** Languages a report can be written in. The backend stores translations
 *  separately from the original text (see the multilingual design). */
enum class Language(val label: String, val nativeLabel: String) {
    ODIA("Odia", "ଓଡ଼ିଆ"),
    HINDI("Hindi", "हिन्दी"),
    ENGLISH("English", "English"),
    BENGALI("Bengali", "বাংলা"),
    OTHER("Other", "Other"),
}
