package com.example.nytimes.ui.settings

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatDelegate
import androidx.preference.*
import com.example.nytimes.R
import com.example.nytimes.util.SharedPref
import com.example.nytimes.ui.MainActivity

class SettingsFragment :  PreferenceFragmentCompat(){


    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
        darkMode()
        back()
    }

    private fun darkMode() {

        var darkMode = findPreference<CheckBoxPreference>("background_mode")
        darkMode?.setOnPreferenceChangeListener { prefs, obj ->
            val yes = obj as Boolean
            if (yes) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                SharedPref.setNightMode(true)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                SharedPref.setNightMode(false)
            }

            true
        }
    }

    private fun back() {
        val back = findPreference<Preference>("back")
        back?.setOnPreferenceClickListener {
            if (it.key == "back") {
                startActivity(Intent(requireContext(), MainActivity::class.java))
                activity?.finish()

            }
            true
        }
    }

    override fun onStart() {
        super.onStart()

        when(SharedPref.getNightMode()){
            true->    {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                Log.i("settings","dark")
            }
            false->    {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                Log.i("settings","light")
            }
        }

    }
}