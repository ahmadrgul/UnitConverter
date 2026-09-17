package com.example.unitconverter.presentation.history.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.FixedScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.unitconverter.generated.resources.Res
import com.example.unitconverter.generated.resources.emtpy_clipboard
import org.jetbrains.compose.resources.painterResource

@Composable
fun EmptyHistoryBanner(
    modifier: Modifier = Modifier
){
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            painter = painterResource(Res.drawable.emtpy_clipboard),
            contentDescription = "Empty History",
            alpha = 0.2f,
            contentScale = FixedScale(1.6f)
        )

        Spacer(modifier = Modifier.height(48.dp))

        Text(
            text = "No history, yet!",
            fontSize = 24.sp,
            fontWeight = FontWeight.SemiBold,
        )
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Your conversions will appear here. Start converting to see your history.",
            textAlign = TextAlign.Center,
            color = Color.Gray.copy(0.4f),
            modifier = Modifier.padding(horizontal = 40.dp),
            lineHeight = 24.sp
        )
    }
}