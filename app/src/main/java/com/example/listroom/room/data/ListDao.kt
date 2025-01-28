package com.example.listroom.room.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface ListDao{
    @Query("SELECT * FROM Category")
    fun getAllList():Flow<List<ListModel>>

    @Delete
    fun deleteList(listModel: ListModel)

    @Insert
    fun insertList(listModel: ListModel)

    @Update
    fun updateList(listModel: ListModel)
}
@Dao
interface ItemDao {
    @Query("SELECT * FROM Item")
    fun getAllItems(): Flow<List<ItemModel>>

    @Query("SELECT * FROM Item WHERE categoryId = :categoryId")
    fun getItemsByCategory(categoryId: Int): Flow<List<ItemModel>>

    @Delete
    fun deleteItem(itemModel: ItemModel)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertItem(itemModel: ItemModel)

    @Update
    fun updateItem(itemModel: ItemModel)
}