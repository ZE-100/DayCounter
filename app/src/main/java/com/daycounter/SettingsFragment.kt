package com.daycounter

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.get
import androidx.navigation.fragment.findNavController
import com.daycounter.databinding.FragmentSettingsBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SettingsFragment : Fragment() {

    private var _binding: FragmentSettingsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.navigationButtons.gotoStartButton.setOnClickListener {
            findNavController().navigate(R.id.action_SettingsFragment_to_StartFragment)
        }

        binding.navigationButtons.gotoCountersButton.setOnClickListener {
            findNavController().navigate(R.id.action_SettingsFragment_to_CountersFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}