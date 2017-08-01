package com.base.project.data.entity

import com.google.gson.annotations.SerializedName

data class GroupRedditNewsListEntity(
	@SerializedName("data") val groupRedditNewsList: RedditNewsListEntity?
)