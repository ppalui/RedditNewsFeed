package com.base.project.data.repository.datasource.pixabay

import com.base.project.data.entity.PixabayImageEntity
import com.base.project.data.local.base.PixabayCache
import com.base.project.data.net.api.PixabayRestApi
import io.reactivex.Observable

/**
 * [PixabayDataStore] implementation based on connections to the api (Cloud).
 *
 * Construct a [PixabayDataStore] based on connections to the api (Cloud).

 * @param pixabayRestApi The [PixabayRestApi] implementation to use.
 * @param pixabayCache A [PixabayCache] to cache data retrieved from the api.
 */
open class CloudPixabayDataStore(private val pixabayRestApi: PixabayRestApi,
								 private val pixabayCache: PixabayCache) : PixabayDataStore {

	override fun getPixabayImageEntityByKeyword(keyword: String,
												page: Int,
												limit: Int): Observable<PixabayImageEntity> {

		// TODO: Can implement .doOnNext to save cloud data to local, right now just ignore
		/*return pixabayRestApi.listImageByKeyword(keyword = keyword, page = page, per_page = limit).doOnNext(saveImageAction)*/

		return pixabayRestApi.listImageByKeyword(keyword = keyword, page = page, per_page = limit)
	}

	/*private val saveImageAction: Consumer<PixabayImageEntity> = Consumer { image ->
		if (image != null) {
			pixabayCache.insertOrUpdatePixabayImage(image)
		}
	}*/
}