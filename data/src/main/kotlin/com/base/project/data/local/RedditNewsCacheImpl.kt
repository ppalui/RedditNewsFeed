package com.base.project.data.local

import android.content.Context
import com.base.project.data.entity.GroupRedditNewsListEntity
import com.base.project.data.local.base.RedditNewsCache
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
open class RedditNewsCacheImpl
@Inject constructor(context: Context): RedditNewsCache {
	override fun getGroupRedditNewsList(): Observable<GroupRedditNewsListEntity> {
		//TODO
		throw UnsupportedOperationException("not implemented")
	}
}