package com.base.project.domain.params


data class ListRedditCommentParams (val newsId: String,
                                    val depth: Int = 1,
                                    val limit: Int = 100) {
}