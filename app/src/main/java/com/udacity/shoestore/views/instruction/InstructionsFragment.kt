package com.udacity.shoestore.views.instruction

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentInstructionBinding
import com.udacity.shoestore.utility.NEW_USER
import com.udacity.shoestore.utility.PREF_NAME
import com.udacity.shoestore.utility.PRIVATE_PREF_MODE

class InstructionsFragment : Fragment() {

    private lateinit var preferences: SharedPreferences
    private var binding: FragmentInstructionBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_instruction, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        preferences = requireContext().getSharedPreferences(PREF_NAME, PRIVATE_PREF_MODE)

        setClickListeners()
    }

    private fun setClickListeners() {
        binding?.fragmentInstructionsBtnNext?.setOnClickListener { goToShoeList() }
    }

    private fun goToShoeList() {
        preferences.edit().putBoolean(NEW_USER, false).apply()
        findNavController().navigate(R.id.action_instructionsFragment_to_shoeListFragment)
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}