package com.base.project.data.net

import com.base.project.data.net.api.PixabayRestApi
import com.base.project.data.net.api.RedditCommentApi
import com.base.project.data.net.api.RedditNewsRestApi

interface ApiConnection {
	fun createPixabayRestApi(): PixabayRestApi

	fun createRedditNewsRestApi(): RedditNewsRestApi

	fun createRedditCommentApi(): RedditCommentApi
}