package com.trekware.shoppinglistcx

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.trekware.shoppinglistcx.data.ShoppingListItem

@Composable
fun AddListScreen(modifier: Modifier = Modifier, viewModel: MainViewModel, navController: NavController) {
    val horzpadding = 15.dp
    val vertpadding = 5.dp
    Column(
        Modifier
            .padding(horizontal = horzpadding, vertical = vertpadding)
            .fillMaxSize()
    ) {
        Row(
            Modifier
                .padding(horizontal = horzpadding, vertical = vertpadding),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(R.drawable.ic_check_document_done_list_paper),
                contentDescription = "App icon"
            )
            Text(stringResource(id = R.string.additemtolist), fontSize = 24.sp)
        }
        Divider(color = Color.LightGray, thickness = 2.dp)
        Spacer(modifier = Modifier.height(10.dp))
        val description = remember { mutableStateOf(TextFieldValue()) }
        val quantity = remember { mutableStateOf(TextFieldValue()) }
        val cost = remember { mutableStateOf(TextFieldValue()) }
        OutlinedTextField(
            value = description.value,
            onValueChange = { description.value = it },
            placeholder = { Text(text = "Description") },
            modifier = Modifier
                .padding(horizontal = horzpadding, vertical = vertpadding)
                .fillMaxWidth()
        )
        OutlinedTextField(
            value = quantity.value,
            onValueChange = { quantity.value = it },
            placeholder = { Text(text = "Quantity") },
            modifier = Modifier
                .padding(horizontal = horzpadding, vertical = vertpadding)
                .fillMaxWidth()
        )
        OutlinedTextField(
            value = cost.value,
            onValueChange = { cost.value = it },
            placeholder = { Text(text = "Cost") },
            modifier = Modifier
                .padding(horizontal = horzpadding, vertical = vertpadding)
                .fillMaxWidth()
        )
        Row(
            modifier = Modifier
                .padding(horizontal = horzpadding, vertical = vertpadding)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
        )
        {
            Button(
                onClick = { navController.navigateUp()},
                modifier = Modifier
                    .width(100.dp)
                ) {
                Text(text = "Cancel")
            }
            Spacer(modifier = Modifier.size(20.dp))
            Button(
                onClick = {
                    // collect data and have the view model to add to the database
                    val shoppingListItem = ShoppingListItem()
                    shoppingListItem.description = description.value.text
                    shoppingListItem.itemQty = quantity.value.text.toLong()
                    shoppingListItem.itemCost = cost.value.text.toDouble()
                    viewModel.addItem(shoppingListItem)
                    navController.navigateUp()
                },
                modifier = Modifier
                    .width(100.dp)
            ) {
                Text(text = "OK")
            }
        }
    }
}