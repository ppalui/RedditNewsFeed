package com.base.project.ui.fragment

import android.content.Context
import android.os.Bundle
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.View
import android.widget.Toast
import com.base.project.R
import com.base.project.extensions.EndlessScrollListener
import com.base.project.extensions.navigateShareLinkToOtherApp
import com.base.project.extensions.navigateToComments
import com.base.project.extensions.navigateToWebView
import com.base.project.model.RedditNewsListModel
import com.base.project.presenter.HomePresenter
import com.base.project.ui.adapter.RedditNewsFeedAdapter
import com.base.project.ui.fragment.base.BaseFragment
import com.base.project.view.HomeView
import com.navik.multiplestate.itemdecoration.GridSpacingItemDecoration
import kotlinx.android.synthetic.main.fragment_home.*
import timber.log.Timber
import javax.inject.Inject

class HomeFragment : BaseFragment(), HomeView {

	@Inject internal lateinit var mPresenter: HomePresenter

	private lateinit var mAdapter: RedditNewsFeedAdapter
	private lateinit var scrollListener: EndlessScrollListener

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		activityComponent().getHomeComponent().inject(this)
		setHasOptionsMenu(true)
	}

	override fun getContentViewId(): Int = R.layout.fragment_home

	override fun context(): Context {
		return activity.applicationContext
	}

	override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		mPresenter.setView(this)
		setupCartRecyclerView()
		mPresenter.loadRedditNewsList()
	}

	override fun onDestroyView() {
		super.onDestroyView()
		mPresenter.destroy()
	}

	override fun displayRedditNewsFeed(redditNewsList: RedditNewsListModel) {
		hideLoading()
		redditNewsList.let {
			mAdapter.setRedditNews(it.redditNews, context)
		}
	}

	override fun appendRedditNewsFeed(redditNewsList: RedditNewsListModel) {
		hideLoading()

		redditNewsList.redditNews.map {
			mAdapter.appendRedditNews(it, getString(R.string.comments,
				it?.redditNewData?.commentsAmount?.toString()),
				getString(R.string.author,
					it?.redditNewData?.author))
		}

	}

	override fun hideLoading() {
		Timber.e("Hide Loading")
	}

	override fun showLoading() {
		Timber.e("Show Loading")
	}

	override fun showError(message: String) {
		hideLoading()
		Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
	}

	private fun setupCartRecyclerView() {
		mAdapter = RedditNewsFeedAdapter()
		mAdapter.newsItemClickListener = { url, title -> url?.let { navigateToWebView(url, title) } }
		mAdapter.newsItemCommentsClickListener = { id -> id?.let { navigateToComments(id) } }
		mAdapter.newsItemShareClickListener = { id, permalink, title ->
			id?.let { navigateShareLinkToOtherApp(id, permalink, title) }
		}

		val layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

		scrollListener = EndlessScrollListener(object : EndlessScrollListener.RefreshList {
			override fun onRefresh(pageNumber: Int) {
				Timber.e("ON LOAD MORE")
				mPresenter.loadMoreRedditNewsList()
			}
		})

		recyclerViewHomeImageList.addOnScrollListener(scrollListener)
		recyclerViewHomeImageList.layoutManager = layoutManager
		recyclerViewHomeImageList.addItemDecoration(GridSpacingItemDecoration(context, R.dimen.grid_spacing_tiny, 2, true))
		recyclerViewHomeImageList.setHasFixedSize(true)
		recyclerViewHomeImageList.adapter = mAdapter
	}
}