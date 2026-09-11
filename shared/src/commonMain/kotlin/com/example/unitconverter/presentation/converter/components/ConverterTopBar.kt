package com.example.unitconverter.presentation.converter.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.unitconverter.generated.resources.Res
import com.example.unitconverter.generated.resources.chevron_left_icon
import com.example.unitconverter.generated.resources.heart_filled_icon
import com.example.unitconverter.generated.resources.heart_icon
import org.jetbrains.compose.resources.painterResource

@Composable
fun ConverterTopBar(
    title: String,
    isFav: Boolean,
    onFavAction: () -> Unit,
    onNavigateBack: () -> Unit,
) {
    TopAppBar(
        title = {
            Text(
                text = title,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        },
        navigationIcon = {
            IconButton(
                onClick = onNavigateBack,
                modifier = Modifier.size(28.dp)
            ) {
                Icon(
                    painter = painterResource(Res.drawable.chevron_left_icon),
                    contentDescription = "Chevron Left",
                    modifier = Modifier.size(28.dp)
                )
            }
        },
        actions = {
            IconButton(
                onClick = onFavAction,
                modifier = Modifier.size(28.dp)
            ) {
                if (isFav) {
                    Icon(
                        painter = painterResource(Res.drawable.heart_filled_icon),
                        contentDescription = "Heart",
                        modifier = Modifier.size(24.dp),
                        tint = Color.Red
                    )
                } else {
                    Icon(
                        painter = painterResource(Res.drawable.heart_icon),
                        contentDescription = "Heart",
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.background),
        modifier = Modifier.padding(horizontal = 10.dp)
    )
}