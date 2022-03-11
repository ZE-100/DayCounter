package com.daycounter.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.daycounter.R
import com.daycounter.dataclass.Counter
import com.daycounter.databinding.FragmentStartBinding
import com.daycounter.other.enum.Constants
import com.daycounter.other.enum.ProgressGetter
import com.daycounter.other.enum.TranslationType
import com.daycounter.service.date.DateDifferenceService

class StartFragment : Fragment() {

    private var _binding: FragmentStartBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?, savedInstanceState: Bundle?): View? {

        _binding = FragmentStartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        updateMainCounter(getCounter())

        binding.navigationButtons.gotoStartButton.setTextColor(
            activity!!.getApplication().getResources().getColor(R.color.nav_btn_focused))

        binding.navigationButtons.gotoCountersButton.setOnClickListener {
            findNavController().navigate(R.id.action_start_to_reminders)
        }

        binding.navigationButtons.gotoSettingsButton.setOnClickListener {
            findNavController().navigate(R.id.action_start_to_settings)
        }

        binding.progressCircle.setOnClickListener {
            findNavController().navigate(R.id.action_start_to_editcounters)
        }
    }

    private fun getCounter(): Counter? {
        if (Constants.MAIN_COUNTER != null)
            return Constants.MAIN_COUNTER!!
        else
            findNavController().navigate(R.id.action_start_to_editcounters)
        return null
    }

    private fun updateMainCounter(mainCounter: Counter?) {
        val dateDiff = DateDifferenceService()

        val days = dateDiff.getDateDifference(mainCounter?.startDate, TranslationType.DAYS)
        val progress = (100 / ProgressGetter.get(days)) * days

        binding.progressCircle.progress = progress.toInt()
        binding.progressText.text = String.format("%s Days", days)

        if (days > 100000)
            binding.progressText.textSize = 45F

        binding.personOne.text = mainCounter?.personOne
        binding.personTwo.text = mainCounter?.personTwo
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
