package com.base.project.data.utils

import com.base.project.data.entity.PixabayImageEntity
import com.base.project.data.entity.PixabayImageSourceEntity
import com.base.project.domain.model.PixabayImage
import com.base.project.domain.model.PixabayImageSource
import io.reactivex.Observable

object EntityUtil {

	val FAKE_KEYWORD_ID = "Flower"
	val FAKE_PAGE = 1
	val FAKE_LIMIT = 20
	val FAKE_TOTAL = 10
	val FAKE_PREVIEW_URL = "URL"

	fun createFakePixabayEntity(): PixabayImageEntity {
		return PixabayImageEntity(FAKE_TOTAL, createFakePixabayImageSourceEntityList())
	}

	fun createFakePixabay(): PixabayImage {
		return PixabayImage(FAKE_TOTAL, createFakePixabayImageSourceList())
	}

	fun createFakePixabayEntityObservable(): Observable<PixabayImageEntity> {
		val fakePixabayEntity = PixabayImageEntity(FAKE_TOTAL, createFakePixabayImageSourceEntityList())

		val fakeObservable = Observable.just(fakePixabayEntity)
		return fakeObservable
	}

	private fun createFakePixabayImageSourceEntity(): PixabayImageSourceEntity {
		return PixabayImageSourceEntity(FAKE_PREVIEW_URL)
	}

	private fun createFakePixabayImageSourceEntityList(): List<PixabayImageSourceEntity> {
		return listOf(createFakePixabayImageSourceEntity())
	}

	private fun createFakePixabayImageSourceList(): List<PixabayImageSource> {
		return listOf()
	}
}