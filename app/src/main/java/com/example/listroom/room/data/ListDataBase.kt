package com.example.listroom.room.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [ListModel::class, ItemModel::class],
    version = 1,
    exportSchema = false
)

abstract class ListDataBase:RoomDatabase() {
    abstract fun getListDao():ListDao
    abstract fun getItemDao(): ItemDao
}