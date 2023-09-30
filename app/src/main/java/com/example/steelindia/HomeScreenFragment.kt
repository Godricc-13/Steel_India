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
import com.google.android.material.floatingactionbutton.FloatingActionButton


class HomeScreen : Fragment() {

    private lateinit var binding: FragmentHomeScreenBinding
    private lateinit var MyPreferences: SharedPreferences
    private var itemList = ArrayList<Item>()
    private lateinit var listView : RecyclerView
    private lateinit var viewModel: HomeVM
    private lateinit var addButton : FloatingActionButton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentHomeScreenBinding.inflate(layoutInflater)
        MyPreferences = requireContext().getSharedPreferences("My_Prefs", Context.MODE_PRIVATE)
        viewModel=ViewModelProvider(this)[HomeVM::class.java]
        val userId = arguments?.getLong("userId")
        viewModel.init(requireContext())
        listView = binding.listView

        addButton = binding.addfloatingActionButton


        val adapter = ItemAdapter(itemList, createItemClickListener())
        listView.layoutManager = LinearLayoutManager(requireContext())
        listView.adapter = adapter
        getallitems()


        viewModel.itemList.observe(viewLifecycleOwner){
            if(it != null){
                adapter.list= it as ArrayList<Item>
                adapter.notifyDataSetChanged()
            }
        }

        

        binding.btnLogout.setOnClickListener {
            val editor = MyPreferences.edit()
            editor.putLong("loginId", -1L)
            editor.apply()

            val action = HomeScreenDirections.actionHomeScreenToMainFragment()
            findNavController().navigate(action)
        }

        addButton.setOnClickListener {
            val action = HomeScreenDirections.actionHomeScreenToItemFragment()
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
    fun getitem(itemId: Long){
        viewModel.getitem(itemId)
    }
    fun upadateitem(item: Item){
        viewModel.updateitem(item)
    }
    fun deleteitem(itemId: Long){
        viewModel.deleteitem(itemId)
    }
    fun additem(item: Item){
        viewModel.additem(item)
    }

    private fun createItemClickListener() : ItemClickListener {
        return object : ItemClickListener {
            override fun onItemClick(itemId: Long) {
                val action = HomeScreenDirections.actionHomeScreenToItemFragment(itemId)
                findNavController().navigate(action)
            }
        }
    }
}