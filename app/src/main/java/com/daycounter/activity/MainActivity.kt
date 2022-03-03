package com.daycounter.activity

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import com.daycounter.databinding.ActivityMainBinding
import com.daycounter.other.Constants

class MainActivity : AppCompatActivity() {
    //TODO On first startup: Upload avatar + names

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //TODO FOR TESTING PURPOSES ONLY: REMOVE
        val sharedPreferences = getSharedPreferences(Constants.MAIN_COUNTER, MODE_PRIVATE)
        val edit = sharedPreferences.edit()
        edit.apply {
            edit.clear()
        }.apply()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }


    }
}