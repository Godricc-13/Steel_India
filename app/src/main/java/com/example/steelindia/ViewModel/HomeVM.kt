package com.example.steelindia.ViewModel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.steelindia.DAO
import com.example.steelindia.Item
import com.example.steelindia.ItemDAO
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeVM : ViewModel() {
    private val coroutineScope = CoroutineScope(Dispatchers.IO)
    val itemList : MutableLiveData<List<Item>?> = MutableLiveData()

    fun init(context:Context ){
        ItemDAO.init(context)
    }

    fun deleteAllItems(){
        coroutineScope.launch {
            ItemDAO.deleteAllItems()
        }

    }
    fun getallitems(){
        coroutineScope.launch {
            val list = ItemDAO.getAllItems()
            itemList.postValue(list)

        }
    }
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
            ItemDAO.deleteItem(itemId )
        }
    }
    fun additem(item: Item){
        coroutineScope.launch {
            ItemDAO.addItem(item)
        }
    }
}