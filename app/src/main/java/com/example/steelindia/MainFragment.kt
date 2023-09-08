package com.example.steelindia


import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.steelindia.ViewModel.LoginVm
import com.example.steelindia.databinding.ActivityMainBinding


class MainFragment : Fragment(){

    private lateinit var binding : ActivityMainBinding

    private val viewModel : LoginVm by activityViewModels()

    private lateinit var Mypreference : SharedPreferences

     override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = ActivityMainBinding.inflate(layoutInflater)

         viewModel.init(requireContext())

        val usernameEditText : EditText = binding.editTextUsername
        val passwordEditText : EditText = binding.editTextPassword
        val loginButton : Button = binding.button
        val registerButton : Button = binding.button2

         Mypreference = requireContext().getSharedPreferences("My_Prefs", Context.MODE_PRIVATE)
         checkIfAlreadyLoggedIn()

         viewModel.error.observe(viewLifecycleOwner){
             Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
         }

         viewModel.loginId.observe(viewLifecycleOwner){
             Toast.makeText(requireContext(), "Login successfully", Toast.LENGTH_LONG).show()
         //TODO go to home screen
             val editor = Mypreference.edit()
             editor.putLong("loginId", it)
             editor.apply()

             val action = MainFragmentDirections.actionMainFragmentToHomeScreen()
             requireView().findNavController().navigate(action)

         }


        loginButton.setOnClickListener {
            val username = usernameEditText.text.toString()

            if(username.isEmpty()){
                Toast.makeText(requireContext(), "Username is empty", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            val password = passwordEditText.text.toString()

            if(password.isEmpty()){
                Toast.makeText(requireContext(), "Password is empty", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            viewModel.login(username, password)


        }

        registerButton.setOnClickListener{
            val action = MainFragmentDirections.actionMainFragmentToRegisterFragment()
            requireView().findNavController().navigate(action)
        }

        return binding.root
    }

    private fun checkIfAlreadyLoggedIn(){
        val loginId = Mypreference.getLong("loginId", -1)

        if(loginId != -1L){
            val action = MainFragmentDirections.actionMainFragmentToHomeScreen()
            findNavController().navigate(action)
        }
    }
    override fun onDestroy(){
        super.onDestroy()
        viewModel.onDestroy()
    }
}




