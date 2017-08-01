package com.base.project.data.net.api

import com.base.project.data.BuildConfig
import com.base.project.data.entity.PixabayImageEntity
import com.base.project.data.net.Url
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface PixabayRestApi {

	/**
	 * Retrieves an [Observable] which will emit a List of [PixabayImageEntity].
	 */
	@GET(Url.baseVersion)
	fun listImageByKeyword(@Query("key") apiKey: String = BuildConfig.PIXABAY_API_KEY,
						   @Query("q") keyword: String,
						   @Query("page") page: Int,
						   @Query("per_page") per_page: Int): Observable<PixabayImageEntity>

}