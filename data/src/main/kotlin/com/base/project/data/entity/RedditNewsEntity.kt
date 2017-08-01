package com.base.project.data.entity

import com.google.gson.annotations.SerializedName

data class RedditNewsEntity (
	@SerializedName("data") val redditNewData: RedditNewsDataEntity?
)