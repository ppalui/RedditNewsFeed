package com.base.project

import android.app.Application
import com.base.project.internal.di.components.ApplicationComponent
import com.base.project.internal.di.components.DaggerApplicationComponent
import com.base.project.internal.di.modules.ApplicationModule
import com.squareup.leakcanary.LeakCanary
import timber.log.Timber
import uk.co.chrisjenx.calligraphy.CalligraphyConfig

/**
 * Android Main Application
 */
class AndroidApplication : Application() {
	lateinit var applicationComponent: ApplicationComponent

	override fun onCreate() {
		super.onCreate()
		initializeInjector()
		initializeFonts()
		initializeLeakDetection()
		initializeLogging()
	}

	private fun initializeFonts() {
		// Need to custom font apply on Toolbar, we can not set custom font on XML for Toolbar :(
		val defaultFont = getString(R.string.fonts_default_bold)
		CalligraphyConfig.initDefault(CalligraphyConfig.Builder()
			.setDefaultFontPath(defaultFont)
			.setFontAttrId(R.attr.fontPath)
			.build())
	}

	private fun initializeInjector() {
		applicationComponent = DaggerApplicationComponent.builder()
			.applicationModule(ApplicationModule(this))
			.build()
	}

	private fun initializeLeakDetection() {
		if (BuildConfig.DEBUG) {
			LeakCanary.install(this)
		}
	}

	private fun initializeLogging() {
		if (BuildConfig.DEBUG) {
			Timber.plant(Timber.DebugTree())
		}
	}
}