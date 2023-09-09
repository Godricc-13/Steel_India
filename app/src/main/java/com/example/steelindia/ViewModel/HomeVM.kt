package com.example.steelindia.ViewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.steelindia.ItemDAO
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeVM : ViewModel() {
    private val coroutineScope = CoroutineScope(Dispatchers.IO)


    fun init(context:Context){

        ItemDAO.init(context)
    }
    fun deleteAllItems(){
        coroutineScope.launch {
            ItemDAO.deleteAllItems()
        }

    }
    fun getallitems(){
        coroutineScope.launch {
            ItemDAO.getAllItems()
        }
    }
    fun getitem(){
        coroutineScope.launch {
            ItemDAO.getItem()
        }
    }
    fun updateitem(){
        coroutineScope.launch {
            ItemDAO.updateItem()
        }

    }
    fun deleteitem(){
        coroutineScope.launch {
            ItemDAO.deleteItem()
        }
    }
    fun additem(){
        coroutineScope.launch {
            ItemDAO.addItem()
        }
    }
}