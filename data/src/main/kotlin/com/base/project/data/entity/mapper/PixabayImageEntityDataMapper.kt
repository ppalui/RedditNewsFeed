package com.base.project.data.entity.mapper

import com.base.project.data.entity.PixabayImageEntity
import com.base.project.data.entity.PixabayImageSourceEntity
import com.base.project.domain.model.PixabayImage
import com.base.project.domain.model.PixabayImageSource
import javax.inject.Inject
import javax.inject.Singleton


/**
 * Mapper class used to toPixabayImage [PixabayImageEntity] (in the data layer) to [PixabayImage] in the
 * domain layer.
 */
@Singleton
open class PixabayImageEntityDataMapper @Inject constructor() {
	open fun toPixabayImage(entity: PixabayImageEntity?): PixabayImage? {
		return entity?.let {
			PixabayImage(total = it.total, images = toPixabayImageSourceList(it.images))
		}
	}

	private fun toPixabayImageSourceList(entityCollection: List<PixabayImageSourceEntity>): List<PixabayImageSource> {
		return entityCollection.mapNotNull { toPixabayImageSource(it) }
	}

	private fun toPixabayImageSource(entity: PixabayImageSourceEntity): PixabayImageSource {
		return PixabayImageSource(previewURL = entity.previewURL,
			previewHeight = entity.previewHeight,
			likes = entity.likes,
			favorites = entity.favorites,
			tags = entity.tags,
			webformatHeight = entity.webformatHeight,
			views = entity.views,
			webformatWidth = entity.webformatWidth,
			previewWidth = entity.previewWidth,
			comments = entity.comments,
			downloads = entity.downloads,
			pageURL = entity.pageURL,
			webformatURL = entity.webformatURL,
			imageWidth = entity.imageWidth,
			userId = entity.userId,
			user = entity.user,
			type = entity.type,
			id = entity.id,
			userImageURL = entity.userImageURL,
			imageHeight = entity.imageHeight
		)
	}
}