package com.base.project.internal.di.components

import android.app.Activity
import com.base.project.internal.di.PerActivity
import com.base.project.internal.di.components.subcomponents.CommentListComponent
import com.base.project.internal.di.components.subcomponents.HomeComponent
import com.base.project.internal.di.modules.ActivityModule
import dagger.Component

/**
 * A base component upon which fragment's components may depend.
 * Activity-level components should extend this component.

 * Subtypes of ActivityComponent should be decorated with annotation:
 * [PerActivity]
 */
@PerActivity
@Component(dependencies = arrayOf(ApplicationComponent::class), modules = arrayOf(ActivityModule::class))
interface ActivityComponent {
	//Exposed to sub-graphs.
	fun activity(): Activity

	// Factory method to createSync subcomponent
	fun getHomeComponent(): HomeComponent

	fun getCommentListComponent(): CommentListComponent
}