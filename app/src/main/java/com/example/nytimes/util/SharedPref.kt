package com.example.nytimes.util

import android.content.Context
import android.content.SharedPreferences


object SharedPref{

    private var pref: SharedPreferences? = null

    fun createPrefObject(context: Context): SharedPreferences? {
        if (pref == null) {
            pref = context.getSharedPreferences("nytimes", Context.MODE_PRIVATE)
        }
        return pref
    }

    fun setNightMode(mode:Boolean){
        val editor = pref!!.edit()
        editor.putBoolean("night_mode", mode)
        editor.apply()
    }
    fun getNightMode():Boolean{
        return pref!!.getBoolean("night_mode", false)
    }

}