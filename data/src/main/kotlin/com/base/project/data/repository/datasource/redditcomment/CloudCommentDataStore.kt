package com.base.project.data.repository.datasource.redditcomment

import com.base.project.data.entity.RedditComment.RedditWrapper
import com.base.project.data.net.api.RedditCommentApi

import io.reactivex.Observable


class CloudCommentDataStore(val commentApi: RedditCommentApi) : RedditCommentDataStore {

	override fun getCommentWrapper(id: String, depth: Int, limit: Int): Observable<RedditWrapper> {
		return commentApi.getComments(id, depth, limit).map {
			it.get(1)!!
		}
	}
}