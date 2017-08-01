package com.base.project.data.local.base
import com.base.project.data.entity.GroupRedditNewsListEntity
import io.reactivex.Observable

interface RedditNewsCache {
	fun getGroupRedditNewsList(): Observable<GroupRedditNewsListEntity>
}