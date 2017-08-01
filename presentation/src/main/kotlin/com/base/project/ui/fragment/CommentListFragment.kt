package com.base.project.ui.fragment

import android.content.Context
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.airbnb.epoxy.EpoxyAdapter
import com.base.project.R
import com.base.project.model.RedditCommentModel
import com.base.project.model.epoxy.redditcomment.RedditCommentEpoxyModel_
import com.base.project.presenter.CommentListPresenter
import com.base.project.ui.activity.CommentsActivity
import com.base.project.ui.fragment.base.BaseFragment
import com.base.project.view.CommentListView
import kotlinx.android.synthetic.main.fragment_comment.*
import javax.inject.Inject

class CommentListFragment : BaseFragment(), CommentListView {

	@Inject internal lateinit var mPresenter: CommentListPresenter
	lateinit var mAdapter: CommentAdapter

	override fun getContentViewId(): Int = R.layout.fragment_comment

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		activityComponent().getCommentListComponent().inject(this)
		setHasOptionsMenu(true)
	}

	override fun context(): Context {
		return context
	}

	override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		mPresenter.setView(this)
		val id = activity.intent.getStringExtra(CommentsActivity.EXTRA_ID)
		mPresenter.loadComment(id)
		setupRecyclerView()
	}

	override fun displayCommentList(commentList: List<RedditCommentModel>) {
		commentList.forEach {
			mAdapter.addRedditCommentModel(it)
		}
	}

	override fun hideLoading() {

	}

	override fun showError(message: String) {

	}

	override fun showLoading() {

	}

	fun setupRecyclerView() {
		mAdapter = CommentAdapter()
		recyclerViewCommentList.layoutManager = LinearLayoutManager(context)
		recyclerViewCommentList.adapter = mAdapter
	}

	class CommentAdapter : EpoxyAdapter() {

		fun addRedditCommentModel(redditCommentModel: RedditCommentModel) {
			addModel(RedditCommentEpoxyModel_()
				.model(redditCommentModel))
		}
	}
}