package com.base.project.presenter

import com.base.project.domain.interactor.GetGroupRedditNewsList
import com.base.project.domain.model.GroupRedditNewsList
import com.base.project.domain.params.GroupRedditNewsListParams
import com.base.project.exception.ErrorMessageFactory
import com.base.project.internal.di.PerFragment
import com.base.project.mapper.GroupRedditNewsListModelDataMapper
import com.base.project.view.HomeView
import com.navik.data.exception.retrofit.DataErrorBundle
import com.navik.domain.exception.ErrorBundle
import com.navik.domain.interactor.DefaultObserver
import com.navik.presentation.presenter.Presenter
import javax.inject.Inject

@PerFragment
class HomePresenter
@Inject constructor(private val mGetRedditNewsUseCase: GetGroupRedditNewsList,
                    private val mDataMapper: GroupRedditNewsListModelDataMapper) : Presenter<HomeView>() {

	private var AFTER_NEWS = ""

	override fun destroy() {
		super.destroy()
		mGetRedditNewsUseCase.dispose()
	}

	fun loadRedditNewsList(after: String = "", limit: Int = 30) {
		view?.showLoading()
		val loadRedditNewsParams = GroupRedditNewsListParams(after, limit)
		mGetRedditNewsUseCase.execute(GetRedditNewsSubscriber(), loadRedditNewsParams)
	}

	fun loadMoreRedditNewsList(after: String = AFTER_NEWS, limit: Int = 30) {
		view?.showLoading()
		val loadRedditNewsParams = GroupRedditNewsListParams(after, limit)
		mGetRedditNewsUseCase.execute(LoadMoreRedditNewsSubscriber(), loadRedditNewsParams)
	}

	fun showErrorMessage(errorBundle: ErrorBundle) {
		val errorMessage = ErrorMessageFactory.create(view?.context(), errorBundle.exception())
		view?.showError(errorMessage)
	}

	private inner class GetRedditNewsSubscriber : DefaultObserver<GroupRedditNewsList>() {
		override fun onComplete() {
			view?.hideLoading()
		}

		override fun onError(e: Throwable) {
			view?.hideLoading()
			val errorBundle = DataErrorBundle(e as Exception)
			showErrorMessage(errorBundle)
		}

		override fun onNext(t: GroupRedditNewsList) {
			val redditNewsList = mDataMapper.toGroupRedditNewsList(t)
			redditNewsList?.let {
				it.groupRedditNewsList?.let { redditNews ->
					AFTER_NEWS = redditNews.after
					view?.displayRedditNewsFeed(redditNews)
				}
			}
		}

	}

	private inner class LoadMoreRedditNewsSubscriber : DefaultObserver<GroupRedditNewsList>() {
		override fun onComplete() {
			view?.hideLoading()
		}

		override fun onError(e: Throwable) {
			view?.hideLoading()
			val errorBundle = DataErrorBundle(e as Exception)
			showErrorMessage(errorBundle)
		}

		override fun onNext(t: GroupRedditNewsList) {
			val redditNewsList = mDataMapper.toGroupRedditNewsList(t)
			redditNewsList?.let {
				it.groupRedditNewsList?.let { redditNews ->
					AFTER_NEWS = redditNews.after
					view?.appendRedditNewsFeed(redditNews)
				}
			}
		}

	}
}