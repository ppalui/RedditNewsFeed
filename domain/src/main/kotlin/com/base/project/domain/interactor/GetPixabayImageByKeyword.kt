package com.base.project.domain.interactor

import com.base.project.domain.model.PixabayImage
import com.base.project.domain.params.ListPixabayImageParams
import com.base.project.domain.repository.PixabayRepository
import com.navik.domain.executor.PostExecutionThread
import com.navik.domain.interactor.UseCase
import io.reactivex.Observable
import io.reactivex.Scheduler
import javax.inject.Inject

/**
 * This class is an implementation of [UseCase] that represents a use case for
 * retrieving a collection of image [PixabayImage].
 */
open class GetPixabayImageByKeyword
@Inject constructor(private val pixabayRepository: PixabayRepository,
					scheduler: Scheduler,
					postExecutionThread: PostExecutionThread) : UseCase<PixabayImage, ListPixabayImageParams>(scheduler, postExecutionThread) {

	override fun buildUseCaseObservable(params: ListPixabayImageParams?): Observable<PixabayImage> {
		if (params == null) {
			throw IllegalArgumentException("buildUseCaseObservable(params) not called, or called with null argument.")
		}

		return pixabayRepository.getImageByKeyword(keyword = params.keyword ?: "", page = params.page, limit = params.limit, sourceType = params.sourceType)
	}
}