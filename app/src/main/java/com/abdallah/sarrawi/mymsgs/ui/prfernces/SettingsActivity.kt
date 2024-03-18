package com.abdallah.sarrawi.mymsgs.ui.prfernces

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceFragmentCompat
import com.abdallah.sarrawi.mymsgs.R

class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings_activity)
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.settings, SettingsFrag())
                .commit()
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }


}