package com.example.unitconverter.presentation.history.components

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.unitconverter.generated.resources.Res
import com.example.unitconverter.generated.resources.trash_icon
import org.jetbrains.compose.resources.painterResource

@Composable
fun HistoryScreenTopBar(
    onClickTrash: () -> Unit,
    isTrashEnabled: Boolean
) {
    TopAppBar(
        title = {
            Text(
                text = "History",
                fontSize = 28.sp,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onBackground
            )
        },
        actions = {
            IconButton(
                enabled = isTrashEnabled,
                onClick = onClickTrash
            ){
                Icon(
                    painter = painterResource(Res.drawable.trash_icon),
                    contentDescription = "Trash"
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.background),
    )
}