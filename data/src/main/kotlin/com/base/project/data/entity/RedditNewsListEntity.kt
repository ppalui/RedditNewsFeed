package com.base.project.data.entity

import com.google.gson.annotations.SerializedName

data class RedditNewsListEntity(
	@SerializedName("children") val redditNews: List<RedditNewsEntity?>,
	val after: String,
	val before: String? = ""
)