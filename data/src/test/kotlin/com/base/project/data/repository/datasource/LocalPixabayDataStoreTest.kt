package com.base.project.data.repository.datasource

import com.base.project.data.local.base.PixabayCache
import com.base.project.data.repository.datasource.pixabay.LocalPixabayDataStore
import com.base.project.data.utils.EntityUtil
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import org.jetbrains.spek.api.SubjectSpek
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.junit.platform.runner.JUnitPlatform
import org.junit.runner.RunWith
import org.mockito.Mockito.verify

@RunWith(JUnitPlatform::class)
class LocalPixabayDataStoreTest : SubjectSpek<LocalPixabayDataStore>({

	val mockPixabayCache: PixabayCache = mock()

	subject { LocalPixabayDataStore(mockPixabayCache) }

	given("call getPixabayImageEntityByKeyword with specified params") {

		/*
		* This test only figure that PixabayCache.getPixabayImage is called in LocalPixabayDataStore.getPixabayImageEntityByKeyword method
		* because we still not implement Cache result yet.
		*
		* Assume that this case passed
		*/

		val fakeObservable = EntityUtil.createFakePixabayEntityObservable()
		whenever(mockPixabayCache.getPixabayImage()).thenReturn(fakeObservable)

		subject.getPixabayImageEntityByKeyword(EntityUtil.FAKE_KEYWORD_ID, EntityUtil.FAKE_PAGE, EntityUtil.FAKE_LIMIT).subscribe()


		it("should call `CACHE`.getPixabayImage from specified params") {
			verify(mockPixabayCache).getPixabayImage()
		}
	}
})

