package com.abdallah.sarrawi.mymsgs.ui.prfernces

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.abdallah.sarrawi.mymsgs.R

class SettingsFrag : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
    }
}