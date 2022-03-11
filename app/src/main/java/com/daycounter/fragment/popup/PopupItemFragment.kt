package com.daycounter.fragment.popup

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.navigation.fragment.findNavController
import com.daycounter.R
import com.daycounter.databinding.FragmentPopupItemBinding

class PopupItemFragment : Fragment() {

    private var _binding: FragmentPopupItemBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?, savedInstanceState: Bundle?): View {

        _binding = FragmentPopupItemBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        generateBindings()
    }

    private fun generateBindings() {

        binding.cancelButton.setOnClickListener {
            findNavController().navigate(R.id.action_popup_to_reminders)
        }

        binding.saveButton.setOnClickListener {
            val title: String = binding.popupTitle.text.toString()
            val description: String = binding.popupDescription.text.toString()
            val date: String = binding.popupDate.text.toString()
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}