package com.example.listroom.screen

import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewmodel.MutableCreationExtras
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.Navigation
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.dialog
import androidx.room.Update
import com.example.listroom.room.data.Graph.repository
import com.example.listroom.viewmodel.ListViewModel

@Composable
fun NavigationGraph(
    modifier: Modifier,
    activity:ComponentActivity,
    navController: NavController
){
    val listViewModel = ViewModelProvider.create(
        activity as ViewModelStoreOwner,
        factory = ListViewModel.factory,
        extras = MutableCreationExtras().apply{
            set(ListViewModel.MY_REPOSITORY, repository)
        }
    )[ListViewModel::class.java]
    NavHost(
        modifier = modifier,
        navController = navController as NavHostController,
        startDestination = Screen.mainScreen
    ){
        composable(route = Screen.mainScreen){
            MainScreen(listViewModel, navController)
        }
        dialog(route = Screen.addScreen){
            AddScreen(listViewModel)
        }
        dialog(route = Screen.updateScreen){
            UpdateScreen(listViewModel)
        }
    }
}

object Screen{
    val mainScreen="MainScreen"
    val addScreen="AddScreen"
    val updateScreen="UpdateScreen"
}