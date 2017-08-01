package com.base.project.domain.repository

import com.base.project.domain.model.RedditComment
import io.reactivex.Observable


interface RedditCommentRepository {

	fun getCommentsByNewsId(id: String,
	                        depth: Int,
	                        limit: Int,
	                        sourceType: SourceType = SourceType.CLOUD): Observable<List<RedditComment>>
}