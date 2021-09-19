package com.trekware.shoppinglistcx.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.trekware.shoppinglistcx.MainViewModel
import com.trekware.shoppinglistcx.Screens
import com.trekware.shoppinglistcx.ShoppingList

@Composable
fun GroceryScreen(modifier: Modifier = Modifier, viewModel: MainViewModel) {
    viewModel.setCurrentScreen(Screens.DrawerScreens.Grocery)
    val items = viewModel.getShoppingList().observeAsState(listOf()).value
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ShoppingList(items, viewModel)
    }
}