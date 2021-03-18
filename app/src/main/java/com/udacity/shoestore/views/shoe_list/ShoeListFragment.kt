package com.udacity.shoestore.views.shoe_list

import android.content.SharedPreferences
import android.os.Bundle
import android.view.*
import androidx.activity.addCallback
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeListBinding
import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.utility.NEW_USER
import com.udacity.shoestore.utility.PREF_NAME
import com.udacity.shoestore.utility.PRIVATE_PREF_MODE
import com.udacity.shoestore.viewmodels.ShoeListViewModel

class ShoeListFragment : Fragment() {

    private val viewModel: ShoeListViewModel by activityViewModels()
    private lateinit var preferences: SharedPreferences
    private var binding: FragmentShoeListBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_list, container, false)
        return binding?.apply {
            lifecycleOwner = viewLifecycleOwner
        }?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        preferences = requireContext().getSharedPreferences(PREF_NAME, PRIVATE_PREF_MODE)

        setClickListeners()

        startObserver()

        setHasOptionsMenu(true)
    }

    private fun setClickListeners() {
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            requireActivity().finish()
        }
        binding?.fragmentShoeListBtnShoeDetails?.setOnClickListener { goToShoeDetails() }
    }

    private fun startObserver() {
        viewModel.listOfShoes().observe(viewLifecycleOwner, { shoeList ->
            if (shoeList.isNotEmpty()) showShoes(shoeList)
        })
    }

    private fun showShoes(shoes: List<Shoe>) {
        context?.let { context ->
            val shoeHolder = binding?.fragmentShoeListHolder
            shoes.forEach { shoe ->
                val shoeListLayout = ShoeListLayout(context)
                shoeListLayout.loadShoe(shoe)
                shoeHolder?.addView(shoeListLayout)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_logout) logOutUser()
        return super.onOptionsItemSelected(item)
    }

    private fun goToShoeDetails() {
        Navigation.findNavController(requireView())
            .navigate(R.id.action_shoeListFragment_to_shoeDetailsFragment)
    }

    private fun logOutUser() {
        preferences.edit().putBoolean(NEW_USER, true).apply()
        findNavController().navigate(R.id.action_shoeListFragment_to_authFragment)
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}