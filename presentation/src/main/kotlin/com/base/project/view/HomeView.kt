package com.base.project.view

import com.base.project.model.RedditNewsListModel
import com.navik.presentation.view.LoadDataView

interface HomeView : LoadDataView {
	fun displayRedditNewsFeed(redditNewsList: RedditNewsListModel)

	fun appendRedditNewsFeed(redditNewsList: RedditNewsListModel)
}