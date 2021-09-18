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

sealed class Screens(val route: String, val title: String, val category: Category) {

    enum class Category { GROCERY, HOMEIMPROVEMENT, ADDLIST }

    sealed class HomeScreen(
        route: String,
        title: String,
        category : Category
    ) : Screens(route, title, category) {
        object AddList : HomeScreen("addlist", "Add List", Category.ADDLIST)
    }

    sealed class DrawerScreens(
        route: String,
        title: String,
        category: Category
    ) : Screens(route, title, category) {
        object Grocery : DrawerScreens("grocery", "Grocery", Category.GROCERY)
        object HomeImprovement : DrawerScreens("homeimprovement", "Home Improvement", Category.HOMEIMPROVEMENT)
    }
}

val screensFromFab = listOf(
    Screens.HomeScreen.AddList
)

val screensFromDrawer = listOf(
    Screens.DrawerScreens.Grocery,
    Screens.DrawerScreens.HomeImprovement,
)