package com.udacity.shoestore.views.shoe_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeDetailsBinding
import com.udacity.shoestore.viewmodels.ShoeListViewModel

class ShoeDetailsFragment : Fragment() {

    private var binding: FragmentShoeDetailsBinding? = null
    private val viewModel: ShoeListViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_details, container, false)
        return binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            viewmodel = viewModel
        }?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setClickListeners()

        startObserver()
    }

    private fun setClickListeners() {
        binding?.fragmentShoeDetailsBtnCancel?.setOnClickListener { goToShoeListScreen() }
    }

    private fun startObserver() {
        viewModel.isItemAdded.observe(viewLifecycleOwner, { value ->
            if (value) {
                goToShoeListScreen()
            }
        })
    }

    private fun goToShoeListScreen() {
        findNavController().navigate(R.id.action_shoeDetailsFragment_to_shoeListFragment)
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}