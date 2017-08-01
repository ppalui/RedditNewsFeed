package com.base.project.mapper

import com.base.project.domain.model.RedditComment
import com.base.project.internal.di.PerFragment
import com.base.project.model.RedditCommentModel
import javax.inject.Inject

@PerFragment
class RedditCommentModelMapper @Inject constructor() {

	fun toRedditcommentModel(redditComment: RedditComment): RedditCommentModel {
		return RedditCommentModel(
			redditComment.id,
			redditComment.author,
			redditComment.message,
			redditComment.createdTime)
	}

}
