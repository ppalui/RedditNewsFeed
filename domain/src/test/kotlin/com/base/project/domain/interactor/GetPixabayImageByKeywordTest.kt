package com.base.project.domain.interactor

import com.base.project.domain.params.ListPixabayImageParams
import com.base.project.domain.repository.PixabayRepository
import com.base.project.domain.repository.SourceType
import com.base.project.domain.utils.InteractorUtil
import com.navik.domain.executor.PostExecutionThread
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import io.reactivex.Scheduler
import io.reactivex.schedulers.TestScheduler
import org.jetbrains.spek.api.SubjectSpek
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.junit.platform.runner.JUnitPlatform
import org.junit.runner.RunWith

@RunWith(JUnitPlatform::class)
class GetPixabayImageByKeywordTest : SubjectSpek<GetPixabayImageByKeyword>({

	val mockPixabayRepository: PixabayRepository = mock()
	val mockThreadExecutor: Scheduler = TestScheduler()
	val mockPostExecutionThread: PostExecutionThread = mock()

	subject { GetPixabayImageByKeyword(mockPixabayRepository, mockThreadExecutor, mockPostExecutionThread) }

	given("call buildUseCaseObservable with specified params") {
		subject.buildUseCaseObservable(ListPixabayImageParams(page = InteractorUtil.FAKE_PAGE, limit = InteractorUtil.FAKE_LIMIT))

		/* Keyword and SourceType use default value so it should call with empty string and CLOUD Type */
		it("should call getImageByKeyword from repository with specified params") {
			verify(mockPixabayRepository).getImageByKeyword(keyword = "", page = InteractorUtil.FAKE_PAGE, limit = InteractorUtil.FAKE_LIMIT, sourceType = SourceType.CLOUD)
		}
	}
})