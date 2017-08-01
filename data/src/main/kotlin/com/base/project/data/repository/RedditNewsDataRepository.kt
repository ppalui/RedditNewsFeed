package com.base.project.data.repository

import com.base.project.data.entity.mapper.GroupRedditNewsListEntityDataMapper
import com.base.project.data.repository.datasource.redditNews.RedditNewsDataStoreFactory
import com.base.project.domain.model.GroupRedditNewsList
import com.base.project.domain.repository.RedditNewsRepository
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RedditNewsDataRepository
@Inject constructor(private val redditNewsDataFactory: RedditNewsDataStoreFactory,
                    private val redditNewsEntityDataMapper: GroupRedditNewsListEntityDataMapper): RedditNewsRepository {
	override fun getRedditNews(after: String, limit: Int): Observable<GroupRedditNewsList> {
		return redditNewsDataFactory.createCloud().getGroupRedditNewsList(after, limit).map { redditNewsEntityDataMapper.toGroupRedditNewsList(it) }
	}
}