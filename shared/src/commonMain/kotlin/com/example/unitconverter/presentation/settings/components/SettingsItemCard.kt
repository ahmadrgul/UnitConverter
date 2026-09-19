package com.example.unitconverter.presentation.settings.components

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.unitconverter.domain.model.settings.SettingsItem
import com.example.unitconverter.presentation.settings.SettingsState
import com.example.unitconverter.presentation.theme.getSettingsItemIcon
import com.example.unitconverter.presentation.theme.getSettingsItemIconColor
import org.jetbrains.compose.resources.painterResource

@Composable
fun SettingsItemCard(
    state: SettingsState,
    item: SettingsItem,
    dynamicContent: @Composable () -> Unit,
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
                    contentDescription = item.id,
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
                        color = Color.Gray,
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
            dynamicContent()
        }

    }
}