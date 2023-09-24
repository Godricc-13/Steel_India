package com.example.steelindia

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ItemViewModel : ViewModel() {
    private val coroutineScope = CoroutineScope(Dispatchers.IO)
    val mutableitem : MutableLiveData<Item?> = MutableLiveData()
    val itemAdded : MutableLiveData<Boolean> = MutableLiveData(false)

    // TODO: Implement the ViewModel
   // add functions
    fun getitem(itemId: Long){
        coroutineScope.launch {
           val item = ItemDAO.getItem(itemId)
            mutableitem.postValue(item)
        }
    }
    fun updateitem(item: Item){
        coroutineScope.launch {
            ItemDAO.updateItem(item)
            itemAdded.postValue(true)
        }

    }
    fun deleteitem(itemId: Long){
        coroutineScope.launch {
            ItemDAO.deleteItem(itemId )
        }
    }
    fun additem(item: Item){
        coroutineScope.launch {
            ItemDAO.addItem(item)
           itemAdded.postValue(true)
        }
    }
}