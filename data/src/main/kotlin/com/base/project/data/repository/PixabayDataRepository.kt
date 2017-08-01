package com.base.project.data.repository

import com.base.project.data.entity.mapper.PixabayImageEntityDataMapper
import com.base.project.data.repository.datasource.pixabay.PixabayDataStoreFactory
import com.base.project.domain.model.PixabayImage
import com.base.project.domain.repository.PixabayRepository
import com.base.project.domain.repository.SourceType
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton

/**
 * [PixabayRepository] for retrieving image data.
 *
 * Constructs a [PixabayRepository].
 * @param dataStoreFactory A factory to construct different data source implementations.
 * @param pixabayImageEntityDataMapper [PixabayImageEntityDataMapper].
 */
@Singleton
class PixabayDataRepository
@Inject constructor(private val dataStoreFactory: PixabayDataStoreFactory,
					private val pixabayImageEntityDataMapper: PixabayImageEntityDataMapper) : PixabayRepository {

	override fun getImageByKeyword(keyword: String,
								   page: Int,
								   limit: Int,
								   sourceType: SourceType): Observable<PixabayImage> {

		val pixabayDataStore = when (sourceType) {
			SourceType.CLOUD -> dataStoreFactory.createCloud()
			SourceType.LOCAL -> dataStoreFactory.createLocal()
			else             -> dataStoreFactory.createSync()
		}

		return pixabayDataStore.getPixabayImageEntityByKeyword(keyword, page, limit).map { pixabayImageEntityDataMapper.toPixabayImage(it) }
	}
}