package com.base.project.data.entity.mapper

import com.base.project.data.entity.GroupRedditNewsListEntity
import com.base.project.data.entity.RedditNewsDataEntity
import com.base.project.data.entity.RedditNewsEntity
import com.base.project.data.entity.RedditNewsListEntity
import com.base.project.domain.model.GroupRedditNewsList
import com.base.project.domain.model.RedditNews
import com.base.project.domain.model.RedditNewsData
import com.base.project.domain.model.RedditNewsList
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
open class GroupRedditNewsListEntityDataMapper
@Inject constructor() {
	open fun toGroupRedditNewsList(entity: GroupRedditNewsListEntity?): GroupRedditNewsList? {
		return entity?.let {
			GroupRedditNewsList(groupRedditNewsList = toGroupRedditNews(it.groupRedditNewsList))
		}
	}

	private fun toGroupRedditNews(entity: RedditNewsListEntity?): RedditNewsList? {
		return entity?.let {
			RedditNewsList(redditNews = toRedditNewsList(it.redditNews), after = it.after, before = it.before)
		}
	}

	private fun toRedditNewsList(entity: List<RedditNewsEntity?>): List<RedditNews?> {
		return entity.mapNotNull { toRedditNews(it) }
	}

	private fun toRedditNews(entity: RedditNewsEntity?): RedditNews? {
		return entity?.let {
			RedditNews(redditNewData = toRedditNewsData(it.redditNewData))
		}
	}

	private fun toRedditNewsData(entity: RedditNewsDataEntity?): RedditNewsData? {
		return entity?.let {
			RedditNewsData(
				id = it.id,
				author = it.author,
				thumbnail = it.thumbnail,
				thumbnail_width = it.thumbnail_width,
				permalink = it.permalink,
				created = it.created,
				url = it.url,
				title = it.title,
				commentsAmount = it.commentsAmount
			)
		}
	}
}