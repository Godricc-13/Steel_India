package com.example.steelindia

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.steelindia.databinding.RegisterBinding
import androidx.navigation.findNavController
import com.example.steelindia.ViewModel.RegisterVM


class RegisterFragment : Fragment() {

        private lateinit var binding :  RegisterBinding

        private lateinit var viewModel : RegisterVM



        override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View {
            binding = RegisterBinding.inflate(layoutInflater)

            viewModel = ViewModelProvider(this)[RegisterVM::class.java]



            val firstnameEditText : EditText = binding.editTextfirst
            val lastnameEditText : EditText = binding.editTextlast
            val usernameEditText : EditText = binding.editTextUsername
            val emailEditText : EditText = binding.editTextemail
            val passwordEditText : EditText = binding.editTextpassword
            val repasswordEditText : EditText = binding.editTextrepassword
            val loginButton : Button = binding.button
           val error : TextView = binding.error

            viewModel.error.observe(viewLifecycleOwner){

                makeVisible(error, it)
            }


            makeInvisible(error)
            loginButton.setOnClickListener {
                val firstname = firstnameEditText.text.toString()
                if(firstname.isEmpty()){

                    makeVisible (error, "firstname  is empty")
                    return@setOnClickListener

                }
                val lastname = lastnameEditText.text.toString()
                if(lastname.isEmpty()){
                    makeVisible(error,"lastname  is empty")
                    return@setOnClickListener
                }
                val username = usernameEditText.text.toString()
                if(username.isEmpty()){
                    makeVisible(error,"Email is empty")
                    return@setOnClickListener
                }

                val email = emailEditText.text.toString()
                if(email.isEmpty()){
                    makeVisible(error,"Email is empty")
                    return@setOnClickListener
                }

                val password = passwordEditText.text.toString()
                if(password.isEmpty()){
                    makeVisible(error,"Password is empty")
                    return@setOnClickListener
                }
                val repassword = repasswordEditText.text.toString()
                if(repassword.isEmpty()){
                    makeVisible(error,"re-Password is empty")
                    return@setOnClickListener
                }
                if(!password.equals(repassword)){
                    makeVisible(error, "both are not equal")
                    return@setOnClickListener
                }

                viewModel.registerUser(firstname, lastname, username, email,password)

                requireView().findNavController().popBackStack()
            }

            return binding.root
        }

        fun makeInvisible(error : TextView){
            error.visibility= View.INVISIBLE
        }

        fun makeVisible(error: TextView, message: String){
            error.visibility= View.VISIBLE
            error.text=message
        }

    }
