package com.example.listroom.viewmodel

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.listroom.room.data.ItemDao
import com.example.listroom.room.data.ItemModel
import com.example.listroom.room.data.ListDao
import com.example.listroom.room.data.ListModel
import kotlinx.coroutines.flow.Flow

class Repository(
    private val itemDao: ItemDao,
    private val listDao: ListDao
) {
    suspend fun getAllList():Flow<List<ListModel>>{
        return listDao.getAllList()
    }
    suspend fun deleteList(listModel:ListModel){
        listDao.deleteList(listModel)
    }
    suspend fun updateList(listModel: ListModel){
        listDao.updateList(listModel)
    }
    suspend fun insertList(listModel: ListModel){
        listDao.insertList(listModel)
    }

    fun getAllItems(): Flow<List<ItemModel>>{
        return itemDao.getAllItems()
    }

    fun getItemsByCategory(categoryId: Int): Flow<List<ItemModel>>{
        return itemDao.getItemsByCategory(categoryId)
    }

    fun deleteItem(itemModel: ItemModel){
        itemDao.deleteItem(itemModel)
    }

    fun insertItem(itemModel: ItemModel){
        itemDao.insertItem(itemModel)
    }


    fun updateItem(itemModel: ItemModel){
        itemDao.updateItem(itemModel)
    }
}