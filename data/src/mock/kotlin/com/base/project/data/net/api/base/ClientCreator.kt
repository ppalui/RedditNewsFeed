package com.base.project.data.net.api.base

import com.base.project.data.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
open class ClientCreator @Inject constructor() {
	open fun createHttpClient(): OkHttpClient {
		val okHttpClientBuilder = OkHttpClient.Builder()

		okHttpClientBuilder.addInterceptor { chain ->
			val originalRequest = chain.request()
			val request = originalRequest.newBuilder()
				.header("Accept", "application/json")
				.method(originalRequest.method(), originalRequest.body())

			chain.proceed(request.build())
		}

		// Add interceptor for returning JSON data if file exist.
		okHttpClientBuilder.addInterceptor(HttpMockInterceptor())

		// Add Http Logging for debug mode
		if (BuildConfig.DEBUG) {
			val interceptor = HttpLoggingInterceptor()
			interceptor.level = HttpLoggingInterceptor.Level.BODY
			okHttpClientBuilder.addInterceptor(interceptor)
		}

		return okHttpClientBuilder.build()
	}
}