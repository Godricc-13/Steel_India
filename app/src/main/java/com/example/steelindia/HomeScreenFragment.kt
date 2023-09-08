package com.example.steelindia

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.steelindia.databinding.FragmentHomeScreenBinding


class HomeScreen : Fragment() {

    private lateinit var binding: FragmentHomeScreenBinding
    private lateinit var MyPreferences: SharedPreferences
    private val ll = ArrayList<ArrayList<String>>()
    private lateinit var listView : RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentHomeScreenBinding.inflate(layoutInflater)
        MyPreferences = requireContext().getSharedPreferences("My_Prefs", Context.MODE_PRIVATE)

        listView = binding.listView

        ll.add(arrayListOf("Name 1", "455", "12"))
        ll.add(arrayListOf("Name 2", "753", "54"))
        ll.add(arrayListOf("Name 3", "235", "23"))
        ll.add(arrayListOf("Name 4", "562", "67"))

        val adapter = Adapter(ll)
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

}
