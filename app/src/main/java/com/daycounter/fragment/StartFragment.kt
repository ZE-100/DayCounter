package com.daycounter.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.daycounter.R
import com.daycounter.databinding.FragmentStartBinding
import com.daycounter.other.TranslationType
import com.daycounter.service.calculation.GetDateDifferenceService
import com.daycounter.service.data.DataHandlingService

class StartFragment : Fragment() {

    private var _binding: FragmentStartBinding? = null

    private val binding get() = _binding!!

    private val dataHandler = DataHandlingService()
    private val dateDiff = GetDateDifferenceService()

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        _binding = FragmentStartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.navigationButtons.gotoCountersButton.setOnClickListener {
            findNavController().navigate(R.id.action_StartFragment_to_CountersFragment)
        }

        binding.navigationButtons.gotoSettingsButton.setOnClickListener {
            findNavController().navigate(R.id.action_StartFragment_to_SettingsFragment)
        }

        val days = dateDiff.getDateDifference(5, TranslationType.DAYS)
        val progress = (100 / getProgress(days)) * days


        binding.progressCircle.progress = progress.toInt()
        binding.progressText.text = String.format("%s Days", days)

        if (days > 100000)
            binding.progressText.textSize = 45F
    }

    /**
     * Ugly bullshit
     */
    private fun getProgress(days: Long): Double {
        if (days <= 100) return 100.0
        if (days <= 365) return 365.0
        if (days <= 420) return 420.0
        if (days <= 1000) return 1000.0
        if (days <= 3650) return 3650.0
        else return -1.0
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
