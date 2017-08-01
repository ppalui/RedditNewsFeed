package com.base.project.mapper

import com.base.project.domain.model.PixabayImage
import com.base.project.domain.model.PixabayImageSource
import com.base.project.internal.di.PerFragment
import com.base.project.model.PixabayImageModel
import com.base.project.model.PixabayImageSourceModel
import javax.inject.Inject

@PerFragment
open class PixabayImageModelDataMapper @Inject constructor() {

	open fun toPixabayImageModel(model: PixabayImage?): PixabayImageModel? {
		return model?.let {
			PixabayImageModel(total = it.total, images = toPixabayImageSourceModelList(it.images))
		}
	}

	private fun toPixabayImageSourceModelList(modelCollection: List<PixabayImageSource>): List<PixabayImageSourceModel> {
		return modelCollection.mapNotNull { toPixabayImageSourceModel(it) }
	}

	private fun toPixabayImageSourceModel(model: PixabayImageSource): PixabayImageSourceModel {
		return PixabayImageSourceModel(model.previewURL,
			previewHeight = model.previewHeight,
			likes = model.likes,
			favorites = model.favorites,
			tags = model.tags,
			webformatHeight = model.webformatHeight,
			views = model.views,
			webformatWidth = model.webformatWidth,
			previewWidth = model.previewWidth,
			comments = model.comments,
			downloads = model.downloads,
			pageURL = model.pageURL,
			webformatURL = model.webformatURL,
			imageWidth = model.imageWidth,
			userId = model.userId,
			user = model.user,
			type = model.type,
			id = model.id,
			userImageURL = model.userImageURL,
			imageHeight = model.imageHeight
		)
	}
}