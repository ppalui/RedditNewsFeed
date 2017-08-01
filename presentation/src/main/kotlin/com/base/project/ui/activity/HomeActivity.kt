package com.base.project.ui.activity

import android.os.Bundle
import com.base.project.R
import com.base.project.ui.fragment.HomeFragment

class HomeActivity : BaseActivity() {

	override fun getContentViewId(): Int = R.layout.activity_layout

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setTitle(R.string.reddit)
		initializeActivity(savedInstanceState)
	}

	/**
	 * Initializes this activity.
	 */
	private fun initializeActivity(savedInstanceState: Bundle?) {
		if (savedInstanceState == null) {
			addFragment(R.id.fragmentContainer, HomeFragment())
		}
	}
}
