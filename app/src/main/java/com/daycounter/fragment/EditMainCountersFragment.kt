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
import com.daycounter.dataclass.Counter
import com.daycounter.databinding.FragmentEditCountersBinding
import com.daycounter.other.enum.Constants
import com.daycounter.other.enum.Snackbar.Factory.displaySnackbar
import com.daycounter.other.enum.Strings
import com.daycounter.service.data.SaveUserDataService
import com.daycounter.service.validation.InputDateValidationService
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

class EditMainCountersFragment : Fragment() {

    private var _binding: FragmentEditCountersBinding? = null
    private val binding get() = _binding!!
    private val validateDate = InputDateValidationService()

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?, savedInstanceState: Bundle?): View? {

        _binding = FragmentEditCountersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        createDateInputBindings()
    }

    private fun createDateInputBindings() {

        binding.submitButtonTestData.setOnClickListener {
            saveNewMainCounter(null, null, null)
            findNavController().navigate(R.id.action_editcounter_to_start)
        }

        binding.submitButton.setOnClickListener {
            if (checkEmptyFields()) {
                saveNewMainCounter(binding.inputPersonOne.text.toString(), binding.inputPersonTwo.text.toString(), generateDate())
                findNavController().navigate(R.id.action_editcounter_to_start)
            } else {
                displaySnackbar(Strings.FILL_IN_ALL_FIELDS, view!!)
            }
        }

        binding.inputDay.doOnTextChanged {
            text, _, _, _ ->
                if (!validateDate.validate(0, text.toString())) {
                    binding.inputDay.error = Strings.INVALID_INPUT_DAY
                    binding.submitButton.isEnabled = false
                } else {
                    binding.submitButton.isEnabled = true
                    if (text?.length == 2) binding.inputMonth.requestFocus()
                }
        }

        binding.inputMonth.doOnTextChanged {
            text, _, _, _ ->
                if (!validateDate.validate(1, text.toString())) {
                    binding.inputMonth.error = Strings.INVALID_INPUT_MONTH
                    binding.submitButton.isEnabled = false
                } else {
                    binding.submitButton.isEnabled = true
                    if (text?.length == 2) binding.inputYear.requestFocus()
                }
        }

        binding.inputYear.doOnTextChanged {
                text, _, _, _ ->
            if (!validateDate.validate(2, text.toString())) {
                binding.inputYear.error = Strings.INVALID_INPUT_YEAR
                binding.submitButton.isEnabled = false
            } else {
                binding.submitButton.isEnabled = true
            }
        }
    }

    private fun generateDate(): String {
        val finalDate = String.format("%s-%s-%s",
            binding.inputDay.text.toString(),
            binding.inputMonth.text.toString(),
            binding.inputYear.text.toString())

        return if (validateDate.validate(3, finalDate)) finalDate else ""
    }

    private fun checkEmptyFields(): Boolean {
        return binding.inputDay.text.toString().trim().isNotEmpty()
                && binding.inputMonth.text.toString().trim().isNotEmpty()
                && binding.inputYear.text.toString().trim().isNotEmpty()
                && binding.inputPersonOne.text.toString().trim().isNotEmpty()
                && binding.inputPersonTwo.text.toString().trim().isNotEmpty()
    }

    private fun saveNewMainCounter(personOne: String?, personTwo: String?, date: String?) {

        val handler = SaveUserDataService()

        Constants.MAIN_COUNTER = try {
            if (personOne != null)
                Counter(personOne, personTwo, Constants.SDF.parse(date!!))
            else //Test data
                Counter("Person One", "Person Two", Constants.SDF.parse("27-12-2019-00-00-00"))
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }

        handler.saveUserData(this.context!!
            .getSharedPreferences(Constants.USER_PREFERENCES, Context.MODE_PRIVATE))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
