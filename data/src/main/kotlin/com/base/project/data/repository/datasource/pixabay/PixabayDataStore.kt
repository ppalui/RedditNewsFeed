package com.base.project.data.repository.datasource.pixabay

import com.base.project.data.entity.PixabayImageEntity
import io.reactivex.Observable

/**
 * Interface that represents a data store from where data is retrieved.
 */
interface PixabayDataStore {
	fun getPixabayImageEntityByKeyword(keyword: String,
									   page: Int,
									   limit: Int): Observable<PixabayImageEntity>
}