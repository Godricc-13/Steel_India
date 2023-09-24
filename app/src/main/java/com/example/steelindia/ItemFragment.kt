package com.example.steelindia

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.steelindia.ViewModel.ItemViewModel
import com.example.steelindia.databinding.FragmentItemBinding

class ItemFragment : Fragment() {



    private lateinit var viewModel: ItemViewModel
    private lateinit var binding: FragmentItemBinding



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(ItemViewModel::class.java)
        binding=FragmentItemBinding.inflate(layoutInflater)

        return binding.root

    }



}