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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.unitconverter.presentation.converter.KeyboardKey

@Composable
fun CustomKeyboard(
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
                        bgColor = if (key == KeyboardKey.EQUAL) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface,
                        fgColor = if (key == KeyboardKey.EQUAL) MaterialTheme.colorScheme.surface else Color.Black,
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
    bgColor: Color,
    fgColor: Color,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val keyShape = RoundedCornerShape(10.dp)

    Button(
        onClick = onClick,
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
            color = fgColor,
            fontWeight = FontWeight.Medium,
            fontSize = 24.sp
        )
    }
}