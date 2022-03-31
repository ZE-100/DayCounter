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
import com.daycounter.other.enum.Strings
import com.google.android.material.snackbar.Snackbar
import java.lang.Exception
import java.util.*

class StartFragment : Fragment() {

    private var _binding: FragmentStartBinding? = null
    private val binding get() = _binding!!

    private var easterEggCount: Int = 0

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?, savedInstanceState: Bundle?): View? {

        _binding = FragmentStartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        generateNavBindings()
        generateDefaultBindings()


        val counter = getCounter()
        if (checkForNullValues(counter))
            findNavController().navigate(R.id.action_start_to_editcounters)
        else updateMainCounter(counter)
    }

    private fun generateNavBindings() {
        binding.navigationButtons.gotoCountersButton.setOnClickListener {
            findNavController().navigate(R.id.action_start_to_reminders)
        }

        binding.navigationButtons.gotoSettingsButton.setOnClickListener {
            findNavController().navigate(R.id.action_start_to_settings)
        }
    }

    private fun generateDefaultBindings() {
        binding.progressCircle.setOnClickListener {
            findNavController().navigate(R.id.action_start_to_editcounters)
        }

        binding.soButton.setOnClickListener {
            easterEggCount++

            if (binding.personTwo.text.equals("Marvin") && easterEggCount == 5)
                Snackbar.make(view!!, Strings.EASTER_EGG, Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
    }

    private fun getCounter(): Counter {
        return try {
            Constants.MAIN_COUNTER!!
        } catch (e: Exception) {
            e.printStackTrace()
            Counter("null", "null", null, -1L)
        }
    }

    private fun updateMainCounter(mainCounter: Counter) {
        val days = mainCounter.dateDiff
        val progress = (100 / ProgressGetter.get(days!!)) * days

        binding.progressCircle.progress = progress.toInt()
        binding.progressText.text = String.format("%s Days", days)

        if (days > 100000)
            binding.progressText.textSize = 45F

        binding.personOne.text = mainCounter.personOne
        binding.personTwo.text = mainCounter.personTwo

        context!!.stopService(Constants.BACKGROUND_INTENT)
        context!!.startService(Constants.BACKGROUND_INTENT)
    }

    private fun checkForNullValues(mainCounter: Counter): Boolean {
        return mainCounter.personOne.equals("null")
                && mainCounter.personTwo.equals("null")
                && mainCounter.dateDiff == -1L
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
