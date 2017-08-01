package com.base.project.data.net

import com.base.project.data.net.api.PixabayRestApi
import com.base.project.data.net.api.base.ApiConnectionImpl
import org.jetbrains.spek.api.SubjectSpek
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.junit.platform.runner.JUnitPlatform
import org.junit.runner.RunWith
import org.mockito.BDDMockito.given
import kotlin.test.assertTrue

@RunWith(JUnitPlatform::class)
class ApiConnectionTest : SubjectSpek<ApiConnection>({
	subject { ApiConnectionImpl() }

	given("calling createPixabayRestApi") {

		it("should return PixabayRestApi") {
			val api = subject.createPixabayRestApi()
			assertTrue(api is PixabayRestApi)
		}
	}
})

