package com.abdallah.sarrawi.mymsgs.ui.prfernces

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.abdallah.sarrawi.mymsgs.BuildConfig
import com.abdallah.sarrawi.mymsgs.R
import com.abdallah.sarrawi.mymsgs.Utils
import com.abdallah.sarrawi.mymsgs.databinding.FragmentFirstBinding
import com.abdallah.sarrawi.mymsgs.databinding.FragmentSecondBinding
import com.abdallah.sarrawi.mymsgs.databinding.FragmentSettingsBinding


class SettingsFragment : Fragment() {

    private lateinit var _binding : FragmentSettingsBinding
    private val binding get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)

        binding.shareApp.setOnClickListener {
            Utils.shareApp(requireContext())
        }

        appVers()
        privacypoli()



        return binding.root
    }

    private fun privacypoli() {
        binding.privacy.setOnClickListener {

            val url = "https://docs.google.com/document/d/1pnXOvy5K9_L3L9lYcC60WyWkEnMxek04K9805HZxatg/edit" // استبدل هذا برابط سياسة الخصوصية الخاص بك
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        }
    }

    private fun appVers() {
        val appVersion = "${BuildConfig.VERSION_NAME}"

        // قم بدمج النصوص باستخدام SpannableStringBuilder
        val builder = SpannableStringBuilder()
        val staticText = "إصدار التطبيق\n"
        val dynamicText = appVersion
//        val redColor = ForegroundColorSpan(resources.getColor(android.R.color.holo_red_light)) // احمر
//        val blackColor = ForegroundColorSpan(resources.getColor(android.R.color.black)) // اسود
        builder.append(staticText)
        builder.append(dynamicText)
//        builder.setSpan(blackColor, 0, staticText.length, 0) // تطبيق اللون الأسود على النص الثابت
//        builder.setSpan(redColor, staticText.length, builder.length, 0) // تطبيق اللون الأحمر على النص المتغير

        // قم بتعيين النص وتنسيقه في TextView
        binding.vers.text = builder
    }


}