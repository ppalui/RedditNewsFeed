package com.base.project.data.repository.datasource

import com.base.project.data.local.PixabayCacheImpl
import com.base.project.data.net.ApiConnection
import com.base.project.data.net.api.PixabayRestApi
import com.base.project.data.repository.datasource.pixabay.CloudPixabayDataStore
import com.base.project.data.repository.datasource.pixabay.LocalPixabayDataStore
import com.base.project.data.repository.datasource.pixabay.PixabayDataStoreFactory
import com.base.project.data.repository.datasource.pixabay.SyncPixabayDataStore
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import org.jetbrains.spek.api.SubjectSpek
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.junit.platform.runner.JUnitPlatform
import org.junit.runner.RunWith
import kotlin.test.assertTrue

@RunWith(JUnitPlatform::class)
class PixabayDataStoreFactoryTest : SubjectSpek<PixabayDataStoreFactory>({

	val mockPixabayCache: PixabayCacheImpl = mock()
	val mockApiConnection: ApiConnection = mock()
	val mockPixabayRestAPI: PixabayRestApi = mock()

	subject { PixabayDataStoreFactory(mockPixabayCache, mockApiConnection) }
	whenever(mockApiConnection.createPixabayRestApi()).thenReturn(mockPixabayRestAPI)

	given("call createSync") {
		val pixabayDataStore = subject.createSync()

		it("should return instance of SyncPixabayDataStore") {
			assertTrue(pixabayDataStore is SyncPixabayDataStore)
		}
	}

	given("call createCloud") {
		val pixabayDataStore = subject.createCloud()

		it("should return instance of CloudPixabayDataStore") {
			assertTrue(pixabayDataStore is CloudPixabayDataStore)
		}
	}

	given("call createLocal") {
		val pixabayDataStore = subject.createLocal()

		it("should return instance of LocalPixabayDataStore") {
			assertTrue(pixabayDataStore is LocalPixabayDataStore)
		}
	}
})

