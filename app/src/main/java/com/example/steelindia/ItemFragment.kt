package com.example.steelindia

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
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
        viewModel.mutableitem.observe(viewLifecycleOwner) {
            if (it != null) {
                binding.editTextItemId.setText(it.itemId.toString())
                binding.editTextItemName.setText(it.itemName)
                binding.editTextItemDesign.setText(it.itemDesign)
                binding.editTextItemPrice.setText(it.itemPrice.toString())
                binding.editTextItemQuantity.setText(it.itemQuantity.toString())
                if(it.itemType == "Heavy"){
                    binding.radioGroupItemType.check(R.id.radioButtonHeavy)
                } else {
                    binding.radioGroupItemType.check(R.id.radioButtonLight)
                }
                if(it.itemPolish == "Fancy"){
                    binding.radioGroupItemPolish.check(R.id.radioButtonFancy)
                } else {
                    binding.radioGroupItemPolish.check(R.id.radioButtonLocal)
                }


            }
        }
        binding.doneButton.setOnClickListener {
            val itemIdet = binding.editTextItemId.text.toString().toLong()
            if(itemIdet.toInt() == 0 or itemIdet.toInt() == null){

                showToast ( "Item ID   is empty")
                return@setOnClickListener

            }
            val itemName =  binding.editTextItemName.text.toString()
            if(itemName.isEmpty()){

                showToast ( "Item Name  is empty")
                return@setOnClickListener

            }
            val itemDesign = binding.editTextItemDesign.text.toString()
            if(itemDesign.isEmpty()){

                showToast ( "Item Design  is empty")
                return@setOnClickListener

            }
            val itemPrice = binding.editTextItemPrice.text.toString().toDouble()
            if(itemPrice.toInt() == 0 or itemPrice.toInt() == null){

                showToast ( "Item Price  is empty")
                return@setOnClickListener

            }
            val itemQuantity = binding.editTextItemQuantity.text.toString().toInt()
            if(itemQuantity == 0 or itemQuantity == null ){

                showToast( "Item Quantity  is empty")
                return@setOnClickListener

            }
            var itemType = ""

            if(binding.radioButtonHeavy.isChecked){
                itemType = "Heavy"
            } else if(binding.radioButtonLight.isChecked) {
                itemType = "Light"
            }
            var itemPolish = ""
            if(binding.radioButtonFancy.isChecked){
                itemPolish="Fancy"
            } else if (binding.radioButtonLocal.isChecked){
                itemPolish = "Local"
            }
            if(itemType.isEmpty()){

                showToast ( "Item Type  is empty")
                return@setOnClickListener

            }



            if(itemId == -1L || itemId == null){
                val itemToAdd = Item(itemIdet, itemName , itemType, itemPrice, itemQuantity, itemDesign, itemPolish)
                viewModel.additem(itemToAdd)

            } else {
                val itemToUpade = Item(itemIdet, itemName , itemType, itemPrice, itemQuantity, itemDesign, itemPolish)
                viewModel.updateitem(itemToUpade)

            }


        }
        viewModel.itemAdded.observe(viewLifecycleOwner) {
            if(it == true){
                this.findNavController().navigateUp()
            }
        }

        return binding.root

    }


    fun showToast(message:String){
        Toast.makeText(requireContext(), message , Toast.LENGTH_SHORT).show()
    }



}

