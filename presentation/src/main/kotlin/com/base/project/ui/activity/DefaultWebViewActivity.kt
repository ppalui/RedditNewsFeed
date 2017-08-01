package com.base.project.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import com.base.project.R
import com.base.project.utils.LocaleUtils
import com.navik.presentation.extensions.hide
import kotlinx.android.synthetic.main.activity_webview.*

class DefaultWebViewActivity : BaseActivity() {

    override fun getContentViewId(): Int = R.layout.activity_webview

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHomeAsUpEnable()
        with(intent) {
            val title: String? = this.getStringExtra(EXTRA_TITLE)
            val url: String? = this.getStringExtra(EXTRA_URL)
            setTitle(title)
            setupWebView()
            loadWebViewUrl(url)
        }
    }

    override fun onConfigurationToolbar(toolbar: Toolbar) {
        toolbar.setNavigationIcon(R.drawable.ic_close_cross_black)
    }

    private fun setupWebView() {
        // Enable Javascript
        val webSettings = webView.settings
        webSettings.javaScriptEnabled = true

        // Force links and redirects to open in the WebView instead of in a browser
        webView.setWebViewClient(WebViewClient())
        webView.setWebChromeClient(object : WebChromeClient() {
            override fun onProgressChanged(view: WebView, progress: Int) {
                if (progress >= 100) progressBar.hide()
            }
        })
    }

    private fun loadWebViewUrl(url: String?) {
        url?.let {
            webView.loadUrl(url, mapOf(HEADER_LANGUAGE to LocaleUtils.getLocale(this).text))
        }
    }

    companion object {
        private val HEADER_LANGUAGE = "Accept-Language"

        private val EXTRA_TITLE = ":extras-title"
        private val EXTRA_URL = ":extras-url"

        fun getCallingIntent(context: Context, url: String, title: String): Intent {
            val callingIntent = Intent(context, DefaultWebViewActivity::class.java)
            callingIntent.putExtra(EXTRA_TITLE, title)
            callingIntent.putExtra(EXTRA_URL, url)
            return callingIntent
        }
    }
}