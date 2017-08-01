package com.base.project.ui.adapter

import android.content.Context
import com.base.project.R
import com.base.project.extensions.allias.NewsItemClickListener
import com.base.project.extensions.allias.NewsItemCommentsClickListener
import com.base.project.extensions.allias.NewsItemShareClickListener
import com.base.project.model.RedditNewsModel
import com.base.project.model.epoxy.NewsModel_
import com.kingpower.member.ui.adapter.base.BaseEpoxyAdapter

class RedditNewsFeedAdapter : BaseEpoxyAdapter() {

	var newsItemClickListener: NewsItemClickListener? = null
	var newsItemCommentsClickListener: NewsItemCommentsClickListener? = null
	var newsItemShareClickListener: NewsItemShareClickListener? = null

	fun appendRedditNews(redditNewsModel: RedditNewsModel?, comment: String, author: String) {
		redditNewsModel?.let {
			addModel(
				NewsModel_()
					.model(redditNewsModel)
					.commentText(comment)
					.authorText(author)
					.newsItemClickListener(newsItemClickListener)
					.newsItemCommentsClickListener(newsItemCommentsClickListener)
					.newsItemShareClickListener(newsItemShareClickListener)
			)
		}
	}

	fun setRedditNews(redditNewsModelList: List<RedditNewsModel?>, context: Context) {
		clearItemList()
		redditNewsModelList.mapNotNull {
			appendRedditNews(it,
				context.getString(R.string.comments, it?.redditNewData?.commentsAmount?.toString()),
				context.getString(R.string.author, it?.redditNewData?.author))
		}
	}

	override fun clearItemList() {
		removeAllModels()
	}
}