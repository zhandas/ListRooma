package com.example.listroom.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.listroom.viewmodel.ListViewModel

@Composable
fun AddScreen(listViewModel: ListViewModel){
    val listName by listViewModel.listName.collectAsState()
    val itemName by listViewModel.itemName.collectAsState()
    val selectedList by listViewModel.listModel.collectAsState()

    Column(
        modifier = Modifier
            .wrapContentSize()
            .background(
                color = Color.LightGray,
                shape = RoundedCornerShape(3.dp)
            )
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Add Something")
        Spacer(Modifier.height(4.dp))
        OutlinedTextField(
            value = listName,
            onValueChange = {
                listViewModel.changeListName(it)
            },
            label = { Text("List Name") }
        )
        Spacer(Modifier.height(8.dp))
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                listViewModel.addList()
            }
        ) {
            Text("Add list")
        }
        Spacer(Modifier.height(16.dp))
        Divider()
        Spacer(Modifier.height(16.dp))

        OutlinedTextField(
            value = itemName,
            onValueChange = { listViewModel.changeItemName(it) },
            label = { Text("Item Name") }
        )
        Spacer(Modifier.height(8.dp))

        selectedList?.let { list->
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = { listViewModel.addItem(list.id) }
            ) {
                Text("Add item")
            }
        }
    }
}