package com.abdallah.sarrawi.mymsgs.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.abdallah.sarrawi.mymsgs.R
import com.abdallah.sarrawi.mymsgs.databinding.FragmentFirstBinding
import com.abdallah.sarrawi.mymsgs.databinding.FragmentSecondBinding
import com.abdallah.sarrawi.mymsgs.databinding.FragmentSettingsBinding


class SettingsFragment : Fragment() {

    private lateinit var _binding : FragmentSettingsBinding
    private val binding get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)

        return binding.root
    }


}