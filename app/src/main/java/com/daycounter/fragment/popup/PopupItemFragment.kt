package com.daycounter.fragment.popup

import android.content.Context
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.widget.doOnTextChanged
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.daycounter.R
import com.daycounter.databinding.FragmentPopupItemBinding
import com.daycounter.dataclass.Reminder
import com.daycounter.dataclass.Reminders
import com.daycounter.other.enum.Constants
import com.daycounter.other.enum.Snackbar
import com.daycounter.other.enum.Strings
import com.daycounter.other.enum.TranslationType
import com.daycounter.service.data.SaveUserDataService
import com.daycounter.service.date.DateDifferenceService
import com.daycounter.service.validation.InputDateValidationService
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

class PopupItemFragment : Fragment() {

    private var _binding: FragmentPopupItemBinding? = null
    private val binding get() = _binding!!

    private val args: PopupItemFragmentArgs by navArgs()
    private val validateDate = InputDateValidationService()
    private var dataHandler = SaveUserDataService()

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?, savedInstanceState: Bundle?): View {

        _binding = FragmentPopupItemBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        createDateInputBindings()

        if (args.itemPos != -1)
            fillInData(args.itemPos)
    }

    private fun createDateInputBindings() {

        binding.cancelButton.setOnClickListener {
            findNavController().navigate(R.id.action_popup_to_reminders)
        }

        binding.saveButton.setOnClickListener {
            if (checkEmptyFields()) {
                saveReminder(args.itemPos)
                findNavController().navigate(R.id.action_popup_to_reminders)
            } else {
                Snackbar.displaySnackbar(Strings.FILL_IN_ALL_FIELDS, view!!)
            }
        }

        binding.deleteReminderButton.setOnClickListener {
            Reminders.remove(args.itemPos)
            dataHandler.saveUserData(context!!.getSharedPreferences(Constants.USER_PREFERENCES, Context.MODE_PRIVATE))
            findNavController().navigate(R.id.action_popup_to_reminders)
        }

        binding.inputDay.doOnTextChanged {
                text, _, _, _ ->
            if (!validateDate.validate(0, text.toString())) {
                binding.inputDay.error = Strings.INVALID_INPUT_DAY
                binding.saveButton.isEnabled = false
            } else {
                binding.saveButton.isEnabled = true
                if (text?.length == 2) binding.inputMonth.requestFocus()
            }
        }

        binding.inputMonth.doOnTextChanged {
                text, _, _, _ ->
            if (!validateDate.validate(1, text.toString())) {
                binding.inputMonth.error = Strings.INVALID_INPUT_MONTH
                binding.saveButton.isEnabled = false
            } else {
                binding.saveButton.isEnabled = true
                if (text?.length == 2) binding.inputYear.requestFocus()
            }
        }

        binding.inputYear.doOnTextChanged {
                text, _, _, _ ->
            if (!validateDate.validate(2, text.toString())) {
                binding.inputYear.error = Strings.INVALID_INPUT_YEAR
                binding.saveButton.isEnabled = false
            } else {
                binding.saveButton.isEnabled = true
            }
        }
    }

    private fun generateDate(): String? {
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
    }

    private fun saveReminder(itemPos: Int) {

        val reminder = if (itemPos == -1)
            Reminder(1, "", "", 3, Date())
        else
            Reminders.getList()[itemPos]

        try {
            reminder.title = binding.popupTitle.text.toString()
            reminder.description = binding.popupDescription.text.toString()
            reminder.thumbnail = binding.popupThumbnail.imageAlpha
            reminder.dueDate = Constants.SDF.parse(generateDate())
        } catch (e: Exception) {
            e.printStackTrace()
        }
        if (itemPos == -1)
            Reminders.add(reminder)

        val handler = SaveUserDataService()
        handler.saveUserData(this.context!!
            .getSharedPreferences(Constants.USER_PREFERENCES, Context.MODE_PRIVATE))
    }

    private fun fillInData(itemPos: Int) {

        val reminder = Reminders.getList()[itemPos]
        binding.popupTitle.setText(reminder.title)
        binding.inputDay.setText(String.format("%s", Constants.SDF.format(reminder.dueDate)).substring(0, 2))
        binding.inputMonth.setText(String.format("%s", Constants.SDF.format(reminder.dueDate)).substring(3, 5))
        binding.inputYear.setText(String.format("%s", Constants.SDF.format(reminder.dueDate)).substring(6, 10))

        binding.popupDescription.setText(reminder.description)
        binding.popupThumbnail.setImageDrawable(context!!.getDrawable(R.drawable.heart_shape))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}