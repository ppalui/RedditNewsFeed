package com.base.project.mockdata

import com.base.project.domain.model.PixabayImage

class MockPixabayImage {
	companion object {
		val PIXABAY_IMAGE by lazy { getPixabayImage() }

		val PIXABAY_IMAGE_TOTAL = 30
		val PIXABAY_IMAGE_IMAGES = listOf(MockPixabayImageSource.PIXABAY_IMAGE_SOURCE)

		private fun getPixabayImage(): PixabayImage {
			return PixabayImage(
				total = PIXABAY_IMAGE_TOTAL,
				images = PIXABAY_IMAGE_IMAGES)
		}
	}
}