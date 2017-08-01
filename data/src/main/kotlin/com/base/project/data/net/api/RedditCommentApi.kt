package com.base.project.data.net.api

import com.base.project.data.entity.RedditComment.RedditWrapper
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface RedditCommentApi {

	@GET("comments/{id}.json")
	fun getComments(@Path("id") id: String,
	                @Query("depth") deptp: Int,
					@Query("limit") limit: Int): Observable<List<RedditWrapper?>>
}
