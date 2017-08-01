package com.base.project.data.repository.datasource.pixabay

import com.base.project.data.local.base.PixabayCache
import com.base.project.data.net.ApiConnection
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Factory that creates different implementations of [PixabayDataStore].
 */
@Singleton
open class PixabayDataStoreFactory
@Inject constructor(private val pixabayCache: PixabayCache,
					private val apiConnection: ApiConnection) {
	/**
	 * Create [PixabayDataStore] from a synchronize.
	 */
	open fun createSync(): PixabayDataStore {
		val local = createLocalDataStore()
		val cloud = createCloudDataStore()
		return SyncPixabayDataStore(local, cloud)
	}

	/**
	 * Create [PixabayDataStore] to retrieve data from the Cloud.
	 */
	open fun createCloud(): PixabayDataStore {
		return createCloudDataStore()
	}

	/**
	 * Create [PixabayDataStore] to retrieve data from the Local.
	 */
	open fun createLocal(): PixabayDataStore {
		return createLocalDataStore()
	}

	private fun createLocalDataStore() = LocalPixabayDataStore(pixabayCache)

	private fun createCloudDataStore(): CloudPixabayDataStore {
		val pixabayRestApi = apiConnection.createPixabayRestApi()
		return CloudPixabayDataStore(pixabayRestApi, pixabayCache)
	}
}