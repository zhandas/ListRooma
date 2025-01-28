package com.example.listroom.room.data

import android.content.Context
import androidx.room.Room
import com.example.listroom.viewmodel.Repository

object Graph {
    var database:ListDataBase?=null
    val repository:Repository by lazy {
        Repository(
            database!!.getItemDao(),
            database!!.getListDao()
        )
    }
    fun build(context: Context){
        database= Room.databaseBuilder(context,ListDataBase::class.java,"List.db").build()
    }
}