package com.zasa.newcaloriy.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.zasa.newcaloriy.R
import com.zasa.newcaloriy.databinding.ActivitySearchBinding
import com.zasa.newcaloriy.databinding.ActivityWebBinding

class WebActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWebBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWebBinding.inflate(layoutInflater)
        setTheme(R.style.Theme_NewCaloriy)
        setContentView(binding.root)

        val bundle : Bundle? =intent.extras
        val title = bundle!!.getString("title")
        val webView = bundle.getString("sourceUrl")

        binding.tvMealWebActivity.text = title

        binding.wbMeal.webViewClient = WebViewClient()
        binding.wbMeal.loadUrl(webView.toString())
        binding.wbMeal.settings.javaScriptEnabled = true
        binding.wbMeal.settings.setSupportZoom(true)

    }


}