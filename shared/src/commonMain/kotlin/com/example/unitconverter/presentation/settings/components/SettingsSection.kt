package com.example.unitconverter.presentation.settings.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.unitconverter.presentation.settings.components.cards.ChoiceSettingsItemCard
import com.example.unitconverter.presentation.settings.components.cards.LinkSettingsItemCard
import com.example.unitconverter.presentation.settings.components.cards.ToggleSettingsItemCard
import com.example.unitconverter.presentation.settings.model.SettingId
import com.example.unitconverter.presentation.settings.model.SettingsItemUi


@Composable
fun SettingsSection(
    title: String,
    items: List<SettingsItemUi>,
    onToggleChange: (SettingId, Boolean) -> Unit,
    onChoiceSelect: (SettingId, String) -> Unit
){
    Column(
        modifier = Modifier.padding(vertical = 12.dp)
    ) {
        Text(
            text = title,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 10.dp)
        )

        Column (
            modifier = Modifier
                .fillMaxWidth()
                .dropShadow(
                    shape = RoundedCornerShape(12.dp),
                    shadow = Shadow(
                        color = Color.Black.copy(alpha = 0.05f),
                        radius = 5.dp,
                        spread = 0.dp
                    )
                )
                .background(
                    color = MaterialTheme.colorScheme.surface,
                    shape = RoundedCornerShape(12.dp)
                )
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items.forEachIndexed { index, item ->
                when (item) {
                    is SettingsItemUi.Toggle -> ToggleSettingsItemCard(
                        item = item,
                        onToggleChange = {
                            onToggleChange(item.id, !item.checked)
                        }
                    )

                    is SettingsItemUi.Choice -> ChoiceSettingsItemCard(
                        id = item.id,
                        title = item.title,
                        subtitle = item.subtitle,
                        options = item.options,
                        currentValue = item.currentValue,
                        onChoiceSelect = { onChoiceSelect(item.id, it) }
                    )

                    is SettingsItemUi.ExternalLink -> LinkSettingsItemCard(
                        id = item.id,
                        title = item.title,
                        subtitle = item.subtitle,
                        url = item.url
                    )
                }

                if (index < items.size - 1) {
                    HorizontalDivider(thickness = 0.5.dp, color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f))
                }

            }
        }
    }
}