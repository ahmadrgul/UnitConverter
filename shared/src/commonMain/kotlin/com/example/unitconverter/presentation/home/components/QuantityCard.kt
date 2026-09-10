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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource


@Composable
fun QuantityCard(
    label: String,
    icon: DrawableResource,
    color: Color,
    onClick: () -> Unit,
) {
    val cardShape = RoundedCornerShape(12.dp)

    Column(
        modifier = Modifier
            .border(
                color = color.copy(alpha = 0.1f),
                width = 0.5.dp,
                shape = cardShape
            )
            .shadow(
                elevation = 1.dp,
                shape = cardShape,
                ambientColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.8f),
                spotColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.8f)
            )
            .clickable(onClick = onClick)
            .background(
                color = MaterialTheme.colorScheme.surface,
                shape = cardShape
            )
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        color.copy(alpha = 0.01f),
                        color.copy(alpha = 0.05f)
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