package com.example.unitconverter.presentation.settings.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.LocalMinimumInteractiveComponentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun CustomSwitch(
    isChecked: Boolean,
    onCheckedChange: () -> Unit,
){
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
            onCheckedChange = { onCheckedChange() },
            modifier = Modifier.scale(0.9f)
        )
    }
}
