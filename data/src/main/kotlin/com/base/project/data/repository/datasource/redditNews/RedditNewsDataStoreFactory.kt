package com.base.project.data.repository.datasource.redditNews

import com.base.project.data.local.base.RedditNewsCache
import com.base.project.data.net.ApiConnection
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
open class RedditNewsDataStoreFactory
@Inject constructor(private val redditNewsCache: RedditNewsCache,
                    private val apiConnection: ApiConnection) {
	open fun createCloud(): RedditNewsDataStore {
		val redditRestApi = apiConnection.createRedditNewsRestApi()
		return CloudRedditNewsDataStore(redditRestApi, redditNewsCache)
	}
}