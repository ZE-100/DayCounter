package com.daycounter.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.daycounter.R
import com.daycounter.blueprint.Counter
import com.daycounter.databinding.FragmentFirstStartupBinding
import com.daycounter.service.data.DataHandlingService
import java.text.SimpleDateFormat
import java.util.*

class FirstStartupFragment : Fragment() {

    private var _binding: FragmentFirstStartupBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        _binding = FragmentFirstStartupBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.submitButton.setOnClickListener {
            saveNewMainCounter()
            findNavController().navigate(R.id.action_FStartupFragment_to_StartFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun saveNewMainCounter() {
        val sdf = SimpleDateFormat("dd-MM-yyyy-HH-mm-ss", Locale.GERMANY)

        val handler = DataHandlingService()

        val counter = Counter("sas", "sos", sdf.parse("27-12-2019-00-00-00"))

        handler.saveData(this.context!!.getSharedPreferences("main-counter", Context.MODE_PRIVATE), counter)
    }
}
