package com.base.project.data.repository

import com.base.project.data.entity.mapper.RedditCommentEntityMapper
import com.base.project.data.repository.datasource.redditcomment.RedditCommentStoreFactory
import com.base.project.domain.model.RedditComment
import com.base.project.domain.repository.RedditCommentRepository
import com.base.project.domain.repository.SourceType
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RedditCommentDataRepository @Inject constructor(
	private val redditCommentDataStoreFactory: RedditCommentStoreFactory,
    private val redditCommentEntityMapper: RedditCommentEntityMapper
) : RedditCommentRepository {

	override fun getCommentsByNewsId(id: String,
	                                 depth: Int,
	                                 limit: Int,
	                                 sourceType: SourceType): Observable<List<RedditComment>> {
		val dataStore =
			when(sourceType) {
				SourceType.CLOUD -> redditCommentDataStoreFactory.createCloud()
				else -> redditCommentDataStoreFactory.createCloud()
			}

		return dataStore.getCommentWrapper(id, depth, limit).map { redditCommentEntityMapper.toRedditCommentList(it) }

	}
}