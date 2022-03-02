package com.daycounter.activity

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import com.daycounter.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    //TODO On first startup: Upload avatar + names

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //TODO FOR TESTING PURPOSES ONLY: REMOVE
        val sharedPreferences = getSharedPreferences("main-counter", MODE_PRIVATE)
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