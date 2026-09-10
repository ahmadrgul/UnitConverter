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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
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
            .shadow(
                elevation = 10.dp,
                shape = RoundedCornerShape(
                    topStart = 30.dp,
                    topEnd = 30.dp
                ),
                ambientColor = MaterialTheme.colorScheme.primary,
                spotColor = MaterialTheme.colorScheme.primary,
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
            NavigationBarItem(
                colors = NavigationBarItemDefaults.colors(
                    selectedTextColor = MaterialTheme.colorScheme.primary,
                    selectedIconColor = MaterialTheme.colorScheme.primary,

                    unselectedIconColor = Color.Gray,
                    unselectedTextColor = Color.Gray,

                    indicatorColor = Color.Transparent
                ),
                selected = currentDestination?.hierarchy?.any {
                    it.hasRoute(item.route::class)
                } == true,
                onClick = { onNavigateToRoute(item.route) },
                icon = {
                    Icon(
                        modifier = Modifier
                            .size(32.dp),
                        painter = painterResource(item.icon),
                        contentDescription = null
                    )
                },
                label = { Text(item.label) }
            )
        }
    }
}