package com.example.steelindia.ViewModel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.steelindia.DAO
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginVm : ViewModel() {
    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    val loginId : MutableLiveData<Long> = MutableLiveData<Long>()

    val error : MutableLiveData<String> = MutableLiveData<String>()


    fun init(context : Context){
        DAO.init(context)
    }
    fun login(username : String, password : String){
        coroutineScope.launch {
            val userId = DAO.login(username, password)
            if(userId != null){
                loginId.postValue(userId!!)
            } else {
                error.postValue("Invalid username or password")
            }
        }
    }

    fun onDestroy(){
        DAO.closeDb()
    }

}
