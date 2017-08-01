package com.base.project.domain.interactor

import com.base.project.domain.model.GroupRedditNewsList
import com.base.project.domain.params.GroupRedditNewsListParams
import com.base.project.domain.repository.RedditNewsRepository
import com.navik.domain.executor.PostExecutionThread
import com.navik.domain.interactor.UseCase
import io.reactivex.Observable
import io.reactivex.Scheduler
import javax.inject.Inject

open class GetGroupRedditNewsList
@Inject constructor(private val redditNewsRepository: RedditNewsRepository,
                    scheduler: Scheduler,
                    postExecutionThread: PostExecutionThread): UseCase<GroupRedditNewsList, GroupRedditNewsListParams>(scheduler,postExecutionThread) {
	override fun buildUseCaseObservable(params: GroupRedditNewsListParams?): Observable<GroupRedditNewsList> {
		if (params == null) {
			throw IllegalArgumentException("buildUseCaseObservable(params) not called, or called with null argument.")
		}

		return redditNewsRepository.getRedditNews(after = params.after ?: "", limit = params.limit)
	}
}