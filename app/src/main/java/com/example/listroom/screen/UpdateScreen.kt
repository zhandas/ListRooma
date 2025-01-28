package com.example.listroom.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.listroom.room.data.ItemModel
import com.example.listroom.viewmodel.ListViewModel

@Composable
fun UpdateScreen(listViewModel: ListViewModel) {
    val listName by listViewModel.listName.collectAsState()
    val itemName by listViewModel.itemName.collectAsState()
    val selectedList by listViewModel.listModel.collectAsState()
    val selectedItem by listViewModel.itemModel.collectAsState()
    val allItems by listViewModel.listOfItems.collectAsState()

    LaunchedEffect(selectedList) {
        listViewModel.getAllItems()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // List name update section
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Update List Name",
                    style = MaterialTheme.typography.titleMedium
                )
                Spacer(Modifier.height(8.dp))
                OutlinedTextField(
                    value = listName,
                    onValueChange = {
                        listViewModel.changeListName(it)
                    },
                    label = { Text("List Name") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(Modifier.height(8.dp))
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        listViewModel.updateList()
                    }
                ) {
                    Text("Update List Name")
                }
            }
        }

        // Items section
        selectedList?.let { list ->
            val listItems = allItems.filter { it.categoryId == list.id }

            Card(
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = "Items in List",
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )

                    LazyColumn(
                        modifier = Modifier.weight(1f)
                    ) {
                        items(listItems) { item ->
                            ItemRow(
                                item = item,
                                isEditing = selectedItem?.id == item.id,
                                itemName = if (selectedItem?.id == item.id) itemName else item.name,
                                onEditClick = {
                                    listViewModel.setItemModelToChange(item)
                                },
                                onNameChange = { newName ->
                                    listViewModel.changeItemName(newName)
                                },
                                onUpdateClick = {
                                    listViewModel.updateItem()
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun ItemRow(
    item: ItemModel,
    isEditing: Boolean,
    itemName: String,
    onEditClick: () -> Unit,
    onNameChange: (String) -> Unit,
    onUpdateClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (isEditing) {
                    OutlinedTextField(
                        value = itemName,
                        onValueChange = onNameChange,
                        label = { Text("Item Name") },
                        modifier = Modifier.weight(1f)
                    )
                    Spacer(Modifier.width(8.dp))
                    Button(onClick = onUpdateClick) {
                        Text("Save")
                    }
                } else {
                    Text(
                        text = item.name,
                        modifier = Modifier.weight(1f)
                    )
                    IconButton(onClick = onEditClick) {
                        Icon(
                            imageVector = Icons.Default.Edit,
                            contentDescription = "Edit Item"
                        )
                    }
                }
            }
        }
    }
}