package com.base.project.data.net.api

import com.base.project.data.entity.GroupRedditNewsListEntity
import com.base.project.data.net.Url
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface RedditNewsRestApi {
	@GET(Url.baseRedditVersion)
	fun getGroupRedditNewsTop(@Query("after") after: String,
	                          @Query("limit") limit: Int): Observable<GroupRedditNewsListEntity>
}