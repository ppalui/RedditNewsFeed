package com.base.project.domain.repository
import com.base.project.domain.model.GroupRedditNewsList
import io.reactivex.Observable

interface RedditNewsRepository {
	fun getRedditNews(after: String, limit: Int): Observable<GroupRedditNewsList>
}