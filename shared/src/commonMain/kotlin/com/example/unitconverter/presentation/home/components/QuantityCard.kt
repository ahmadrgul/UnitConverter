package com.example.unitconverter.presentation.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource


@Composable
fun QuantityCard(
    label: String,
    icon: DrawableResource,
    color: Color,
    isDarkTheme: Boolean,
    onClick: () -> Unit,
) {
    val cardShape = RoundedCornerShape(12.dp)

    Column(
        modifier = Modifier
            .clip(cardShape)
            .border(
                color = if(isDarkTheme) MaterialTheme.colorScheme.onSurface.copy(0.05f) else color.copy(alpha = 0.1f),
                width = 0.5.dp,
                shape = cardShape
            )
            .dropShadow(
                shape = cardShape,
                shadow = Shadow(
                    color = Color.Black.copy(alpha = 0.05f),
                    radius = 2.dp,
                    spread = 0.dp
                )
            )
            .clickable(onClick = onClick)
            .background(
                color = MaterialTheme.colorScheme.surface,
                shape = cardShape
            )
            .background(
                brush = Brush.verticalGradient(
                    colors = if(!isDarkTheme) listOf(
                        color.copy(alpha = 0.01f),
                        color.copy(alpha = 0.05f)
                    ) else listOf(
                        Color.Transparent,
                        Color.Transparent
                    ),
                ),
                shape = cardShape
            )
            .padding(vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = painterResource(icon),
            contentDescription = "$label icon",
            modifier = Modifier.size(40.dp),
            tint = color
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = label,
            style = MaterialTheme.typography.labelLarge
        )
    }
}