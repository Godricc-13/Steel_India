package com.example.steelindia.ViewModel

import androidx.lifecycle.ViewModel
import com.example.steelindia.Item
import com.example.steelindia.ItemDAO
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ItemViewModel : ViewModel() {
    private val coroutineScope = CoroutineScope(Dispatchers.IO)
    // TODO: Implement the ViewModel
   // add functions
    fun getitem(itemId: Long){
        coroutineScope.launch {
            ItemDAO.getItem(itemId)
        }
    }
    fun updateitem(item: Item){
        coroutineScope.launch {
            ItemDAO.updateItem(item)
        }

    }
    fun deleteitem(itemId: Long){
        coroutineScope.launch {
            ItemDAO.deleteItem(itemId)
        }
    }
    fun additem(item: Item){
        coroutineScope.launch {
            ItemDAO.addItem(item)
        }
    }
}