package com.abdallah.sarrawi.mymsgs.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.abdallah.sarrawi.mymsgs.R

class BlankFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_blank, container, false)
        val tv: TextView? = rootView.findViewById(R.id.aa)
        tv?.setOnClickListener {
            val direction = BlankFragmentDirections.actionBlankFragmentToNewMsgsFragment()
            findNavController().navigate(direction)
        }
        return rootView
    }


}