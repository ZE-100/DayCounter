package com.daycounter.fragment

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import android.widget.RadioGroup
import androidx.annotation.RequiresApi
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.navigation.fragment.findNavController
import com.daycounter.R
import com.daycounter.activity.MainActivity
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
        //        val wrapped = DrawableCompat.wrap(AppCompatResources
//                .getDrawable(context as Context, R.drawable.settings_icon) as Drawable)
//why do i have to do this
//        DrawableCompat.setTint(wrapped, ContextCompat
//            .getColor(context as Context, R.color.nav_btn_focused))
//
//        binding.navigationButtons.gotoSettingsButton
//            .setCompoundDrawables(null, wrapped, null, null)
        //TODO: Change icon colour
        binding.navigationButtons.gotoSettingsButton.compoundDrawableTintList = ColorStateList.valueOf(activity!!.getColor(R.color.nav_btn_focused))

        binding.navigationButtons.gotoStartButton.setOnClickListener {
            findNavController().navigate(R.id.action_SettingsFragment_to_StartFragment)
        }

        binding.navigationButtons.gotoCountersButton.setOnClickListener {
            findNavController().navigate(R.id.action_SettingsFragment_to_CountersFragment)
        }
    }

    /**
     * Creates the button bindings with their
     * corresponding functionalities
     */
    private fun settingBtnBinding() {

        binding.notificationSwitch.isChecked = Constants.NOTIFICATIONS_ENABLED
        binding.notificationSwitch.setOnCheckedChangeListener {
            _, isChecked ->
            if (isChecked) {
                Constants.NOTIFICATIONS_ENABLED = isChecked
                Constants.RUN_IN_BACKGROUND_ENABLED = isChecked
                binding.runInBackgroundSwitch.isChecked = isChecked
            } else {
                Constants.NOTIFICATIONS_ENABLED = isChecked
            }
            savePreferences()
        }

        binding.runInBackgroundSwitch.isChecked = Constants.RUN_IN_BACKGROUND_ENABLED
        binding.runInBackgroundSwitch.setOnCheckedChangeListener {
            _, isChecked ->
            if (isChecked) {
                Constants.RUN_IN_BACKGROUND_ENABLED = isChecked
            } else {
                Constants.RUN_IN_BACKGROUND_ENABLED = isChecked
                Constants.NOTIFICATIONS_ENABLED = isChecked
                binding.notificationSwitch.isChecked = isChecked
            }

            savePreferences()
        }

        binding.wallpaperButton.setOnClickListener {
            sendTestNotification()
        }
    }

    private fun savePreferences() {
        handler.saveData(this.activity!!.getSharedPreferences(Constants.USER_PREFERENCES, Context.MODE_PRIVATE))

    }
    private fun sendTestNotification() {
        if (Constants.NOTIFICATIONS_ENABLED)
            (activity as MainActivity).createNewNotification("You did it!",
                "You just enabled anniversary notifications")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
