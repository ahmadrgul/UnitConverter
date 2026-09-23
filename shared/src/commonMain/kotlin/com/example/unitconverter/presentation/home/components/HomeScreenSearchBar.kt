package com.example.unitconverter.presentation.home.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.example.unitconverter.generated.resources.Res
import com.example.unitconverter.generated.resources.search_icon
import com.example.unitconverter.generated.resources.x_icon
import org.jetbrains.compose.resources.painterResource


@Composable
fun HomeScreenSearchBar(
    searchQuery: String,
    onSearchQueryChange: (String) -> Unit,
    clearFocus: () -> Unit,
    onFocusChange: (Boolean) -> Unit,
) {
    val textFieldShape = RoundedCornerShape(25.dp)

    OutlinedTextField(
        value = searchQuery,
        onValueChange = { onSearchQueryChange(it) },
        placeholder = { Text("Search units to convert...") },
        leadingIcon = {
            Icon(
                painter = painterResource(Res.drawable.search_icon),
                contentDescription = "Search Icon",
                tint = MaterialTheme.colorScheme.onSurface.copy(0.8f),
                modifier = Modifier.size(20.dp)
            )
        },
        trailingIcon = {
            if (searchQuery.isNotEmpty()) {
                Icon(
                    painter = painterResource(Res.drawable.x_icon),
                    contentDescription = "Cross Icon",
                    tint = MaterialTheme.colorScheme.onSurface.copy(0.8f),
                    modifier = Modifier
                        .size(14.dp)
                        .clickable(
                            interactionSource = MutableInteractionSource(),
                            indication = null,
                            onClick = {
                                onSearchQueryChange("")
                                clearFocus()
                            }
                        )
                )
            }
        },
        singleLine = true,
        shape = textFieldShape,
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = MaterialTheme.colorScheme.surface,
            focusedContainerColor = MaterialTheme.colorScheme.surface,
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = MaterialTheme.colorScheme.primary,
            unfocusedPlaceholderColor = MaterialTheme.colorScheme.onSurface.copy(0.5f),
            focusedPlaceholderColor = MaterialTheme.colorScheme.onSurface.copy(0.5f),
        ),
        modifier = Modifier
            .padding(
                top = 16.dp,
                start = 16.dp,
                end = 16.dp,
                bottom = 16.dp,
            )
            .dropShadow(
                shape = textFieldShape,
                shadow = Shadow(
                    color = Color.Black.copy(alpha = 0.05f),
                    radius = 5.dp,
                    spread = 0.dp
                )
            )
            .fillMaxWidth()
            .onFocusChanged { focusState -> onFocusChange(focusState.isFocused) }
    )
}