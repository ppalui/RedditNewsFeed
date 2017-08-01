package com.base.project.ui.fragment.base

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.base.project.internal.di.HasComponent
import com.base.project.internal.di.components.ActivityComponent
import com.base.project.ui.activity.BaseActivity
import com.navik.presentation.extensions.finish

/**
 * Base [android.app.Fragment] class for every fragment in this application.
 */
abstract class BaseFragment : Fragment(), BaseActivity.OnBackPressedListener {
	init {
		retainInstance = true
	}

	abstract fun getContentViewId(): Int

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
		return inflater.inflate(getContentViewId(), container, false)
	}

	override fun onAttach(context: Context?) {
		super.onAttach(context)
		(activity as BaseActivity).registerOnBackPressedListener(this)
	}

	override fun onDetach() {
		super.onDetach()
		(activity as BaseActivity).unregisterOnBackPressedListener()
	}

	override fun onBackPressed() {
		finish()
	}

	/**
	 * Gets activity component for dependency injection to sub component.
	 */
	@Suppress("UNCHECKED_CAST")
	protected fun activityComponent(): ActivityComponent {
		return (ActivityComponent::class.java).cast((activity as HasComponent<ActivityComponent>).component())
	}
}