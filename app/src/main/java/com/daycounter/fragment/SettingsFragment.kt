package com.daycounter.fragment

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.daycounter.R
import com.daycounter.MainActivity
import com.daycounter.databinding.FragmentSettingsBinding
import com.daycounter.other.Constants
import com.daycounter.service.data.DataHandlingService

class SettingsFragment : Fragment() {

    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!

    private val handler = DataHandlingService()

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?, savedInstanceState: Bundle?): View? {

        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("UseCompatTextViewDrawableApis")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        handler.loadData(activity!!
            .getSharedPreferences(Constants.USER_PREFERENCES, Context.MODE_PRIVATE))

        navBtnBinding()
        settingBtnBinding()
    }

    //TODO Do once, include this idk
    private fun navBtnBinding() {
        //TODO: Change icon colour
        binding.navigationButtons.gotoStartButton.setOnClickListener {
            findNavController().navigate(R.id.action_settings_to_start)
        }

        binding.navigationButtons.gotoCountersButton.setOnClickListener {
            findNavController().navigate(R.id.action_settings_to_reminders)
        }
    }

    private fun settingBtnBinding() {

        binding.notificationSwitch.isChecked = Constants.ENABLE_NOTIFICATIONS
        binding.notificationSwitch.setOnCheckedChangeListener {
            _, isChecked ->
            if (isChecked) {
                Constants.ENABLE_NOTIFICATIONS = isChecked
                Constants.ENABLE_BACKGROUND_SERVICES = isChecked
                binding.runInBackgroundSwitch.isChecked = isChecked
                sendTestNotification()
            } else {
                Constants.ENABLE_NOTIFICATIONS = isChecked
            }
            savePreferences()
        }

        binding.runInBackgroundSwitch.isChecked = Constants.ENABLE_BACKGROUND_SERVICES
        binding.runInBackgroundSwitch.setOnCheckedChangeListener {
            _, isChecked ->
            if (isChecked) {
                Constants.ENABLE_BACKGROUND_SERVICES = isChecked
            } else {
                Constants.ENABLE_BACKGROUND_SERVICES = isChecked
                Constants.ENABLE_NOTIFICATIONS = isChecked
                binding.notificationSwitch.isChecked = isChecked
            }
            savePreferences()
        }
        //TODO Implement useless feature
        binding.darkModeSwitch.isChecked = Constants.ENABLE_DARKMODE
        binding.darkModeSwitch.setOnCheckedChangeListener {
            _, isChecked -> Constants.ENABLE_DARKMODE = isChecked
            savePreferences()
        }
    }

    private fun savePreferences() {
        handler.saveData(this.activity!!.getSharedPreferences(Constants.USER_PREFERENCES, Context.MODE_PRIVATE))

    }
    private fun sendTestNotification() {
        if (Constants.ENABLE_NOTIFICATIONS)
            (activity as MainActivity).createNewNotification("You did it!",
                "You just enabled anniversary notifications")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
