package com.base.project.data.local

import android.content.Context
import com.base.project.data.local.base.PreferenceHelper
import com.navik.data.extentions.defaultSharedPreferences
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
open class PreferenceHelperImpl @Inject constructor(context: Context) : PreferenceHelper {

	// TODO: Implement preference here
	private val preferences = context.defaultSharedPreferences
}