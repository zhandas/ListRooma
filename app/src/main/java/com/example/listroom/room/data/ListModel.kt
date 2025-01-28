package com.example.listroom.room.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.util.Locale.Category


@Entity(tableName = "Category")
data class ListModel(
    @PrimaryKey(autoGenerate = true)
    val id:Int=0,
    @ColumnInfo
    val listName:String

)
@Entity(
    tableName = "Item"
)
data class ItemModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "categoryId")
    val categoryId: Int
)


