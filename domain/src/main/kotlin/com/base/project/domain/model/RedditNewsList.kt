package com.base.project.domain.model

data class RedditNewsList(
	val redditNews: List<RedditNews?>,
    val after: String,
    val before: String? = ""
)