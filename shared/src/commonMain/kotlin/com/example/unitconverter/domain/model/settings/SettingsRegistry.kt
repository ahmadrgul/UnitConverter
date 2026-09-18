package com.example.unitconverter.domain.model.settings

object SettingsRegistry {
    private val settingItems = mapOf(
        "General" to listOf(
            SettingsItem(id = "darkMode", title = "Dark Mode", subtitle = "Use dark theme across app"),
            SettingsItem(id = "language", title = "Language"),
            SettingsItem(id = "history", title = "Auto Save History", subtitle = "Save conversion automatically"),
            SettingsItem(id = "precision", title = "Decimal Precision", subtitle = "Set default decimal places"),
        ),
        "Support" to listOf(
            SettingsItem(id = "faq", title = "Help & FAQ", subtitle = "Find answers to common question"),
            SettingsItem(id = "support", title = "Contact Support", subtitle = "Get help from our support team"),
            SettingsItem(id = "rating", title = "Rate Us", subtitle = "Rate Unit Converter on Play Store"),
            SettingsItem(id = "share", title = "Share App", subtitle = "Share Unit Converter with friends"),
        ),
        "About" to listOf(
            SettingsItem(id = "about", title = "About Unit Converter"),
        ),
    )

    fun getAllSettingsItems(): Map<String, List<SettingsItem>> {
        return settingItems
    }
}