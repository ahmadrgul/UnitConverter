package com.example.unitconverter.presentation.settings.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.unitconverter.generated.resources.Res
import com.example.unitconverter.generated.resources.chevron_right_icon
import org.jetbrains.compose.resources.painterResource

@Composable
fun SettingsOptionWithDropdown(
    selectedOption: String,
    options: List<String>,
    onOptionSelect: (String) -> Unit,
){
    var isDropDownVisible by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier.clickable(
            onClick = { isDropDownVisible = true }
        ),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = selectedOption,
            fontSize = 14.sp,
            color = Color.Gray,
            fontWeight = FontWeight.Medium
        )
        Icon(
            painter = painterResource(Res.drawable.chevron_right_icon),
            contentDescription = "Chevron Right",
            modifier = Modifier.size(16.dp),
            tint = Color.Gray
        )
    }

    DropdownMenu(
        expanded = isDropDownVisible,
        onDismissRequest = { isDropDownVisible = false },
        shape = RoundedCornerShape(8.dp),
        tonalElevation = 1.dp,
        shadowElevation = 1.dp,
        modifier = Modifier
            .background(
                color = MaterialTheme.colorScheme.surface
            )
    ) {
        options.forEach { option ->
            DropdownMenuItem(
                text = { Text(option as String) },
                onClick = {
                    onOptionSelect(option)
                    isDropDownVisible = false
                }
            )
        }
    }
}