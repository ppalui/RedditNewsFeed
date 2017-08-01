package com.base.project.domain.interactor

import com.base.project.domain.model.RedditComment
import com.base.project.domain.params.ListRedditCommentParams
import com.base.project.domain.repository.RedditCommentRepository
import com.navik.domain.executor.PostExecutionThread
import com.navik.domain.interactor.UseCase
import io.reactivex.Observable
import io.reactivex.Scheduler
import javax.inject.Inject

class GetRedditCommentByNewsId @Inject constructor(private val redditCommentRepository: RedditCommentRepository,
                                                   scheduler: Scheduler,
                                                   postExecutionThread: PostExecutionThread)

	: UseCase<List<RedditComment>, ListRedditCommentParams>(scheduler, postExecutionThread) {

	override fun buildUseCaseObservable(params: ListRedditCommentParams?): Observable<List<RedditComment>> {
		return redditCommentRepository.getCommentsByNewsId(params?.newsId ?: "", params?.depth ?: 1, params?.limit ?: 100)
	}

}