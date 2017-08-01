package com.base.project.extensions

import android.content.Intent
import android.net.Uri
import android.support.v4.app.Fragment
import com.base.project.BuildConfig
import com.base.project.ui.activity.CommentsActivity
import com.base.project.ui.activity.DefaultWebViewActivity



fun Fragment.navigateToWebView(url: String, title: String) {
	val intentToLaunch = DefaultWebViewActivity.getCallingIntent(context, url, title)
	this.startActivity(intentToLaunch)
}

fun Fragment.navigateToComments(id: String) {
	val intent = CommentsActivity.getCallingIntent(context, id)
	this.startActivity(intent)
}

fun Fragment.navigateShareLinkToOtherApp(id: String, permalink: String, title: String){
	val urlToShare = BuildConfig.BASE_REDDIT_URL + permalink
	try {
		val intent = Intent()
		intent.action = Intent.ACTION_SEND
		intent.type = "text/plain"
		intent.putExtra(Intent.EXTRA_TEXT, urlToShare)
		startActivity(intent)
	}
	catch (e: Exception) {
		// If we failed (not native FB app installed), try share through SEND
		val sharerUrl = "https://www.facebook.com/sharer/sharer.php?u=" + urlToShare
		val intent = Intent(Intent.ACTION_VIEW, Uri.parse(sharerUrl))
		startActivity(intent)
	}
}
