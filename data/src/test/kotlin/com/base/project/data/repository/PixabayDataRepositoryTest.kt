package com.base.project.data.repository

import com.base.project.data.entity.mapper.PixabayImageEntityDataMapper
import com.base.project.data.repository.datasource.pixabay.PixabayDataStore
import com.base.project.data.repository.datasource.pixabay.PixabayDataStoreFactory
import com.base.project.data.utils.EntityUtil
import com.base.project.domain.model.PixabayImage
import com.base.project.domain.repository.SourceType
import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Observable
import io.reactivex.observers.TestObserver
import org.jetbrains.spek.api.SubjectSpek
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.junit.platform.runner.JUnitPlatform
import org.junit.runner.RunWith

@RunWith(JUnitPlatform::class)
class PixabayDataRepositoryTest : SubjectSpek<PixabayDataRepository>({

	val mockPixabayImageEntityDataMapper: PixabayImageEntityDataMapper = mock()
	val mockPixabayDataStore: PixabayDataStore = mock()

	val mockPixabayDataStoreFactory = mock<PixabayDataStoreFactory> {
		on { createCloud() } doReturn mockPixabayDataStore
		on { createLocal() } doReturn mockPixabayDataStore
		on { createSync() } doReturn mockPixabayDataStore
	}

	subject { PixabayDataRepository(mockPixabayDataStoreFactory, mockPixabayImageEntityDataMapper) }

	given("getting fake PixabayImage data from cloud") {

		val fakePixabayEntity = EntityUtil.createFakePixabayEntity()
		val fakePixabay = EntityUtil.createFakePixabay()

		whenever(mockPixabayDataStore.getPixabayImageEntityByKeyword(EntityUtil.FAKE_KEYWORD_ID, EntityUtil.FAKE_PAGE, EntityUtil.FAKE_LIMIT)).thenReturn(Observable.just
		(fakePixabayEntity))
		whenever(mockPixabayImageEntityDataMapper.toPixabayImage(fakePixabayEntity)).thenReturn(fakePixabay)

		val testObserver = TestObserver<PixabayImage>()
		subject.getImageByKeyword(EntityUtil.FAKE_KEYWORD_ID, EntityUtil.FAKE_PAGE, EntityUtil.FAKE_LIMIT, SourceType.CLOUD).subscribe(testObserver)

		it("should call createCloud in PixabayDataStoreFactory") {
			verify(mockPixabayDataStoreFactory).createCloud()
		}

		it("should call getPixabayImageEntityByKeyword in PixabayDataStore") {
			verify(mockPixabayDataStore).getPixabayImageEntityByKeyword(EntityUtil.FAKE_KEYWORD_ID, EntityUtil.FAKE_PAGE, EntityUtil.FAKE_LIMIT)
		}

		it("should return PixabayImage from stream") {
			testObserver.assertValue(fakePixabay)
		}

		it("should have no error") {
			testObserver.assertNoErrors()
		}
	}
})

