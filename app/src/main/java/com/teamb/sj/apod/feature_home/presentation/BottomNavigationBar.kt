package com.teamb.sj.apod.feature_home.presentation

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.teamb.sj.apod.core.util.Constants

@Composable
fun BottomNavigationBar(navController: NavHostController) {

    BottomNavigation() {
        val navBackStackEntry by navController.currentBackStackEntryAsState()

        val currentDestination = navBackStackEntry?.destination
        Constants.BottomNavItems.forEach { navItem ->
            BottomNavigationItem(
                selected = currentDestination?.hierarchy?.any { it.route == navItem.route } == true,
                onClick = {
                    navController.navigate(navItem.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = { Icon(imageVector = navItem.icon, contentDescription = navItem.label) },
                label = { Text(text = navItem.label) },
                alwaysShowLabel = true
            )
        }
    }
}