package com.base.project.presenter

import android.content.Context
import com.base.project.domain.interactor.GetPixabayImageByKeyword
import com.base.project.mapper.PixabayImageModelDataMapper
import com.base.project.view.HomeView
import com.navik.data.exception.retrofit.DataErrorBundle
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.jetbrains.spek.api.SubjectSpek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.junit.platform.runner.JUnitPlatform
import org.junit.runner.RunWith
import org.mockito.BDDMockito.given
import org.opentest4j.TestSkippedException

@RunWith(JUnitPlatform::class)
class HomePresenterTest : SubjectSpek<HomePresenter>({

	val mockGetPixabayImageByKeyword: GetPixabayImageByKeyword = mock()
	val mockPixabayImageModelDataMapper: PixabayImageModelDataMapper = mock()
	val mockContext: Context = mock()
	val mockView : HomeView = mock()

	subject { HomePresenter(mockGetPixabayImageByKeyword, mockPixabayImageModelDataMapper) }

	subject.setView(mockView)
	given(mockView.context()).willReturn(mockContext)

	describe("call presenter method") {

		given("call loadImage") {

			val fakeKeyword = "Test Search"

			subject.loadImage(fakeKeyword)

			it("should call view.showLoading") {
				verify(mockView).showLoading()
			}

			it("should call getPixabayImageByKeyword.execute") {
				verify(mockGetPixabayImageByKeyword).execute(any(), any())
			}
		}

		given("call shoErrorMessage") {
			val errorBundle = DataErrorBundle(TestSkippedException())
			subject.showErrorMessage(errorBundle)

			it("should call view.showError") {
				verify(mockView).showError(any())
			}
		}
	}
})