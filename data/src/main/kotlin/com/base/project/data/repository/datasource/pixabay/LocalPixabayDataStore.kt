package com.base.project.data.repository.datasource.pixabay

import com.base.project.data.entity.PixabayImageEntity
import com.base.project.data.local.base.PixabayCache
import io.reactivex.Observable

/**
 * [PixabayDataStore] implementation based on file system data store.
 *
 * Construct a [PixabayDataStore] based file system data store.

 * @param pixabayCache A [PixabayCache] to cache data retrieved from the paper.
 */
open class LocalPixabayDataStore(private val pixabayCache: PixabayCache) : PixabayDataStore {

	override fun getPixabayImageEntityByKeyword(keyword: String,
												page: Int,
												limit: Int): Observable<PixabayImageEntity> {

		// TODO: Can implement local if possible
		return pixabayCache.getPixabayImage()
	}
}