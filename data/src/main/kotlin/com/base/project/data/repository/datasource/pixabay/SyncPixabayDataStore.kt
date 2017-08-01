package com.base.project.data.repository.datasource.pixabay

import com.base.project.data.entity.PixabayImageEntity
import io.reactivex.Observable

/**
 * [PixabayDataStore] implementation based on connections to cache and api (Cloud).
 *
 * Construct a [PixabayDataStore] based on connections to cache and api (Cloud).
 *
 * @param localPixabayDataStore A [LocalPixabayDataStore] to cache data retrieved.
 * @param cloudPixabayDataStore A [CloudPixabayDataStore] to data retrieved from the api.
 */
open class SyncPixabayDataStore(private val localPixabayDataStore: LocalPixabayDataStore,
								private val cloudPixabayDataStore: CloudPixabayDataStore) : PixabayDataStore {

	override fun getPixabayImageEntityByKeyword(keyword: String,
												page: Int,
												limit: Int): Observable<PixabayImageEntity> {

		// TODO: We can use localDataStore and cloudDataStore to sync data from two source, right now just ignore
		throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
	}
}