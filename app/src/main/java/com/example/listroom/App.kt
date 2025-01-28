package com.example.listroom

import android.app.Application
import com.example.listroom.room.data.Graph

class App:Application(){
    override fun onCreate(){
        super.onCreate()
        Graph.build(this)
    }
}