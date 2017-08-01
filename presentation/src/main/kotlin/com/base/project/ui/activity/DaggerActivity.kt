package com.base.project.ui.activity

import android.support.v7.app.AppCompatActivity
import com.base.project.AndroidApplication
import com.base.project.internal.di.HasComponent
import com.base.project.internal.di.PerActivity
import com.base.project.internal.di.components.ActivityComponent
import com.base.project.internal.di.components.DaggerActivityComponent
import com.base.project.internal.di.modules.ActivityModule

abstract class DaggerActivity : AppCompatActivity(), HasComponent<ActivityComponent> {
	@PerActivity private val component: ActivityComponent by lazy { initComponent() }

	override fun component(): ActivityComponent = component

	/**
	 * Get the Main Application component for dependency injection.
	 * @return [DaggerActivityComponent]
	 */
	protected fun getApplicationComponent() = (application as AndroidApplication).applicationComponent

	private fun initComponent(): ActivityComponent {
		return DaggerActivityComponent.builder()
			.applicationComponent(getApplicationComponent())
			.activityModule(ActivityModule(this))
			.build()
	}
}