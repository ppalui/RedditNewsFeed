package com.base.project.ui.activity

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import com.base.project.R
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper

/**
 * Base [android.app.Activity] class for every Activity in this application.
 */
abstract class BaseActivity : DaggerActivity() {
	protected var onBackPressedListener: OnBackPressedListener? = null

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(getContentViewId())
		setupToolbar()
	}

	override fun attachBaseContext(newBase: Context?) {
		super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase))
	}

	override fun onOptionsItemSelected(item: MenuItem): Boolean {
		when (item.itemId) {
			android.R.id.home -> {
				// Respond to the action bar's Up/Home button
				onBackPressed()
				return true
			}
		}
		return super.onOptionsItemSelected(item)
	}

	override fun onBackPressed() {
		if (onBackPressedListener == null) {
			super.onBackPressed()
		}
		else {
			onBackPressedListener?.onBackPressed()
		}
	}

	abstract fun getContentViewId(): Int

	/**
	 * Override this for configuration [Toolbar] eg. Icon, Title
	 */
	open fun onConfigurationToolbar(toolbar: Toolbar) {
	}

	fun registerOnBackPressedListener(listener: OnBackPressedListener) {
		this.onBackPressedListener = listener
	}

	fun unregisterOnBackPressedListener() {
		this.onBackPressedListener = null
	}

	protected fun setHomeAsUpEnable() {
		val actionBar = supportActionBar
		actionBar?.setDisplayHomeAsUpEnabled(true)
	}

	private fun setupToolbar() {
		val toolbar = findViewById(R.id.toolbar) as Toolbar?
		toolbar?.let {
			onConfigurationToolbar(toolbar)
			setSupportActionBar(toolbar)
		}
	}

	/**
	 * Adds a [Fragment] to this activity's layout.
	 * @param containerViewId The container view to where add the fragment.
	 * @param fragment The fragment to be added.
	 */
	protected fun addFragment(containerViewId: Int, fragment: Fragment) {
		supportFragmentManager.beginTransaction()
			.add(containerViewId, fragment)
			.commit()
	}

	interface OnBackPressedListener {

		/**
		 * Callback, which is called if the Back Button is pressed.
		 * Fragments that extend MainFragment can/should override this Method.
		 *
		 * @return true if the App can be closed, false otherwise
		 */
		fun onBackPressed()
	}
}
