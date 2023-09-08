package com.example.steelindia.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.steelindia.DAO
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegisterVM : ViewModel() {

    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    val error : MutableLiveData<String> = MutableLiveData<String>()

    fun registerUser(firstname: String, lastname: String,Username:String ,email: String, password: String) {
        coroutineScope.launch {

            if(DAO.userExists(email)) {
                //Error
                error.postValue("Email already exists")
            } else {
                DAO.registerUser(firstname, lastname, email, Username ,password)
            }

        }
    }

}