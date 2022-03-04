package com.daycounter.fragment

import android.content.Context
import android.graphics.Color.red
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.daycounter.R
import com.daycounter.blueprint.Counter
import com.daycounter.databinding.FragmentStartBinding
import com.daycounter.other.Constants
import com.daycounter.other.ProgressGetter
import com.daycounter.other.TranslationType
import com.daycounter.service.calculation.GetDateDifferenceService
import com.daycounter.service.data.DataHandlingService

class StartFragment : Fragment() {

    private var _binding: FragmentStartBinding? = null

    private val binding get() = _binding!!

    private var mainCounter: Counter? = null

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?, savedInstanceState: Bundle?): View? {

        _binding = FragmentStartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getCounter()
        updateMainCounter()

        binding.navigationButtons.gotoStartButton.setTextColor(
            activity!!.getApplication().getResources().getColor(R.color.nav_btn_focused))

        binding.navigationButtons.gotoCountersButton.setOnClickListener {
            findNavController().navigate(R.id.action_StartFragment_to_CountersFragment)
        }

        binding.navigationButtons.gotoSettingsButton.setOnClickListener {
            findNavController().navigate(R.id.action_StartFragment_to_SettingsFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun getCounter() {
        val dataHandler = DataHandlingService()

        val fetchedData = dataHandler.loadData(
            this.activity!!.getSharedPreferences(
                Constants.MAIN_COUNTER,
                Context.MODE_PRIVATE))

        if (fetchedData != null)
            mainCounter = fetchedData
        else
            findNavController().navigate(R.id.action_StartFragment_to_FStartupFragment)
    }

    private fun updateMainCounter() {
        val dateDiff = GetDateDifferenceService()

        val days = dateDiff.getDateDifference(mainCounter?.startDate, TranslationType.DAYS)
        val progress = (100 / ProgressGetter.get(days)) * days

        binding.progressCircle.progress = progress.toInt()
        binding.progressText.text = String.format("%s Days", days)

        if (days > 100000)
            binding.progressText.textSize = 45F

        binding.personOne.text = mainCounter?.personOne
        binding.personTwo.text = mainCounter?.personTwo
    }
}
