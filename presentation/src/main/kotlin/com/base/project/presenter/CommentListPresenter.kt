package com.base.project.presenter

import com.base.project.domain.interactor.GetRedditCommentByNewsId
import com.base.project.domain.model.RedditComment
import com.base.project.domain.params.ListRedditCommentParams
import com.base.project.internal.di.PerFragment
import com.base.project.mapper.RedditCommentModelMapper
import com.base.project.model.RedditCommentModel
import com.base.project.view.CommentListView
import com.navik.domain.interactor.DefaultObserver
import com.navik.presentation.presenter.Presenter
import javax.inject.Inject

@PerFragment
class CommentListPresenter @Inject constructor(private val getRedditCommentByNewsId: GetRedditCommentByNewsId,
                                               private val redditCommentModelMapper: RedditCommentModelMapper)
	: Presenter<CommentListView>() {

	override fun destroy() {
		super.destroy()
		getRedditCommentByNewsId.dispose()
	}

	fun loadComment(newsId: String) {
		view?.showLoading()
		val params = ListRedditCommentParams(newsId)
		getRedditCommentByNewsId.execute(GetCommentListObserver(), params)
	}

	private inner class GetCommentListObserver : DefaultObserver<List<RedditComment>>()  {
		override fun onComplete() {
			view?.hideLoading()
		}

		override fun onError(e: Throwable) {
			super.onError(e)
		}

		override fun onNext(t: List<RedditComment>) {
			val commentModelList = mutableListOf<RedditCommentModel>()
			t.forEach {
				commentModelList.add(redditCommentModelMapper.toRedditcommentModel(it))
			}
			view?.displayCommentList(commentModelList)
		}
	}
}