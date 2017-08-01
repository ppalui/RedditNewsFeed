package com.base.project.model

data class RedditNewsDataModel(
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