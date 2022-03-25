package com.daycounter.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.daycounter.R
import com.daycounter.databinding.FragmentRemindersBinding
import com.daycounter.other.custom.RecyclerViewAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.daycounter.dataclass.Reminders
import com.daycounter.other.enum.Constants
import com.daycounter.service.data.SaveUserDataService


class RemindersFragment : Fragment() {

    private var _binding: FragmentRemindersBinding? = null
    private val binding get() = _binding!!

    private val dataHandler = SaveUserDataService()

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?, savedInstanceState: Bundle?): View? {

        _binding = FragmentRemindersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        generateNavBindings()
        generateReminderBindings()
    }

    private fun generateNavBindings() {
        binding.navigationButtons.gotoStartButton.setOnClickListener {
            findNavController().navigate(R.id.action_reminders_to_start)
        }

        binding.navigationButtons.gotoSettingsButton.setOnClickListener {
            findNavController().navigate(R.id.action_reminders_to_settings)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun generateReminderBindings() {
        binding.addReminderButton.setOnClickListener {
            binding.remindersRecycleView.adapter!!.notifyDataSetChanged()
            findNavController().navigate(R.id.action_reminders_to_popup)
        }

        binding.remindersRecycleView.adapter = RecyclerViewAdapter(context, Reminders.get())
        binding.remindersRecycleView.layoutManager = LinearLayoutManager(
            context, LinearLayoutManager.VERTICAL, false)

        context!!.stopService(Constants.BACKGROUND_INTENT)
        context!!.startService(Constants.BACKGROUND_INTENT)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
