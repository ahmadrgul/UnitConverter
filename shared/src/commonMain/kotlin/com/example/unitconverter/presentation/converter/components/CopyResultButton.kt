package com.example.unitconverter.presentation.converter.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.unitconverter.generated.resources.Res
import com.example.unitconverter.generated.resources.copy_icon
import org.jetbrains.compose.resources.painterResource

@Composable
fun CopyResultButton(
    result: String,
    onCopyResults: () -> Unit
) {

    Button(
        onClick = onCopyResults,
        enabled = result.isNotEmpty(),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = Color.White,

            disabledContentColor = Color.LightGray,
            disabledContainerColor = Color.LightGray.copy(alpha = 0.15f)
        ),
        shape = RoundedCornerShape(20.dp),
        contentPadding = PaddingValues(vertical = 14.dp),
        modifier = Modifier
            .padding(horizontal = 12.dp)
            .fillMaxWidth()
    ) {
        Icon(
            painter = painterResource(Res.drawable.copy_icon),
            contentDescription = "Copy",
        )

        Spacer(modifier = Modifier.width(10.dp))

        Text(
            text = "Copy Result",
            fontWeight = FontWeight.Medium,
            fontSize = 16.sp
        )
    }
}