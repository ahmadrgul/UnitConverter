package com.example.unitconverter.presentation.converter.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.unitconverter.generated.resources.Res
import com.example.unitconverter.generated.resources.copy_icon
import org.jetbrains.compose.resources.painterResource

@Composable
fun CopyResultButton() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(24.dp))
            .background(
                color = MaterialTheme.colorScheme.primary
            )
            .clickable(onClick = {
            })
            .padding(vertical = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp, Alignment.CenterHorizontally),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(Res.drawable.copy_icon),
            contentDescription = "Copy",
            tint = Color.White
        )
        Text(
            text = "Copy Result",
            color = Color.White,
            fontWeight = FontWeight.Medium
        )
    }
}