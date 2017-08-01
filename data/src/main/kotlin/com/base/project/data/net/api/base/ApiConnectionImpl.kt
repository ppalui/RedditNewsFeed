package com.base.project.data.net.api.base

import com.base.project.data.BuildConfig
import com.base.project.data.net.ApiConnection
import com.base.project.data.net.api.PixabayRestApi
import com.base.project.data.net.api.RedditCommentApi
import com.base.project.data.net.api.RedditNewsRestApi
import com.google.gson.GsonBuilder
import com.navik.data.exception.retrofit.RxErrorHandlingCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
open class ApiConnectionImpl
@Inject constructor(private val clientCreator: ClientCreator) : ApiConnection {

	override fun createPixabayRestApi(): PixabayRestApi {
		return createRetrofit(BuildConfig.BASE_PIXABAY_URL).create(PixabayRestApi::class.java)
	}

	override fun createRedditNewsRestApi(): RedditNewsRestApi {
		return createRetrofit(BuildConfig.BASE_REDDIT_URL).create(RedditNewsRestApi::class.java)
	}

	override fun createRedditCommentApi(): RedditCommentApi {
		return createRetrofit("https://www.reddit.com").create(RedditCommentApi::class.java)
	}

	/**
	 * FieldNamingPolicy use to convert property of dataSource to specific convention name
	 * eg. LOWER_CASE_WITH_UNDERSCORES will convert example_name to exampleName
	 */
	private fun createRetrofit(baseUrl: String): Retrofit {
		val gson = GsonBuilder()
//			.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
			.setDateFormat("yyyy-MM-dd HH:mm:ss")
			.create()

		val client = clientCreator.createHttpClient()
		return Retrofit.Builder()
			.baseUrl(baseUrl)
			.client(client)
			.addConverterFactory(GsonConverterFactory.create(gson))
			.addCallAdapterFactory(RxErrorHandlingCallAdapterFactory.create())
			.build()
	}
}