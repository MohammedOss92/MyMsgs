package com.abdallah.sarrawi.mymsgs

import android.app.Application
import android.content.Context
import androidx.appcompat.app.AppCompatDelegate

class App : Application() {

     companion object {
         var ctx: Context? = null
     }
    var IsDark :Boolean = true
     override fun onCreate() {
         super.onCreate()
         ctx = applicationContext

         var sharedPref: SharedPref = SharedPref(this)
         IsDark=sharedPref.getThemeStatePref()


         if(IsDark){
             AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
         }
         else{
             AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
         }
     }

 }