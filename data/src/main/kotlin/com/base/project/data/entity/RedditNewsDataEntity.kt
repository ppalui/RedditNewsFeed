package com.base.project.data.entity

import com.google.gson.annotations.SerializedName

data class RedditNewsDataEntity(
	val id: String,
	val author: String,
	val thumbnail: String,
	val thumbnail_width: Int,
	val permalink: String,
	val created: Long,
	val url: String,
	val title: String,
	@SerializedName("num_comments") val commentsAmount: Int
)