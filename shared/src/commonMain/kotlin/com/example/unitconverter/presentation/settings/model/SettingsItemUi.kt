package com.example.unitconverter.presentation.settings.model

data class ChoiceOption(
    val label: String,
    val value: String
)

sealed interface SettingsItemUi {
    val id: SettingId
    val title: String
    val subtitle: String?

    data class Toggle(
        override val id: SettingId,
        override val title: String,
        override val subtitle: String? = null,
        val checked: Boolean,
    ) : SettingsItemUi

    data class Choice(
        override val id: SettingId,
        override val title: String,
        override val subtitle: String? = null,
        val options: List<ChoiceOption>,
        val currentValue: String
    ) : SettingsItemUi

    data class ExternalLink(
        override val id: SettingId,
        override val title: String,
        override val subtitle: String? = null,
        val url: String
    ) : SettingsItemUi
}