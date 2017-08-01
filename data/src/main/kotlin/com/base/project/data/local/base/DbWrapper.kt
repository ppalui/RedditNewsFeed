package com.base.project.data.local.base

import android.content.Context
import com.base.project.data.local.base.model.CacheData
import com.navik.data.exception.CacheExpiredException
import com.pacoworks.rxpaper2.RxPaperBook
import io.reactivex.Completable
import io.reactivex.Observable
import timber.log.Timber

class DbWrapper(context: Context, dbName: String) {

	private val db: RxPaperBook by lazy { RxPaperBook.with(dbName) }

	init {
		RxPaperBook.init(context)
	}

	fun <T> read(key: String, timeInMillis: Long? = null): Observable<T> {
		return db.read<CacheData<T>>(key)
			.map { storage ->
				timeInMillis?.let {
					val presentTime = System.currentTimeMillis()
					val dataLifeTime = storage.timeStamp + timeInMillis
					if (dataLifeTime < presentTime) {
						Timber.d("Cache expired")
						throw CacheExpiredException()
					}
				}

				Timber.d("Cache available")
				storage.data
			}.toObservable()
	}

	fun <T> write(key: String, data: T): Completable {
		return db.write(key, CacheData(data))
	}

	fun delete(key: String): Completable {
		return db.delete(key)
	}
}