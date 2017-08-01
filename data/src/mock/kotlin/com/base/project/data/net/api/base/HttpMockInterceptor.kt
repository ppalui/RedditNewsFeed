package com.base.project.data.net.api.base

import okhttp3.*
import timber.log.Timber


class HttpMockInterceptor : Interceptor {

	override fun intercept(chain: Interceptor.Chain): Response {
		val request = chain.request()

		val startTime = System.currentTimeMillis()
		val fileName = getFilename(request)
		Timber.d("File name: " + fileName)

		val json = getJsonData(fileName)
		Timber.d("JSON response: " + json)

		if (json != null) {
			val response = Response.Builder()
			response.request(request)
			response.headers(request.headers())
			response.code(200)
			response.body(getResponseBody(json))
			response.protocol(Protocol.HTTP_1_1)

			val totalRequestTime = System.currentTimeMillis() - startTime
			response.sentRequestAtMillis(totalRequestTime)

			return response.build()
		}

		return chain.proceed(request)
	}

	private fun getFilename(request: Request): String {

		val requestedUrl = request.url()
		var filename = request.method()

		requestedUrl.pathSegments().forEach {
			if (it.isNullOrEmpty().not())
				filename += INTERMEDIATE_STRING + it
		}

		requestedUrl.queryParameterNames().forEach { paramKey ->
			if (paramKey.isNullOrEmpty().not()) {
				requestedUrl.queryParameterValues(paramKey).forEach { paramValue ->
					if (paramValue.isNullOrEmpty().not())
						filename += INTERMEDIATE_STRING + paramKey + INTERMEDIATE_STRING + paramValue
				}
			}
		}

		REPLACE_URL_LIST.forEach {
			filename = filename.replace(it, INTERMEDIATE_STRING)
		}

		filename = filename.toLowerCase()
		return JSON_PATH + filename + FILE_TYPE
	}

	private fun getJsonData(filename: String): String? {
		val reader = HttpMockInterceptor::class.java.classLoader.getResourceAsStream(filename)?.bufferedReader()

		var response: String? = null
		reader?.use { reader ->
			response = reader.lineSequence().joinToString("")
		}
		return response
	}

	private fun getResponseBody(json: String): ResponseBody {
		return ResponseBody.create(MediaType.parse("application/json"), json)
	}

	companion object {
		val REPLACE_URL_LIST = listOf("/", "-", "{", "}", ",")
		val INTERMEDIATE_STRING = "_"
		val JSON_PATH = "assets/mock/"
		val FILE_TYPE = ".json"
	}
}