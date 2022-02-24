package com.daycounter.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.daycounter.R
import com.daycounter.databinding.FragmentCountersBinding

class CountersFragment : Fragment() {

    private var _binding: FragmentCountersBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        _binding = FragmentCountersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.navigationButtons.gotoStartButton.setOnClickListener {
            findNavController().navigate(R.id.action_CountersFragment_to_StartFragment)
        }

        binding.navigationButtons.gotoSettingsButton.setOnClickListener {
            findNavController().navigate(R.id.action_CountersFragment_to_SettingsFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
