package com.base.project.data.entity.mapper

import com.base.project.data.entity.RedditComment.Child
import com.base.project.data.entity.RedditComment.RedditWrapper

import com.base.project.domain.model.RedditComment
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RedditCommentEntityMapper @Inject constructor() {

	fun toRedditCommentList(redditWrapper: RedditWrapper): List<RedditComment> {
		val children = redditWrapper.data.children
		val redditCommentList = mutableListOf<RedditComment>()

		children.forEach {
			child: Child ->
			if (!child.kind.equals("more")) {
				val commentEntity = child.data

				val redditComment = RedditComment(
					id = commentEntity.id,
					author = commentEntity.author,
					message = commentEntity.message,
					createdTime = commentEntity.createdTime
				)

				redditCommentList.add(redditComment)
			}
		}

		return redditCommentList
	}

}