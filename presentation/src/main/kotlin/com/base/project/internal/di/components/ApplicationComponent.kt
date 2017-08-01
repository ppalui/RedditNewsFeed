package com.base.project.internal.di.components

import android.content.Context
import com.base.project.data.local.base.PreferenceHelper
import com.base.project.domain.repository.PixabayRepository
import com.base.project.domain.repository.RedditCommentRepository
import com.base.project.domain.repository.RedditNewsRepository
import com.base.project.internal.di.modules.ApplicationModule
import com.navik.domain.executor.PostExecutionThread
import dagger.Component
import io.reactivex.Scheduler
import javax.inject.Singleton

/**
 * A component whose lifetime is the life of the application.
 */
@Singleton // Constraints this component to one-per-application or unscoped bindings.
@Component(modules = arrayOf(ApplicationModule::class))
interface ApplicationComponent {

	//Exposed to sub-graphs.
	fun context(): Context

	/**
	 * Defined Scheduler for exposed to sub-graph
	 */
	fun postExecutionThread(): PostExecutionThread

	fun schedulerIO(): Scheduler

	/**
	 * Defined Repository for exposed to sub-graph
	 */
	fun preferenceHelper(): PreferenceHelper

	fun pixabayRepository(): PixabayRepository

	fun redditNewsRepository(): RedditNewsRepository

	fun redditCommentCommentRepository(): RedditCommentRepository
}