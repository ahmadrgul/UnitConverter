package com.example.unitconverter.presentation.settings.components.cards

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import com.example.unitconverter.generated.resources.chevron_down_icon
import com.example.unitconverter.generated.resources.chevron_right_icon
import com.example.unitconverter.presentation.settings.model.ChoiceOption
import com.example.unitconverter.presentation.settings.model.SettingId
import com.example.unitconverter.presentation.settings.model.SettingsItemUi
import com.example.unitconverter.presentation.theme.getSettingsItemIcon
import com.example.unitconverter.presentation.theme.getSettingsItemIconColor
import okio.Options
import org.jetbrains.compose.resources.painterResource


@Composable
fun ChoiceSettingsItemCard(
    id: SettingId,
    title: String,
    subtitle: String? = null,
    options: List<ChoiceOption>,
    currentValue: String,
    onChoiceSelect: (String) -> Unit
){
    val icon = getSettingsItemIcon(id)
    val color = getSettingsItemIconColor(id)

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
                    contentDescription = title,
                    tint = getSettingsItemIconColor(id),
                    modifier = Modifier.size(26.dp)
                )
            }

            Column (
                modifier = Modifier
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.spacedBy(4.dp, alignment = Alignment.CenterVertically)
            ) {
                Text(
                    text = title,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 14.sp
                )

                if (subtitle != null) {
                    Text(
                        text = subtitle,
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
            CustomChoice(
                options = options,
                onChoiceSelect = onChoiceSelect,
                currentValue = currentValue
            )
        }

    }
}

@Composable
fun CustomChoice(
    currentValue: String,
    options: List<ChoiceOption>,
    onChoiceSelect: (String) -> Unit,
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
            text = currentValue,
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
            fontWeight = FontWeight.Medium
        )
        if (isDropDownVisible) {
            Icon(
                painter = painterResource(Res.drawable.chevron_down_icon),
                contentDescription = "Chevron Down",
                modifier = Modifier.size(16.dp),
                tint = Color.Gray
            )
        } else {
            Icon(
                painter = painterResource(Res.drawable.chevron_right_icon),
                contentDescription = "Chevron Right",
                modifier = Modifier.size(16.dp),
                tint = Color.Gray
            )
        }
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
                text = { Text(option.label) },
                onClick = {
                    onChoiceSelect(option.value)
                    isDropDownVisible = false
                }
            )
        }
    }
}
