package com.trekware.shoppinglistcx

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Drawer(
    modifier: Modifier = Modifier,
    onDestinatonClicked: (route: String) -> Unit
) {
    Column(
        modifier
            .fillMaxSize()
            .padding(start = 24.dp, top = 48.dp)
    ) {
       Row(
           verticalAlignment = Alignment.CenterVertically
       ) {
           Image(
            painter = painterResource(R.drawable.ic_check_document_done_list_paper),
            contentDescription = "App icon")
            Text(stringResource(id = R.string.shoppinglist), fontSize = 28.sp)
       }
        Divider(color = Color.LightGray, thickness = 2.dp)
        screensFromDrawer.forEach { screen ->
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = screen.title,
                style = MaterialTheme.typography.h6,
                modifier = Modifier.clickable {
                    onDestinatonClicked(screen.route)
                }
            )
        }
    }
}