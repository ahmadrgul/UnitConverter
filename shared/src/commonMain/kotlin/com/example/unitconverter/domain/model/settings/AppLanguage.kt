package com.example.unitconverter.domain.model.settings

enum class AppLanguage(val tag: String, val nativeName: String) {
    ENGLISH("en", "English"),
    URDU("ur", "اردو"),
    ARABIC("ar", "العربية");

    companion object {
        fun fromTag(tag: String?): AppLanguage? {
            return entries.find { it.tag == tag }
        }
    }
}