package com.daycounter.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.navigation.fragment.findNavController
import com.daycounter.R
import com.daycounter.blueprint.Counter
import com.daycounter.databinding.FragmentFirstStartupBinding
import com.daycounter.other.Constants
import com.daycounter.other.Snackbar.Factory.displaySnackbar
import com.daycounter.other.Strings
import com.daycounter.service.data.DataHandlingService
import com.daycounter.service.validate.InputDateValidationService
import com.google.android.material.snackbar.Snackbar
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

class FirstStartupFragment : Fragment() {

    private var _binding: FragmentFirstStartupBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?, savedInstanceState: Bundle?): View? {

        _binding = FragmentFirstStartupBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fun generateDate(): String? {
            return String.format("%s-%s-%s-00-00-00",
                binding.inputDay.text.toString(),
                binding.inputMonth.text.toString(),
                binding.inputYear.text.toString())
        }

        binding.submitButtonTestData.setOnClickListener {
            saveNewMainCounter(null, null, null)
            findNavController().navigate(R.id.action_FStartupFragment_to_StartFragment)
        }

        binding.submitButton.setOnClickListener {
            saveNewMainCounter(binding.inputPersonOne.text.toString(), binding.inputPersonTwo.text.toString(), generateDate())
            findNavController().navigate(R.id.action_FStartupFragment_to_StartFragment)
        }

        createDateInputBindings(view)
    }

    // TODO Make red glow and change snackbar to text
    private fun createDateInputBindings(view: View) {
        val validateDate = InputDateValidationService()

        binding.inputDay.doOnTextChanged {
                text, start, count, after ->

            if (!validateDate.validate(text.toString()))
                displaySnackbar(Strings.INVALID_INPUT, view)

            binding.submitButton.isEnabled = true
        }

        binding.inputMonth.doOnTextChanged {
                text, start, count, after ->

            if (!validateDate.validate(text.toString()))
                displaySnackbar(Strings.INVALID_INPUT, view)

            binding.submitButton.isEnabled = true
        }

        binding.inputYear.doOnTextChanged {
                text, start, count, after ->

            if (!validateDate.validate(text.toString()))
                displaySnackbar(Strings.INVALID_INPUT, view)

            binding.submitButton.isEnabled = true
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun saveNewMainCounter(personOne: String?, personTwo: String?, date: String?) {

        val sdf = SimpleDateFormat(Constants.DATE_FORMAT, Locale.GERMANY)
        val handler = DataHandlingService()

        var counter = Counter(null, null, null)

        try {
            counter = if (personOne != null) Counter(personOne, personTwo, sdf.parse(date))
                        else Counter("Person One", "Person Two", sdf.parse("27-12-2019-00-00-00")) //Test data
        } catch (e: Exception) {
            e.printStackTrace()
        }

        handler.saveData(this.context!!.getSharedPreferences(Constants.MAIN_COUNTER, Context.MODE_PRIVATE), counter)
    }
}
