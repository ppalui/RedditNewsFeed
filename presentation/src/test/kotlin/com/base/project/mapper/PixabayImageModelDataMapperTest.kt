package com.base.project.mapper

import com.base.project.mockdata.MockPixabayImage
import com.base.project.model.PixabayImageModel
import com.base.project.model.PixabayImageSourceModel
import org.jetbrains.spek.api.SubjectSpek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.junit.platform.runner.JUnitPlatform
import org.junit.runner.RunWith
import kotlin.test.assertEquals
import kotlin.test.assertNull
import kotlin.test.assertTrue

@RunWith(JUnitPlatform::class)
class PixabayImageModelDataMapperTest : SubjectSpek<PixabayImageModelDataMapper>({
	subject { PixabayImageModelDataMapper() }

	describe("call toPixabayImageModel") {

		given("PixabayImage") {
			val fakePixabayImage = MockPixabayImage.PIXABAY_IMAGE
			val expectedPixabayImageModel =  subject.toPixabayImageModel(fakePixabayImage)!!

			it("should return PixabayImageModel") {
				assertTrue { expectedPixabayImageModel is PixabayImageModel }
			}

			it("should have correct fields and values") {
				assertEquals(fakePixabayImage.total, MockPixabayImage.PIXABAY_IMAGE_TOTAL)

				assertTrue { expectedPixabayImageModel.images is List<PixabayImageSourceModel> }
				assertEquals(expectedPixabayImageModel.images.size, MockPixabayImage.PIXABAY_IMAGE_IMAGES.size)
			}
		}

		given("null") {
			val expectedPixabayImageModel =  subject.toPixabayImageModel(null)

			it("should return null") {
				assertNull(expectedPixabayImageModel)
			}
		}
	}
})