package com.daycounter

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.daycounter.databinding.FragmentStartBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class StartFragment : Fragment() {

    private var _binding: FragmentStartBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentStartBinding.inflate(inflater, container, false)
        return binding.root

    }

    @SuppressLint("NewApi")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.navigationButtons.gotoCountersButton.setOnClickListener {
            findNavController().navigate(R.id.action_StartFragment_to_CountersFragment)
        }

        binding.navigationButtons.gotoSettingsButton.setOnClickListener {
            findNavController().navigate(R.id.action_StartFragment_to_SettingsFragment)
        }

        binding.small.setOnClickListener {
            binding.progressCircle.setProgress(10, true)
        }

        binding.big.setOnClickListener {
            binding.progressCircle.setProgress(90, true)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
