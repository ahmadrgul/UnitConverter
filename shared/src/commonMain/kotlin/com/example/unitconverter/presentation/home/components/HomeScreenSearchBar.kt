package com.example.unitconverter.presentation.home.components

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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.example.unitconverter.generated.resources.Res
import com.example.unitconverter.generated.resources.search_icon
import org.jetbrains.compose.resources.painterResource


@Composable
fun HomeScreenSearchBar(
    searchQuery: String,
    onSearchQueryChange: (String) -> Unit,
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
                tint = Color.Gray,
                modifier = Modifier.size(20.dp)
            )
        },
        singleLine = true,
        shape = textFieldShape,
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = MaterialTheme.colorScheme.surface,
            focusedContainerColor = MaterialTheme.colorScheme.surface,
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedPlaceholderColor = Color.Gray,
            focusedPlaceholderColor = Color.Gray,
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
                    color = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f),
                    radius = 4.dp,
                    spread = 2.dp,
                    offset = DpOffset(0.dp, 0.dp)
                )
            )
            .fillMaxWidth()
    )
}