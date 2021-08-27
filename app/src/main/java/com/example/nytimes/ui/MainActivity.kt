package com.example.nytimes.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModelProviders
import com.example.nytimes.R
import com.example.nytimes.data.DefaultRemote
import com.example.nytimes.data.RemoteDataSource
import com.example.nytimes.data.repo.ApiRepository
import com.example.nytimes.data.repo.DefaultRepo
import com.example.nytimes.util.SharedPref


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    override fun onStart() {
        darkMode()
        super.onStart()
    }
    private fun darkMode() {
        SharedPref.createPrefObject(this)
        when(SharedPref.getNightMode()){
            true->    {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
            false->    {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }
    }


}