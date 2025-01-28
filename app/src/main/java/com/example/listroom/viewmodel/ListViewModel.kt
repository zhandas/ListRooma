package com.example.listroom.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.listroom.room.data.Graph
import com.example.listroom.room.data.ItemModel
import com.example.listroom.room.data.ListModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ListViewModel(
    private val repository: Repository
):ViewModel() {
    private val _listName = MutableStateFlow("")
    val listName = _listName.asStateFlow()
    private val _listModel = MutableStateFlow<ListModel?>(null)
    val listModel = _listModel.asStateFlow()
    private val _listOfList = MutableStateFlow(listOf<ListModel>())
    val listOfList = _listOfList.asStateFlow()

    private val _itemName = MutableStateFlow("")
    val itemName = _itemName.asStateFlow()
    private val _itemModel = MutableStateFlow<ItemModel?>(null)
    val itemModel = _itemModel.asStateFlow()
    private val _listOfItems = MutableStateFlow(listOf<ItemModel>())
    val listOfItems = _listOfItems.asStateFlow()

    ///////Item
    fun deleteItem(itemModel: ItemModel) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteItem(itemModel)
        }
    }
    fun addItem(categoryId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val item = ItemModel(
                name = itemName.value,
                categoryId = categoryId
            )
            repository.insertItem(item)
        }
    }
    fun setItemModelToChange(itemModel: ItemModel) {
        _itemModel.value = itemModel
        _itemName.value = itemModel.name
    }
    fun changeItemName(name: String) {
        _itemName.value = name
    }
    fun updateItem() {
        viewModelScope.launch(Dispatchers.IO) {
            val itemToEdit = _itemModel.value!!.copy(name = _itemName.value)
            repository.updateItem(itemToEdit)
        }
    }
    fun getAllItems() {
        viewModelScope.launch(Dispatchers.IO) {
            val items = repository.getAllItems()
            items.collect {
                _listOfItems.value = it
            }
        }
    }

            //CATEGORY
            fun deletelist(listModel: ListModel) {
                viewModelScope.launch(Dispatchers.IO) {
                    repository.deleteList(listModel)
                }
            }

            fun changeListName(name: String) {
                _listName.value = name
            }

            fun setListModelToChange(listModel: ListModel) {
                _listModel.value = listModel
                _listName.value = listModel.listName
            }

            fun addList() {
                viewModelScope.launch(Dispatchers.IO) {
                    val list = ListModel(
                        listName = listName.value
                    )
                    repository.insertList(list)
                }
            }

            fun updateList() {
                viewModelScope.launch(Dispatchers.IO) {
                    val listToEdit = _listModel.value!!.copy(listName = _listName.value)
                    repository.updateList(listToEdit)
                }
            }

            fun getAllList() {
                viewModelScope.launch(Dispatchers.IO) {
                    val list = repository.getAllList()
                    list.collect {
                        _listOfList.value = it
                    }
                }
            }
    companion object {
        val MY_REPOSITORY = object : CreationExtras.Key<Repository> {}

        val factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val repository = this[MY_REPOSITORY] as Repository
                ListViewModel(repository)
            }
        }
    }
    }

