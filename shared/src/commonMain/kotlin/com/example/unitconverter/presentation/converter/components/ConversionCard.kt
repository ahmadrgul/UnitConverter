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
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.platform.InterceptPlatformTextInput
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.unitconverter.domain.model.quantity.unit.QuantityUnit
import com.example.unitconverter.generated.resources.Res
import com.example.unitconverter.generated.resources.chevron_down_icon
import kotlinx.coroutines.awaitCancellation
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ConversionCard(
    label: String,
    value: TextFieldValue,
    autoFocused: Boolean = false,
    unit: QuantityUnit,
    approximateValue: String,
    editable: Boolean,
    color: Color,
    onValueChange: (TextFieldValue) -> Unit,
    onUnitClick: () -> Unit,
    decorationBox: @Composable (@Composable () -> Unit) -> Unit,
) {
    val cardShape = RoundedCornerShape(20.dp)

    val focusRequester = remember { FocusRequester() }
    LaunchedEffect(Unit) {
        if (autoFocused) {
            focusRequester.requestFocus()
        }
    }

    Column(
        modifier = Modifier
            .dropShadow(
                shadow = Shadow(
                    color = Color.Black.copy(alpha = 0.05f),
                    radius = 8.dp,
                    spread = 2.dp,
                    offset = DpOffset(0.dp, 0.dp)
                ),
                shape = cardShape
            )
            .background(
                shape = cardShape,
                color = MaterialTheme.colorScheme.surface
            )
            .padding(20.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = label,
            color = Color.Gray,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium
        )

        Spacer(modifier = Modifier.height(24.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(2.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            InterceptPlatformTextInput(
                interceptor = { _, _ ->
                    awaitCancellation()
                }
            ) {
                BasicTextField(
                    readOnly = !editable,
                    value = value,
                    onValueChange = { onValueChange(it) },
                    textStyle = TextStyle(
                        color = color,
                        fontSize = 40.sp,
                        fontWeight = FontWeight.SemiBold,
                        lineHeight = 48.sp,
                    ),
                    singleLine = true,
                    decorationBox = decorationBox,
                    modifier = Modifier
                        .weight(1f)
                        .focusRequester(focusRequester)
                )
            }

            val interactionSource = remember { MutableInteractionSource() }
            Row(
                modifier = Modifier.clickable(
                    interactionSource = interactionSource,
                    indication = null,
                    onClick = onUnitClick
                ),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                val unitDisplay = if ("${unit.unitName} (${unit.symbol})".length <= 18 ) "${unit.unitName} (${unit.symbol})" else "(${unit.symbol})"

                Text(
                    text = unitDisplay,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 14.sp,
                )
                Icon(
                    painter = painterResource(Res.drawable.chevron_down_icon),
                    contentDescription = "Chevron Down",
                    modifier = Modifier.size(18.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "≈ $approximateValue ${unit.symbol}",
            color = MaterialTheme.colorScheme.onSurface.copy(0.5f),
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
        )
    }
}