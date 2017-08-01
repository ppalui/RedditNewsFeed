package com.base.project.data.repository.datasource.redditNews

import com.base.project.data.entity.GroupRedditNewsListEntity
import io.reactivex.Observable

interface RedditNewsDataStore {
	fun getGroupRedditNewsList(after: String,
	                           limit: Int): Observable<GroupRedditNewsListEntity>
}