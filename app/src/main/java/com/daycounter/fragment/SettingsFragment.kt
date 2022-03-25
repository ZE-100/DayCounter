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
import com.daycounter.databinding.FragmentSettingsBinding
import com.daycounter.other.enum.Constants
import com.daycounter.service.data.SaveUserDataService
import com.daycounter.service.notification.CreateNotificationService
import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.annotation.RequiresApi
import com.daycounter.other.enum.Strings


class SettingsFragment : Fragment() {

    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!

    private val dataHandler = SaveUserDataService()
    private val notification = CreateNotificationService()

    private var gayestNumberCounter = 0

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?, savedInstanceState: Bundle?): View? {

        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("UseCompatTextViewDrawableApis")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        generateNavBindings()
        generateSettingBindings()
    }

    private fun generateNavBindings() {
        binding.navigationButtons.gotoStartButton.setOnClickListener {
            findNavController().navigate(R.id.action_settings_to_start)
        }

        binding.navigationButtons.gotoCountersButton.setOnClickListener {
            findNavController().navigate(R.id.action_settings_to_reminders)
        }
    }

    private fun generateSettingBindings() {

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
                context!!.startService(Constants.BACKGROUND_INTENT)
            } else {
                Constants.ENABLE_BACKGROUND_SERVICES = isChecked
                Constants.ENABLE_NOTIFICATIONS = isChecked
                binding.notificationSwitch.isChecked = isChecked
                context!!.stopService(Constants.BACKGROUND_INTENT)
            }
            savePreferences()
        }

        binding.darkModeSwitch.isChecked = Constants.ENABLE_DARKMODE
        binding.darkModeSwitch.setOnCheckedChangeListener {
            _, isChecked ->
            Constants.ENABLE_DARKMODE = isChecked
            savePreferences()
        }

        binding.supportButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            val data: Uri = Uri.parse(
                "mailto:marvin131t@gmail.com?subject=" + Uri.encode(Strings.EMAIL_SUBJECT_SUPPORT)
                    .toString() + "&body=" + Uri.encode(Strings.EMAIL_BODY_SUPPORT)
            )
            intent.data = data
            startActivity(intent)
        }

        binding.rateAppButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            val data: Uri = Uri.parse(
                "mailto:marvin131t@gmail.com?subject=" + Uri.encode(Strings.EMAIL_SUBJECT_RATE)
                    .toString() + "&body=" + Uri.encode(Strings.EMAIL_BODY_RATE)
            )
            intent.data = data
            startActivity(intent)
        }

        // https://archiveofourown.org/tags/Fifty%20seven%20is%20the%20gayest%20number
        // "That's the because"
        binding.backupButton.setOnClickListener {
            gayestNumberCounter++

            if (gayestNumberCounter > 57) {
                Constants.GAY_SWITCH_ENABLED = true
                savePreferences()
                binding.gayModePanel.visibility = View.VISIBLE
            }
        }

        if (Constants.GAY_SWITCH_ENABLED)
            binding.gayModePanel.visibility = View.VISIBLE

        binding.gaymodeSwitch.isChecked = Constants.GAY_THEME_ENABLED
        binding.gaymodeSwitch.setOnCheckedChangeListener {
            _, isChecked ->
            Constants.GAY_THEME_ENABLED = isChecked
            savePreferences()

            if (isChecked)
                activity!!.window.setBackgroundDrawableResource(R.drawable.background_app_pan)
            else
                activity!!.window.setBackgroundDrawableResource(R.drawable.background_app)
        }
    }

    private fun savePreferences() {
        dataHandler.saveUserData(this.activity!!.getSharedPreferences(Constants.USER_PREFERENCES, Context.MODE_PRIVATE))
    }

    private fun sendTestNotification() {
        if (Constants.ENABLE_NOTIFICATIONS)
            notification.new("You did it!", "You just enabled anniversary notifications", 1)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
