package com.base.project.data.local.base

import com.base.project.data.entity.PixabayImageEntity
import io.reactivex.Observable

interface PixabayCache {
	fun getPixabayImage(): Observable<PixabayImageEntity>
	fun insertOrUpdatePixabayImage(pixabayImage: PixabayImageEntity)
}