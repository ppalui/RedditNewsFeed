package com.base.project.data.repository.datasource.redditcomment

import com.base.project.data.entity.RedditComment.RedditWrapper

import io.reactivex.Observable


interface RedditCommentDataStore {
	fun getCommentWrapper(id: String, depth: Int, limit: Int): Observable<RedditWrapper>
}