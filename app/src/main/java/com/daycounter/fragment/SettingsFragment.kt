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

class SettingsFragment : Fragment() {

    private var _binding: FragmentSettingsBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?, savedInstanceState: Bundle?): View? {

        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("UseCompatTextViewDrawableApis")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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

        binding.wallpaperButton.setOnClickListener {
            sendTestNotification()
        } //TODO Safe this shit in preferences

        binding.notificationSwitch.setOnCheckedChangeListener {
                _, isChecked -> Constants.NOTIFICATIONS_ENABLED = isChecked
        }
    }

    private fun sendTestNotification() {
        if (Constants.NOTIFICATIONS_ENABLED)
            (activity as MainActivity).createNewNotification("This is a test",
                "If you see this, you did everything right!") //TODO: Replace with option to turn off notifications
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
