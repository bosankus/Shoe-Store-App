package com.udacity.shoestore.views.login

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentAuthBinding
import com.udacity.shoestore.utility.NEW_USER
import com.udacity.shoestore.utility.PREF_NAME
import com.udacity.shoestore.utility.PRIVATE_PREF_MODE

class AuthFragment : Fragment() {

    private lateinit var preferences: SharedPreferences
    private var binding: FragmentAuthBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_auth, container, false)
        return binding?.apply { lifecycleOwner = viewLifecycleOwner }?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        preferences = requireContext().getSharedPreferences(PREF_NAME, PRIVATE_PREF_MODE)

        checkIfNewUser()

        setClickListeners()

    }

    private fun checkIfNewUser() {
        val isFirstTime = preferences.getBoolean(NEW_USER, true)
        if (!isFirstTime) findNavController().navigate(R.id.action_authFragment_to_shoeListFragment)
        else return
    }

    private fun setClickListeners() {
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            requireActivity().finish()
        }

        binding?.apply {
            fragmentAuthBtnLogin.setOnClickListener { goToWelcomeScreen() }
            fragmentAuthBtnSignup.setOnClickListener { goToWelcomeScreen() }
        }
    }

    private fun goToWelcomeScreen() {
        findNavController().navigate(R.id.action_authFragment_to_welcomeFragment)
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}