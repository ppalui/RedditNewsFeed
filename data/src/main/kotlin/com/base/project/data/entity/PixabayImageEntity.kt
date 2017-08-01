package com.base.project.data.entity

import com.google.gson.annotations.SerializedName

data class PixabayImageEntity(val total: Int,
							  @SerializedName("hits") val images: List<PixabayImageSourceEntity>)