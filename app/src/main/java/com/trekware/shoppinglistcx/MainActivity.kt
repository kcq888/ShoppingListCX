package com.trekware.shoppinglistcx

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
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
                    AppScaffold()
                }
            }
    }
}

@Composable
fun AppScaffold() {
    val viewModel: MainViewModel = viewModel()
    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
    val scope = rememberCoroutineScope()
    val navController = rememberNavController()
    val currentScreen by viewModel.currentScreen.observeAsState()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                title = { Text("TopAppBar") },
                navigationIcon = {
                    IconButton(onClick = {
                        scope.launch {
                            scaffoldState.drawerState.open()
                        }
                    }) {
                        Icon(Icons.Filled.Menu, contentDescription = "hamburger menu")
                    }
                },
                backgroundColor = MaterialTheme.colors.primary)  },
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
                    //popUpToId = navController.graph.startDestinationId
                    launchSingleTop = true
                }
            }
        },
        drawerGesturesEnabled = scaffoldState.drawerState.isOpen,
    ){ innerPadding ->
        NavigationHost(navController = navController, viewModel = viewModel)
    }
}

@Composable
fun NavigationHost(navController: NavController, viewModel: MainViewModel) {
    NavHost(
        navController = navController as NavHostController,
        startDestination = Screens.DrawerScreens.Grocery.route
    ) {
        composable(Screens.DrawerScreens.Grocery.route) { GroceryScreen(viewModel = viewModel) }
        composable(Screens.DrawerScreens.HomeImprovement.route) { HomeImprovementScreen(viewModel = viewModel) }
        composable(Screens.HomeScreen.AddList.route) { AddListScreen(viewModel = viewModel) }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ShoppingListCXTheme {
        AppScaffold()
    }
}