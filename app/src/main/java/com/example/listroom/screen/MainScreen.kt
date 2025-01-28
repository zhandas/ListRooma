package com.example.listroom.screen

import android.graphics.pdf.models.ListItem
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.listroom.room.data.ItemModel
import com.example.listroom.room.data.ListModel
import com.example.listroom.viewmodel.ListViewModel

@Composable
fun MainScreen(listViewModel: ListViewModel, navController: NavController) {
    val listList by listViewModel.listOfList.collectAsState()
    val itemsList by listViewModel.listOfItems.collectAsState()

    LaunchedEffect(Unit) {
        listViewModel.getAllList()
        listViewModel.getAllItems()
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        ListOfList(
            listList = listList,
            itemsList = itemsList,
            listViewModel = listViewModel,
            navController = navController
        )
    }
}

@Composable
fun ListItem(
    listModel: ListModel,
    items: List<ItemModel>,
    listViewModel: ListViewModel,
    onNavigateToUpdateScreen: (ListModel) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 2.dp)
            .background(color = Color.DarkGray)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onNavigateToUpdateScreen(listModel) },
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                modifier = Modifier.padding(8.dp),
                text = "List: ${listModel.listName}"
            )
            IconButton(
                onClick = { listViewModel.deletelist(listModel) }
            ) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Delete",
                    tint = Color.Red
                )
            }
        }

        // Display items for this list
        val listItems = items.filter { it.categoryId == listModel.id }
        listItems.forEach { item ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 4.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "- ${item.name}",
                    color = Color.White
                )
                IconButton(
                    onClick = { listViewModel.deleteItem(item) }
                ) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Delete Item",
                        tint = Color.Red
                    )
                }
            }
        }
    }
}

@Composable
fun ListOfList(
    listList: List<ListModel>,
    itemsList: List<ItemModel>,
    listViewModel: ListViewModel,
    navController: NavController
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(6.dp)
    ) {
        items(listList) { listModel ->
            ListItem(
                listModel = listModel,
                items = itemsList,
                listViewModel = listViewModel,
                onNavigateToUpdateScreen = { model ->
                    listViewModel.setListModelToChange(model)
                    navController.navigate(Screen.updateScreen)
                }
            )
        }
    }
}