package com.example.unitconverter.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.unitconverter.calculator.CalculatorScreen
import com.example.unitconverter.generated.resources.Res
import com.example.unitconverter.generated.resources.history_icon
import com.example.unitconverter.generated.resources.home_icon
import com.example.unitconverter.generated.resources.settings_icon
import com.example.unitconverter.home.HomeScreen
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = { BottomBar(navController) }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Home,
            modifier = Modifier.padding(bottom = innerPadding.calculateBottomPadding())
        ) {
            composable<Home> { HomeScreen(navController) }
            composable<History> { Text("History") }
            composable<Settings> { Text("Settings") }
            composable<Calculator> { backStackEntry ->
                val routeParams = backStackEntry.toRoute<Calculator>()
                CalculatorScreen(
                    navController = navController,
                    routeId = routeParams.routeId
                )
            }
        }
    }
}

@Composable
fun BottomBar(
    navController: NavController
){
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    NavigationBar (
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
                onClick = { navController.navigate(item.route) },
                icon = { Icon(
                    modifier = Modifier
                        .size(32.dp),
                    painter = painterResource(item.icon),
                    contentDescription = null
                )},
                label = { Text(item.label) }
            )
        }
    }
}

data class NavItem(
    val label: String,
    val icon: DrawableResource,
    val route: BottomBarRoute
)

val navItems = listOf(
    NavItem("Home", Res.drawable.home_icon, route = Home),
    NavItem("History", Res.drawable.history_icon, route = History),
    NavItem("Settings", Res.drawable.settings_icon, route = Settings)
)