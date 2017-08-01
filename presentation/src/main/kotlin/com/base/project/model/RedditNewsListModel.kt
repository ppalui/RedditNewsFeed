package com.base.project.model

data class RedditNewsListModel(
	val redditNews: List<RedditNewsModel?>,
	val after: String,
	val before: String? = ""
)