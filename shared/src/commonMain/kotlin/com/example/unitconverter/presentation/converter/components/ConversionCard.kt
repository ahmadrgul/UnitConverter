package com.example.unitconverter.presentation.converter.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.unitconverter.domain.model.unit.QuantityUnit
import com.example.unitconverter.generated.resources.Res
import com.example.unitconverter.generated.resources.chevron_down_icon
import org.jetbrains.compose.resources.painterResource

@Composable
fun ConversionCard(
    label: String,
    value: String,
    approximateValue: String,
    editable: Boolean,
    onValueChange: (String) -> Unit,
    unit: QuantityUnit,
    onUnitClick: () -> Unit,
    decorationBox: @Composable (@Composable () -> Unit) -> Unit,
) {
    val cardShape = RoundedCornerShape(24.dp)

    Column(
        modifier = Modifier
            .dropShadow(
                shadow = Shadow(
                    color = Color.LightGray.copy(alpha = 0.4f),
                    radius = 6.dp,
                    spread = 2.dp,
                    offset = DpOffset(0.dp, 0.dp)
                ),
                shape = cardShape
            )
            .background(
                shape = cardShape,
                color = MaterialTheme.colorScheme.surface
            )
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = label,
            color = Color.LightGray,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
        )

        Spacer(modifier = Modifier.height(20.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(2.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            BasicTextField(
                enabled = editable,
                value = value,
                onValueChange = { onValueChange(it) },
                textStyle = TextStyle(
                    color = Color.DarkGray,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.SemiBold,
                ),
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done,
                    keyboardType = KeyboardType.Number
                ),
                decorationBox = decorationBox,
                modifier = Modifier.weight(1f)
            )

            val interactionSource = remember { MutableInteractionSource() }

            Row(
                modifier = Modifier.clickable(
                    interactionSource = interactionSource,
                    indication = null,
                    onClick = onUnitClick
                ),
                horizontalArrangement = Arrangement.spacedBy(2.dp)
            ) {
                Text(
                    text = "${unit.unitName} (${unit.symbol})",
                    fontWeight = FontWeight.SemiBold,
                    color = Color.DarkGray,
                    fontSize = 14.sp,
                )
                Icon(
                    painter = painterResource(Res.drawable.chevron_down_icon),
                    contentDescription = "Chevron Down",
                    modifier = Modifier.size(18.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "≈ $approximateValue",
            color = Color.LightGray,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
        )
    }
}