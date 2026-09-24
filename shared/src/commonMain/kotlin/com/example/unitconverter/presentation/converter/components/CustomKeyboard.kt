package com.example.unitconverter.presentation.converter.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.unitconverter.presentation.converter.KeyboardKey

@Composable
fun CustomKeyboard(
    isInputEmpty: Boolean,
    onKeyPressed: (KeyboardKey) -> Unit,
) {
    val keyRows = listOf(
        listOf(KeyboardKey.NUM_7, KeyboardKey.NUM_8, KeyboardKey.NUM_9, KeyboardKey.BACKSPACE),
        listOf(KeyboardKey.NUM_4, KeyboardKey.NUM_5, KeyboardKey.NUM_6, KeyboardKey.AC),
        listOf(KeyboardKey.NUM_1, KeyboardKey.NUM_2, KeyboardKey.NUM_3, KeyboardKey.DOT),
        listOf(KeyboardKey.NUM_0, KeyboardKey.EQUAL),
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.background)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        keyRows.forEach { row ->
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                row.forEach { key ->
                    CustomKeyboardKey(
                        content = key.symbol,
                        enabled = !((key == KeyboardKey.EQUAL || key == KeyboardKey.BACKSPACE) && isInputEmpty),
                        bgColor = if (key == KeyboardKey.EQUAL) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface,
                        fgColor = if (key == KeyboardKey.EQUAL) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurface,
                        onClick = { onKeyPressed(key) },
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }
    }
}

@Composable
fun CustomKeyboardKey(
    content: String,
    enabled: Boolean,
    bgColor: Color,
    fgColor: Color,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val haptic = LocalHapticFeedback.current
    val keyShape = RoundedCornerShape(10.dp)

    Button(
        enabled = enabled,
        onClick = {
            haptic.performHapticFeedback(HapticFeedbackType.VirtualKey)
            onClick()
        },
        shape = keyShape,
        colors = ButtonDefaults.buttonColors(
            containerColor = bgColor,
            contentColor = fgColor
        ),
        modifier = modifier
            .dropShadow(
                shape = keyShape,
                shadow = Shadow(
                    color = Color.Black.copy(alpha = 0.03f),
                    radius = 6.dp,
                    spread = 0.dp,
                    offset = DpOffset(0.dp, 0.dp)
                )
            ),
        contentPadding = PaddingValues(vertical = 14.dp)
    ) {
        Text(
            text = content,
            fontWeight = FontWeight.Medium,
            fontSize = 24.sp
        )
    }
}