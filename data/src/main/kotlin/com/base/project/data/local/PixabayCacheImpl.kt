package com.base.project.data.local

import android.content.Context
import com.base.project.data.entity.PixabayImageEntity
import com.base.project.data.local.base.DbWrapper
import com.base.project.data.local.base.PixabayCache
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton

/**
 * CacheImpl will implement [Cache] and use rxPaper to save data to local via DbWrapper
 */
@Singleton
open class PixabayCacheImpl @Inject constructor(context: Context) : PixabayCache {

	companion object {
		private val CACHE_TIME_IMAGE = 900000L
		private val ARG_PIXABAY_IMAGE = "ARG_PIXABAY_IMAGE"
	}

	private val db by lazy { DbWrapper(context, "images") }

	override fun getPixabayImage(): Observable<PixabayImageEntity> {
		//TODO
		throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
	}

	override fun insertOrUpdatePixabayImage(pixabayImage: PixabayImageEntity) {
		//TODO
		throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
	}
}