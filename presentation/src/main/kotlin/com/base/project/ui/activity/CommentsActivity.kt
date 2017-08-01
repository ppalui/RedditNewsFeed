package com.base.project.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.base.project.R
import com.base.project.ui.fragment.CommentListFragment

class CommentsActivity: BaseActivity() {
	override fun getContentViewId(): Int = R.layout.activity_layout

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setTitle(R.string.comment)
		initializeActivity(savedInstanceState)
		//Log.v("Joe", intent.getStringExtra(EXTRA_ID))
	}

	private fun initializeActivity(savedInstanceState: Bundle?) {
		if (savedInstanceState == null) {
			addFragment(R.id.fragmentContainer, CommentListFragment())
		}
	}

	companion object {
		 val EXTRA_ID = ":EXTRA_ID"

		fun getCallingIntent(context: Context, id: String?): Intent {
			val intent = Intent(context, CommentsActivity::class.java)
			intent.putExtra(EXTRA_ID, id)
			return intent
		}
	}
}