package com.example.steelindia

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

        val itemId = arguments?.getLong("itemId", -1)
        if(itemId == -1L || itemId == null){

        } else {
            binding.textViewAdd.text = "Update Item"
            binding.doneButton.text = "Update"
            viewModel.getitem(itemId)
        }
        viewModel.updateItem.observe(viewLifecycleOwner) {
            if (it != null) {
                binding.editTextItemId.setText(it.itemId.toString())

                if(it.itemType == "Heavy"){
                    binding.radioGroupItemType.check(R.id.radioButtonHeavy)
                } else {
                    binding.radioGroupItemType.check(R.id.radioButtonLight)
                }
            }
        }

        return binding.root

    }



}