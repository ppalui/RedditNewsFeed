package com.base.project.data.entity.RedditComment

import com.google.gson.annotations.SerializedName


data class RedditCommentEntity(val id: String,
                               val author: String,
                               @SerializedName("body") val message: String,
                               @SerializedName("created") val createdTime: Long) {
}