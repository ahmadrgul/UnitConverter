package com.example.unitconverter.presentation.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.unitconverter.generated.resources.Res
import com.example.unitconverter.generated.resources.trash_icon
import org.jetbrains.compose.resources.painterResource

@Composable
fun HomeScreenTopBar() {
    TopAppBar(
        title = {
            Column {
                Text(
                    text = "Unit Converter",
                    style = MaterialTheme.typography.headlineLarge,
                    fontSize = 24.sp,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Text(
                    text = "Convert Anything, Anywhere.",
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onBackground.copy(0.5f),
                    modifier = Modifier.padding(start = 1.dp)
                )
            }
        },
//        actions = {
//            IconButton(
//                enabled =
//                onClick =
//            ){
//                Icon(
//                    painter = painterResource(Res.drawable.trash_icon),
//                    contentDescription = "Trash"
//                )
//            }
//        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent),
    )
}