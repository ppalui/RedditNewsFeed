package com.base.project.internal.di.modules

import android.content.Context
import com.base.project.AndroidApplication
import com.base.project.UIThread
import com.base.project.data.local.PixabayCacheImpl
import com.base.project.data.local.PreferenceHelperImpl
import com.base.project.data.local.RedditNewsCacheImpl
import com.base.project.data.local.base.PixabayCache
import com.base.project.data.local.base.PreferenceHelper
import com.base.project.data.local.base.RedditNewsCache
import com.base.project.data.net.ApiConnection
import com.base.project.data.net.api.base.ApiConnectionImpl
import com.base.project.data.repository.PixabayDataRepository
import com.base.project.data.repository.RedditCommentDataRepository
import com.base.project.data.repository.RedditNewsDataRepository
import com.base.project.domain.repository.PixabayRepository
import com.base.project.domain.repository.RedditCommentRepository
import com.base.project.domain.repository.RedditNewsRepository
import com.navik.domain.executor.PostExecutionThread
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import javax.inject.Singleton

/**
 * Dagger module that provides objects which will live during the application lifecycle.
 */
@Module
class ApplicationModule(private val application: AndroidApplication) {

	@Provides @Singleton internal fun provideApplicationContext(): Context {
		return this.application
	}

	@Provides @Singleton internal fun provideSchedulersIO(): Scheduler {
		return Schedulers.io()
	}

	@Provides @Singleton internal fun providePostExecutionThread(uiThread: UIThread): PostExecutionThread {
		return uiThread
	}

	@Provides @Singleton internal fun provideApiConnection(apiConnection: ApiConnectionImpl): ApiConnection {
		return apiConnection
	}

	@Provides @Singleton internal fun providePixabayCache(pixabayCache: PixabayCacheImpl): PixabayCache {
		return pixabayCache
	}

	@Provides @Singleton internal fun provideRedditNewsCache(redditNewsCache: RedditNewsCacheImpl): RedditNewsCache {
		return redditNewsCache
	}

	@Provides @Singleton internal fun providePreferenceHelper(preferenceHelper: PreferenceHelperImpl): PreferenceHelper {
		return preferenceHelper
	}

	@Provides @Singleton internal fun providePixabayRepository(pixabayDataRepository: PixabayDataRepository): PixabayRepository {
		return pixabayDataRepository
	}

	@Provides @Singleton internal fun provideRedditNewsRepository(redditNewsDataRepository: RedditNewsDataRepository): RedditNewsRepository {
		return redditNewsDataRepository
	}

	@Provides @Singleton internal fun provideRedditCommentRepository(redditCommentDataRepository: RedditCommentDataRepository): RedditCommentRepository {
		return redditCommentDataRepository
	}

}