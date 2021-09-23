package com.trekware.shoppinglistcx

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.trekware.shoppinglistcx.screens.GroceryScreen
import com.trekware.shoppinglistcx.screens.HomeImprovementScreen
import com.trekware.shoppinglistcx.ui.theme.ShoppingListCXTheme
import kotlinx.coroutines.launch
import androidx.lifecycle.viewmodel.compose.viewModel as viewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ShoppingListCXTheme {
                    AppShoppingList()
                }
            }
    }
}

/*
 * AppShoppingList is the main App main landing screen with navigation drawer, action and fab buttons
 */
@Composable
fun AppShoppingList() {
    val viewModel: MainViewModel = MainViewModel(LocalContext.current)
    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
    val scope = rememberCoroutineScope()
    val navController = rememberNavController()
    val currentScreen by viewModel.currentScreen.observeAsState()

    /*
     * Using Google Material Design's Scaffold UI component to build the hamburger menu
     * and navigation drawer. Adding the TopAppBar with an action button. The Action button
     * deletes all content of the database
     */
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                title = { Text(text = currentScreen!!.title) },
                navigationIcon = {
                    IconButton(onClick = {
                        scope.launch {
                            scaffoldState.drawerState.open()
                        }
                    }) {
                        Icon(Icons.Filled.Menu, contentDescription = "hamburger menu")
                    }
                },
                backgroundColor = MaterialTheme.colors.primary,
                actions = {
                    IconButton(onClick = {
                        viewModel.delAll()
                    }) {
                        Icon(Icons.Filled.Delete, null)
                    }
                }
            )
        },
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(Screens.HomeScreen.AddList.route)
                }
            ){
                Icon(Icons.Filled.Add, "")
            }
        },
        drawerContent = {
            Drawer { route ->
                scope.launch {
                    scaffoldState.drawerState.close()
                }
                navController.navigate(route) {
                    launchSingleTop = true
                }
            }
        },
        drawerGesturesEnabled = scaffoldState.drawerState.isOpen,
    ){ innerPadding ->
        NavigationHost(navController = navController, viewModel = viewModel)
    }
}

/*
 * The NavigationHost composable builds the navigation graph between the list and add list screen
 * and switching between the category list screens.
 */
@Composable
fun NavigationHost(navController: NavController, viewModel: MainViewModel) {
    NavHost(
        navController = navController as NavHostController,
        startDestination = Screens.DrawerScreens.Grocery.route
    ) {
        composable(Screens.DrawerScreens.Grocery.route) { GroceryScreen(viewModel = viewModel) }
        composable(Screens.DrawerScreens.HomeImprovement.route) { HomeImprovementScreen(viewModel = viewModel) }
        composable(Screens.HomeScreen.AddList.route) { AddListScreen(viewModel = viewModel, navController = navController) }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ShoppingListCXTheme {
        AppShoppingList()
    }
}