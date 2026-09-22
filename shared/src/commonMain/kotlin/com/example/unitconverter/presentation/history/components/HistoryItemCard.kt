package com.example.unitconverter.presentation.history.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.PopupProperties
import com.example.unitconverter.core.utils.ClipboardService
import com.example.unitconverter.generated.resources.Res
import com.example.unitconverter.generated.resources.arrow_right_icon
import com.example.unitconverter.generated.resources.copy_icon
import com.example.unitconverter.generated.resources.ellipsis_icon
import com.example.unitconverter.generated.resources.star_icon
import com.example.unitconverter.generated.resources.star_icon_filled
import com.example.unitconverter.generated.resources.trash_icon
import com.example.unitconverter.presentation.theme.getQuantityIcon
import org.jetbrains.compose.resources.painterResource

@Composable
fun HistoryItemCard(
    quantityName: String,
    quantityId: String,
    fromUnit: String,
    toUnit: String,
    inputValue: String,
    convertedValue: String,
    isStarred: Boolean,
    color: Color,
    timestamp: String,
    onDelete: () -> Unit,
    onStarred: () -> Unit,
    onCopy: () -> Unit
){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
            .dropShadow(
                shape = RoundedCornerShape(14.dp),
                shadow = Shadow(
                    color = Color.Black.copy(alpha = 0.05f),
                    radius = 8.dp,
                    spread = 0.dp,
                    offset = DpOffset(0.dp, 0.dp)
                )
            )
            .background(
                color = MaterialTheme.colorScheme.surface,
                shape = RoundedCornerShape(14.dp)
            )
            .padding(horizontal = 14.dp, vertical = 14.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Min)
        ) {
            Box(
                modifier = Modifier
                    .background(
                        color = color.copy(alpha = 0.05f),
                        shape = RoundedCornerShape(10.dp)
                    )
                    .padding(8.dp),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(getQuantityIcon(quantityId)),
                    contentDescription = quantityId,
                    tint = color,
                    modifier = Modifier.size(24.dp)
                )
            }

            Spacer(modifier = Modifier.width(10.dp))

            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceAround
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = inputValue,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Spacer(modifier = Modifier.width(3.dp))
                    Text(
                        text = fromUnit,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                }

                Text(
                    text = quantityName,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }

            Box(
                modifier = Modifier.fillMaxHeight(),
                contentAlignment = Alignment.BottomCenter
            ){
                Icon(
                    painter = painterResource(Res.drawable.arrow_right_icon),
                    contentDescription = "Arrow Right",
                    tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
                    modifier = Modifier.size(20.dp)
                )
            }


            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceAround,
                horizontalAlignment = Alignment.End
            ) {
                Text(
                    text = convertedValue,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = toUnit,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            Box(
                modifier = Modifier.fillMaxHeight(),
                contentAlignment = Alignment.Center
            ) {

                var expanded by remember { mutableStateOf(false) }

                IconButton(
                    onClick = { expanded = true },
                    modifier = Modifier.size(24.dp),
                ) {
                    Icon(
                        painter = painterResource(Res.drawable.ellipsis_icon),
                        contentDescription = "Ellipsis",
                        tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
                        modifier = Modifier.size(16.dp)
                    )
                }

                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                    shape = RoundedCornerShape(8.dp),
                    tonalElevation = 1.dp,
                    shadowElevation = 1.dp,
                    modifier = Modifier
                        .background(
                            color = MaterialTheme.colorScheme.surface
                        )
                ) {
                    DropdownMenuItem(
                        leadingIcon = {
                            Icon(
                                painter = painterResource(Res.drawable.copy_icon),
                                contentDescription = "Copy",
                                modifier = Modifier.size(18.dp)
                            )
                        },
                        text = {
                            Text(
                                text = "Copy",
                                fontSize = 14.sp
                            )
                        },
                        onClick = {
                            onCopy()
                            expanded = false
                        },
                    )

                    DropdownMenuItem(
                        leadingIcon = {
                            Icon(
                                painter = painterResource(Res.drawable.trash_icon),
                                contentDescription = "Trash",
                                tint = Color.Red,
                                modifier = Modifier.size(18.dp)
                            )
                        },
                        text = {
                            Text(
                                text = "Delete",
                                color = Color.Red,
                                fontSize = 14.sp
                            )
                        },
                        onClick = {
                            onDelete()
                            expanded = false
                        },
                    )
                }
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = timestamp,
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
            )

            IconButton(
                onClick = onStarred,
                modifier = Modifier.size(24.dp),
            ) {
                if (isStarred) {
                    Icon(
                        painter = painterResource(Res.drawable.star_icon_filled),
                        contentDescription = "Yellow Star",
                        tint = Color(0xFFFFBF00),
                        modifier = Modifier.size(16.dp)
                    )
                } else {
                    Icon(
                        painter = painterResource(Res.drawable.star_icon),
                        contentDescription = "Star",
                        tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
                        modifier = Modifier.size(16.dp)
                    )
                }
            }
        }
    }
}