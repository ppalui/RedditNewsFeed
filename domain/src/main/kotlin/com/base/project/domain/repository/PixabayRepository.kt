package com.base.project.domain.repository

import com.base.project.domain.model.PixabayImage
import io.reactivex.Observable

/**
 * Interface that represents a Repository for getting [PixabayImage] related data.
 */
interface PixabayRepository {
	/**
	 * Get an [rx.Observable] which will emit a Detail of [PixabayImage].
	 */
	fun getImageByKeyword(keyword: String,
						  page: Int,
						  limit: Int,
						  sourceType: SourceType): Observable<PixabayImage>
}