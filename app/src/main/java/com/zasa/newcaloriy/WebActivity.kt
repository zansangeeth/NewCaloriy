package com.zasa.newcaloriy

import android.app.ProgressDialog
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_web.*
import kotlinx.android.synthetic.main.item_meal.*

class WebActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web)

        val bundle : Bundle? =intent.extras
        val title = bundle!!.getString("title")
        val webView = bundle.getString("sourceUrl")

        tvMealWebActivity.text = title

//        wbMeal.webViewClient = object : WebViewClient(){
//            override fun shouldOverrideUrlLoading(
//                view: WebView?,
//                request: WebResourceRequest?
//            ): Boolean {
//                view?.loadUrl(webView.toString())
//                return super.shouldOverrideUrlLoading(view, request)
//            }
//        }

        wbMeal.webViewClient = WebViewClient()
        wbMeal.loadUrl(webView.toString())
        wbMeal.settings.javaScriptEnabled = true
        wbMeal.settings.setSupportZoom(true)

    }
}