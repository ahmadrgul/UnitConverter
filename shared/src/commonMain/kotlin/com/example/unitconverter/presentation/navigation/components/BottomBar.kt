package com.example.unitconverter.presentation.navigation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.shadow.Shadow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import com.example.unitconverter.presentation.navigation.BottomBarRoute
import com.example.unitconverter.presentation.navigation.navItems
import org.jetbrains.compose.resources.painterResource


@Composable
fun BottomBar(
    currentDestination: NavDestination?,
    onNavigateToRoute: (BottomBarRoute) -> Unit
) {
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.surface,
        modifier = Modifier
            .dropShadow(
                shape = RoundedCornerShape(
                    topStart = 20.dp,
                    topEnd = 20.dp
                ),
                shadow = Shadow(
                    color = Color.Black.copy(alpha = 0.05f),
                    radius = 6.dp,
                    spread = 0.dp
                )
            )
            .background(
                color = MaterialTheme.colorScheme.surface,
                shape = RoundedCornerShape(
                    topStart = 20.dp,
                    topEnd = 20.dp
                )
            )
            .padding(horizontal = 16.dp)
    ) {
        navItems.forEach { item ->
            val isSelected = currentDestination?.hierarchy?.any { it.hasRoute(item.route::class) } == true

            NavigationBarItem(
                colors = NavigationBarItemDefaults.colors(
                    unselectedIconColor = MaterialTheme.colorScheme.onSurface.copy(0.8f),
                    indicatorColor = Color.Transparent
                ),
                selected = isSelected,
                onClick = { onNavigateToRoute(item.route) },
                icon = {
                    if (isSelected) {
                        Icon(
                            modifier = Modifier
                                .size(36.dp),
                            painter = painterResource(item.selectedIcon),
                            tint = MaterialTheme.colorScheme.primary,
                            contentDescription = item.label
                        )
                    } else {
                        Icon(
                            modifier = Modifier
                                .size(32.dp),
                            painter = painterResource(item.icon),
                            contentDescription = item.label
                        )
                    }
                }
            )
        }
    }
}