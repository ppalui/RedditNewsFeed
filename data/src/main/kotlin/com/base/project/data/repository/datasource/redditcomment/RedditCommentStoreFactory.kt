package com.base.project.data.repository.datasource.redditcomment

import com.base.project.data.net.ApiConnection
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RedditCommentStoreFactory @Inject constructor(val apiConnection: ApiConnection) {

	open fun createCloud(): RedditCommentDataStore {
		val commentApi = apiConnection.createRedditCommentApi()
		return CloudCommentDataStore(commentApi)
	}
}