package com.base.project.data.repository.datasource.redditNews

import com.base.project.data.entity.GroupRedditNewsListEntity
import com.base.project.data.local.base.RedditNewsCache
import com.base.project.data.net.api.RedditNewsRestApi
import io.reactivex.Observable

open class CloudRedditNewsDataStore(private val redditNewsRestApi: RedditNewsRestApi,
                                    private val redditNewsCache: RedditNewsCache): RedditNewsDataStore {
	override fun getGroupRedditNewsList(after: String, limit: Int): Observable<GroupRedditNewsListEntity> {
		return redditNewsRestApi.getGroupRedditNewsTop(after = after, limit = limit)
	}
}