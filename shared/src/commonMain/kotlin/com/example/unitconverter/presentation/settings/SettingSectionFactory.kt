package com.example.unitconverter.presentation.settings

import com.example.unitconverter.domain.model.settings.AppLanguage
import com.example.unitconverter.domain.model.settings.AppSettings
import com.example.unitconverter.presentation.settings.model.ChoiceOption
import com.example.unitconverter.presentation.settings.model.SectionId
import com.example.unitconverter.presentation.settings.model.SettingId
import com.example.unitconverter.presentation.settings.model.SettingsItemUi
import com.example.unitconverter.presentation.settings.model.SettingsSectionUi

fun AppSettings.toSections(): List<SettingsSectionUi> = listOf(
    SettingsSectionUi(
        id = SectionId.GENERAL,
        title = "General",
        items = listOf(
            SettingsItemUi.Toggle(
                id = SettingId.THEME,
                title = "Dark Mode",
                subtitle = "Use dark theme across the app",
                checked = darkMode
            ),
            SettingsItemUi.Choice(
                id = SettingId.LANGUAGE,
                title = "Language",
                subtitle = "Set app language",
                options = AppLanguage.entries.map { ChoiceOption(label = it.nativeName, value = it.tag) },
                currentValue = language.nativeName
            ),
            SettingsItemUi.Toggle(
                id = SettingId.HISTORY,
                title = "Auto Save History",
                subtitle = "Save conversions automatically",
                checked = saveHistory
            ),
            SettingsItemUi.Choice(
                id = SettingId.PRECISION,
                title = "Decimal Precision",
                subtitle = "Set default decimal places",
                options = (0..3).map { ChoiceOption(label = it.toString(), value = it.toString() ) },
                currentValue = decimalPrecision.toString()
            )
        )
    ),

    SettingsSectionUi(
        id = SectionId.SUPPORT,
        title = "Support",
        items = listOf(
            SettingsItemUi.ExternalLink(
                id = SettingId.HELP,
                title = "Help & FAQ",
                subtitle = "Find answers to common question",
                url = "https://webscare.com"
            ),
            SettingsItemUi.ExternalLink(
                id = SettingId.SUPPORT,
                title = "Contact Support",
                subtitle = "Get help from our support team",
                url = "https://webscare.com"
            ),
            SettingsItemUi.ExternalLink(
                id = SettingId.RATE,
                title = "Rate Us",
                subtitle = "Rate Unit Converter on Play Store",
                url = "https://webscare.com"
            ),
            SettingsItemUi.ExternalLink(
                id = SettingId.SHARE,
                title = "Share App",
                subtitle = "Share Unit Converter with friends",
                url = "https://webscare.com"
            ),
        )
    ),

    SettingsSectionUi(
        id = SectionId.ABOUT,
        title = "About",
        items = listOf(
            SettingsItemUi.ExternalLink(
                id = SettingId.ABOUT,
                title = "About Unit Converter",
                subtitle = "Version 1.0.0",
                url = "https://webscare.com"
            )
        )
    )
)