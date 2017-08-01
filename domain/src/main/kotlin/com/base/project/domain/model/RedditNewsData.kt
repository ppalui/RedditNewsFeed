package com.base.project.domain.model

data class RedditNewsData(
	val id: String,
    val author: String,
    val thumbnail: String,
	val thumbnail_width: Int,
	val permalink: String,
    val created: Long,
    val url: String,
    val title: String,
    val commentsAmount: Int
)