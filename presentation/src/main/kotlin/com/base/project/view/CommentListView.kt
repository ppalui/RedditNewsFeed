package com.base.project.view

import com.base.project.model.RedditCommentModel
import com.navik.presentation.view.LoadDataView


interface CommentListView : LoadDataView {

	fun displayCommentList(commentList: List<RedditCommentModel>)
}