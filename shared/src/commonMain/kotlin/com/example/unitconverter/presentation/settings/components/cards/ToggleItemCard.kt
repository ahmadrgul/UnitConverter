package com.example.unitconverter.presentation.settings.components.cards

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalMinimumInteractiveComponentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.unitconverter.presentation.settings.model.SettingsItemUi
import com.example.unitconverter.presentation.theme.getSettingsItemIcon
import com.example.unitconverter.presentation.theme.getSettingsItemIconColor
import org.jetbrains.compose.resources.painterResource


@Composable
fun ToggleSettingsItemCard(
    item: SettingsItemUi.Toggle,
    onToggleChange: () -> Unit
){
    val icon = getSettingsItemIcon(item.id)
    val color = getSettingsItemIconColor(item.id)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row (
            modifier = Modifier.weight(1f),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Box(
                modifier = Modifier
                    .background(
                        color = color.copy(alpha = 0.08f),
                        shape = RoundedCornerShape(10.dp),
                    )
                    .padding(6.dp)
            ) {
                Icon(
                    painter = painterResource(icon),
                    contentDescription = item.id.name,
                    tint = getSettingsItemIconColor(item.id),
                    modifier = Modifier.size(26.dp)
                )
            }

            Column (
                modifier = Modifier
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.spacedBy(4.dp, alignment = Alignment.CenterVertically)
            ) {
                Text(
                    text = item.title,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 14.sp
                )

                if (item.subtitle != null) {
                    Text(
                        text = item.subtitle,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
                        fontSize = 13.sp,
                    )
                }
            }
        }

        Box(
            modifier = Modifier
                .fillMaxHeight(),
            contentAlignment = Alignment.Center
        ){
            CustomSwitch(
                item.checked,
                onToggleChange = onToggleChange
            )
        }

    }
}

@Composable
fun CustomSwitch(
    isChecked: Boolean,
    onToggleChange: () -> Unit,
){
    val haptic = LocalHapticFeedback.current

    CompositionLocalProvider(LocalMinimumInteractiveComponentSize provides 0.dp){
        Switch(
            thumbContent = {
                Box(
                    modifier = Modifier
                        .size(20.dp)
                        .background(
                            color = Color.White,
                            shape = CircleShape
                        )
                )
            },
            colors = SwitchDefaults.colors(
                uncheckedBorderColor = Color.LightGray,
                checkedThumbColor = Color.White,
                uncheckedThumbColor = Color.White,
                checkedTrackColor = MaterialTheme.colorScheme.primary,
                uncheckedTrackColor = Color.LightGray,
            ),
            checked = isChecked,
            onCheckedChange = {
                haptic.performHapticFeedback(if (isChecked) HapticFeedbackType.ToggleOff else HapticFeedbackType.ToggleOn)
                onToggleChange()
            },
            modifier = Modifier.scale(0.9f)
        )
    }
}