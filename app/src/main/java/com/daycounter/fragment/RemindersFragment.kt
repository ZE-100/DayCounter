package com.daycounter.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.daycounter.R
import com.daycounter.blueprint.Reminder
import com.daycounter.databinding.FragmentRemindersBinding
import com.daycounter.other.RecyclerViewAdapter
import java.util.*
import androidx.recyclerview.widget.LinearLayoutManager




class RemindersFragment : Fragment() {

    private var _binding: FragmentRemindersBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?, savedInstanceState: Bundle?): View? {

        _binding = FragmentRemindersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.navigationButtons.gotoStartButton.setOnClickListener {
            findNavController().navigate(R.id.action_reminders_to_start)
        }

        binding.navigationButtons.gotoSettingsButton.setOnClickListener {
            findNavController().navigate(R.id.action_counters_to_settings)
        }

        createRecyclerView()
    }

    private fun createRecyclerView() {
        val reminders: List<Reminder> = getReminders()
        binding.remindersRecycleView.adapter = RecyclerViewAdapter(context, reminders)

        val linearLayoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.remindersRecycleView.layoutManager = linearLayoutManager
    }

    private fun getReminders(): List<Reminder> {
        return listOf(
            Reminder("Sananas", "Sosasdasdasd", 5, Date()),
            Reminder("ads", "Sosasdasdadasdasd", 5, Date()),
            Reminder("Sanaasdasnas", "Sosaasdasdsdasdasd", 5, Date()),
            Reminder("asdas", "asdasd", 5, Date()))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
