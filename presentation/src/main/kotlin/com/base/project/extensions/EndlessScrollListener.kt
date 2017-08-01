package com.base.project.extensions

import android.os.Handler
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager


internal class EndlessScrollListener(private val refreshList: RefreshList) : RecyclerView.OnScrollListener() {
	private var isLoading: Boolean = false
	private var hasMorePages: Boolean = false
	private var pageNumber = 0
	private var isRefreshing: Boolean = false
	private var pastVisibleItems: Int = 0

	init {
		this.isLoading = false
		this.hasMorePages = true
	}

	override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
		super.onScrolled(recyclerView, dx, dy)
		val manager = recyclerView!!.layoutManager as StaggeredGridLayoutManager

		val visibleItemCount = manager.childCount
		val totalItemCount = manager.itemCount
		val firstVisibleItems = manager.findFirstVisibleItemPositions(null)
		if (firstVisibleItems != null && firstVisibleItems.isNotEmpty()) {
			pastVisibleItems = firstVisibleItems[0]
		}

		if (visibleItemCount + pastVisibleItems >= totalItemCount && !isLoading) {
			isLoading = true
			if (hasMorePages && !isRefreshing) {
				isRefreshing = true
				Handler().postDelayed({ refreshList.onRefresh(pageNumber) }, 200)
			}
		}
		else {
			isLoading = false
		}
	}

	fun noMorePages() {
		this.hasMorePages = false
	}

	fun notifyMorePages() {
		isRefreshing = false
		pageNumber++
	}

	internal interface RefreshList {
		fun onRefresh(pageNumber: Int)
	}
}