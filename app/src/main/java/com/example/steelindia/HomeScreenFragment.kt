package com.example.steelindia

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.steelindia.ViewModel.HomeVM
import com.example.steelindia.databinding.FragmentHomeScreenBinding


class HomeScreen : Fragment() {

    private lateinit var binding: FragmentHomeScreenBinding
    private lateinit var MyPreferences: SharedPreferences
    private var itemList = ArrayList<Item>()
    private lateinit var listView : RecyclerView
    private lateinit var viewModel: HomeVM

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentHomeScreenBinding.inflate(layoutInflater)
        MyPreferences = requireContext().getSharedPreferences("My_Prefs", Context.MODE_PRIVATE)
        viewModel=ViewModelProvider(this)[HomeVM::class.java]
        viewModel.init(requireContext())
        listView = binding.listView


        val adapter = ItemAdapter(itemList)
        listView.layoutManager = LinearLayoutManager(requireContext())
        listView.adapter = adapter



        binding.btnLogout.setOnClickListener {
            val editor = MyPreferences.edit()
            editor.putLong("loginId", -1)
            editor.apply()

            val action = HomeScreenDirections.actionHomeScreenToMainFragment()
            findNavController().navigate(action)
        }

        return binding.root

    }

    fun deleteallitems(){

        viewModel.deleteAllItems()

    }
    fun getallitems(){
        viewModel.getallitems()
    }
    fun getitem(){
        viewModel.getitem()
    }
    fun upadateitem(){
        viewModel.updateitem()
    }
    fun deleteitem(){
        viewModel.deleteitem()
    }
    fun additem(){
        viewModel.additem()
    }
}
