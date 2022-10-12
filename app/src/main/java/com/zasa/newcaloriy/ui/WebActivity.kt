package com.zasa.newcaloriy.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.zasa.newcaloriy.R
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.activity_web.*
import kotlinx.android.synthetic.main.item_meal.*

class WebActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_NewCaloriy)
        setContentView(R.layout.activity_web)

        val bundle : Bundle? =intent.extras
        val title = bundle!!.getString("title")
        val webView = bundle.getString("sourceUrl")

        tvMealWebActivity.text = title

        wbMeal.webViewClient = WebViewClient()
        wbMeal.loadUrl(webView.toString())
        wbMeal.settings.javaScriptEnabled = true
        wbMeal.settings.setSupportZoom(true)

    }


}