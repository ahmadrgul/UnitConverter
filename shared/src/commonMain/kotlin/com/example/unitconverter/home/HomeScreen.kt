package com.example.unitconverter.home

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.unitconverter.generated.resources.Res
import com.example.unitconverter.generated.resources.area_icon
import com.example.unitconverter.generated.resources.clock_icon
import com.example.unitconverter.generated.resources.flash_icon
import com.example.unitconverter.generated.resources.jar_icon
import com.example.unitconverter.generated.resources.power_icon
import com.example.unitconverter.generated.resources.pressure_icon
import com.example.unitconverter.generated.resources.ruler_icon
import com.example.unitconverter.generated.resources.search_icon
import com.example.unitconverter.generated.resources.speed_icon
import com.example.unitconverter.generated.resources.thermostat_icon
import com.example.unitconverter.generated.resources.weight_icon
import com.example.unitconverter.generated.resources.wrench_icon
import com.example.unitconverter.navigation.Calculator
import com.example.unitconverter.navigation.Measurement
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun HomeScreen(navController: NavController) {
    Scaffold(
        topBar = { TopAppBar(
            title = { HomeScreenTopBar() },
            colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.background),
//            windowInsets = WindowInsets.safeDrawing,
        )},
    ) { innerPadding ->
        Column (
            modifier = Modifier.padding(top = innerPadding.calculateTopPadding())
        ) {
            HomeSearchBar()
            LazyVerticalGrid(
                columns = GridCells.Adaptive(minSize = 100.dp),
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(
                    start = 16.dp,
                    end = 16.dp,
                    bottom = 16.dp,
                ),
                horizontalArrangement = Arrangement.spacedBy(6.dp),
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                item(span = { GridItemSpan(maxLineSpan) }) {
                    SectionHeading("Everyday Measurements")
                }

                items(measurements) { item ->
                    MeasurementCardItem(
                        label = item.label,
                        icon = item.icon,
                        color = item.color,
                        onClick = { navController.navigate(Calculator(item.route))}
                    )
                }

                item(span = { GridItemSpan(maxLineSpan) }) {
                    Spacer(modifier = Modifier.height(16.dp))
                }

                item(span = { GridItemSpan(maxLineSpan) }) {
                    SectionHeading("Mechanical Units")
                }

                items(mechanicalUnits) { item ->
                    MeasurementCardItem(
                        label = item.label,
                        icon = item.icon,
                        color = item.color,
                        onClick = { navController.navigate(Calculator(item.route)) }
                    )
                }
            }
        }
    }
}


@Composable
fun HomeScreenTopBar() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column {
            Text(
                text = "Unit Converter",
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.onBackground
            )
            Spacer(Modifier.height(2.dp))
            Text(
                text = "Convert Anything, Anywhere.",
                style = MaterialTheme.typography.labelMedium,
                color = Color.Gray
            )
        }
    }
}

@Composable
fun HomeSearchBar() {
    var query by remember { mutableStateOf("") }

    val textFieldShape = RoundedCornerShape(25.dp)

    OutlinedTextField(
        value = query,
        onValueChange = { query = it },
        placeholder = { Text ("Search units to convert...")},
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

@Composable
fun SectionHeading(
    text: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.titleLarge
        )
        Text(
            text = "See All",
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.primary
        )
    }
}

@Composable
fun MeasurementCardItem(
    label: String,
    icon: DrawableResource,
    color: Color,
    onClick: () -> Unit,
) {
    val cardShape = RoundedCornerShape(12.dp)

    Column(
        modifier = Modifier
            .border(
                color = color.copy(alpha = 0.1f),
                width = 0.5.dp,
                shape = cardShape
            )
            .shadow(
                elevation = 1.dp,
                shape = cardShape,
                ambientColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.8f),
                spotColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.8f)
            )
            .clickable(onClick = onClick)
            .background(
                color = MaterialTheme.colorScheme.surface,
                shape = cardShape
            )
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        color.copy(alpha = 0.01f),
                        color.copy(alpha = 0.05f)
                    ),
                ),
                shape = cardShape
            )
            .padding(vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = painterResource(icon),
            contentDescription = "$label icon",
            modifier = Modifier.size(40.dp),
            tint = color
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = label,
            style = MaterialTheme.typography.labelLarge
        )
    }
}

data class MeasurementCard(
    val label: String,
    val icon: DrawableResource,
    val color: Color,
    val route: Measurement
)

val measurements = listOf(
    MeasurementCard("Length", Res.drawable.ruler_icon, Color(0xFF1FA971), route = Measurement.LENGTH),
    MeasurementCard("Area", Res.drawable.area_icon, Color(0xFF4C6FFF), Measurement.AREA),
    MeasurementCard("Time", Res.drawable.clock_icon, Color(0xFF8B5CF6), Measurement.TIME),
    MeasurementCard("Volume", Res.drawable.jar_icon, Color(0xFFF5941D), Measurement.VOLUME),
    MeasurementCard("Temperature", Res.drawable.thermostat_icon, Color(0xFFF0454F), Measurement.TEMPERATURE),
    MeasurementCard("Weight", Res.drawable.weight_icon, Color(0xFF14B8A6), Measurement.WEIGHT),
    MeasurementCard("Speed", Res.drawable.speed_icon, Color(0xFF2F80ED), Measurement.SPEED),
    MeasurementCard("Energy", Res.drawable.flash_icon, Color(0xFFFF9F1C), Measurement.ENERGY)
)

val mechanicalUnits = listOf(
    MeasurementCard("Power", Res.drawable.power_icon, Color(0xFF8B5CF6), Measurement.POWER),
    MeasurementCard("Torque", Res.drawable.wrench_icon, Color(0xFF34A853), Measurement.TORQUE),
    MeasurementCard("Pressure", Res.drawable.pressure_icon, Color(0xFFF5941D), Measurement.PRESSURE),
)