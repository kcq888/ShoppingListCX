package com.trekware.shoppinglistcx

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Checkbox
import androidx.compose.material.ListItem
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.trekware.shoppinglistcx.data.ShoppingListItem

@Composable
fun ShoppingList(shoppingList: List<ShoppingListItem>, viewModel: MainViewModel) {
    LazyColumn (
        Modifier.fillMaxSize()
    ) {
        items(shoppingList) {
            ItemRow(item = it, onCheckBoxClicked = {
                viewModel.updateItem(it)
            })
        }
    }
}

@Composable
fun ItemRow(item: ShoppingListItem, onCheckBoxClicked: (ShoppingListItem) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 15.dp, vertical = 8.dp)
    ) {
       val checkState = remember{ mutableStateOf(item.isItemFill)}
        Checkbox(
            checked = checkState.value,
            onCheckedChange = {
                checkState.value = it
                item.isItemFill = it
                onCheckBoxClicked(item)
            }
        )
        Spacer(modifier = Modifier.width(10.dp))
        Column (
            Modifier.width(200.dp)
        ) {
            Text(text = item.description)
        }
        Column( Modifier.width(50.dp)) {
            Text(text = item.itemQty.toString())
        }
        Column(Modifier.width(50.dp)) {
            Text(text = "$" + item.itemCost.toString())
        }
    }
}