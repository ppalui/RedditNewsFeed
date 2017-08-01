package com.base.project.data.repository.datasource

import com.base.project.data.local.base.PixabayCache
import com.base.project.data.net.api.PixabayRestApi
import com.base.project.data.repository.datasource.pixabay.CloudPixabayDataStore
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
class CloudPixabayDataStoreTest : SubjectSpek<CloudPixabayDataStore>({

	val mockPixabayRestApi: PixabayRestApi = mock()
	val mockPixabayCache: PixabayCache = mock()

	subject { CloudPixabayDataStore(mockPixabayRestApi, mockPixabayCache) }

	given("call getPixabayImageEntityByKeyword with specified params") {

		val fakeObservable = EntityUtil.createFakePixabayEntityObservable()
		whenever(mockPixabayRestApi.listImageByKeyword(keyword = EntityUtil.FAKE_KEYWORD_ID, page = EntityUtil.FAKE_PAGE, per_page = EntityUtil.FAKE_LIMIT))
			.thenReturn(fakeObservable)

		subject.getPixabayImageEntityByKeyword(EntityUtil.FAKE_KEYWORD_ID, EntityUtil.FAKE_PAGE, EntityUtil.FAKE_LIMIT).subscribe()

		it("should call `REST API`.listImageByKeyword from specified params") {
			verify(mockPixabayRestApi).listImageByKeyword(keyword = EntityUtil.FAKE_KEYWORD_ID, page = EntityUtil.FAKE_PAGE, per_page = EntityUtil.FAKE_LIMIT)
		}
	}
})
