package com.base.project.mapper

import com.base.project.domain.model.GroupRedditNewsList
import com.base.project.domain.model.RedditNews
import com.base.project.domain.model.RedditNewsData
import com.base.project.domain.model.RedditNewsList
import com.base.project.internal.di.PerFragment
import com.base.project.model.GroupRedditNewsListModel
import com.base.project.model.RedditNewsDataModel
import com.base.project.model.RedditNewsListModel
import com.base.project.model.RedditNewsModel
import javax.inject.Inject

@PerFragment
open class GroupRedditNewsListModelDataMapper @Inject constructor() {
	open fun toGroupRedditNewsList(model: GroupRedditNewsList?): GroupRedditNewsListModel? {
		return model?.let {
			GroupRedditNewsListModel(groupRedditNewsList = toGroupRedditNewsModel(it.groupRedditNewsList))
		}
	}

	private fun toGroupRedditNewsModel(model: RedditNewsList?): RedditNewsListModel? {
		return model?.let {
			RedditNewsListModel(redditNews = toRedditNewsModelList(it.redditNews), after = it.after, before = it.before)
		}
	}

	private fun toRedditNewsModelList(model: List<RedditNews?>): List<RedditNewsModel?> {
		return model.mapNotNull { toRedditNewsModel(it) }
	}

	private fun toRedditNewsModel(model: RedditNews?): RedditNewsModel? {
		return model?.let {
			RedditNewsModel(redditNewData = toRedditNewsDataModel(it.redditNewData))
		}
	}

	private fun toRedditNewsDataModel(model: RedditNewsData?): RedditNewsDataModel? {
		return model?.let {
			RedditNewsDataModel(
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