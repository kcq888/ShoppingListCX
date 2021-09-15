package com.trekware.shoppinglistcx

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screens(val route: String, val title: String) {

    sealed class HomeScreen(
        route: String,
        title: String
    ) : Screens(route, title) {
        object AddList : HomeScreen("addlist", "Add List")
    }

    sealed class DrawerScreens(
        route: String,
        title: String
    ) : Screens(route, title) {
        object Grocery : DrawerScreens("grocery", "Grocery")
        object HomeImprovement : DrawerScreens("homeimprovement", "Home Improvement")
    }
}

val screensFromFab = listOf(
    Screens.HomeScreen.AddList
)

val screensFromDrawer = listOf(
    Screens.DrawerScreens.Grocery,
    Screens.DrawerScreens.HomeImprovement,
)