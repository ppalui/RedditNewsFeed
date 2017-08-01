package com.base.project.data.entity.mapper

import com.base.project.data.utils.EntityUtil
import com.base.project.domain.model.PixabayImage
import com.base.project.domain.model.PixabayImageSource
import org.jetbrains.spek.api.SubjectSpek
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.junit.platform.runner.JUnitPlatform
import org.junit.runner.RunWith
import org.mockito.BDDMockito.given
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@RunWith(JUnitPlatform::class)
class PixabayImageDataMapperTest : SubjectSpek<PixabayImageEntityDataMapper>({

	subject { PixabayImageEntityDataMapper() }

	given("fake PixabayImageEntity data") {

		it("should return PixabayImage") {
			val pixabayEntity = EntityUtil.createFakePixabayEntity()
			val pixabay = subject.toPixabayImage(pixabayEntity)

			assertTrue(pixabay is PixabayImage)
			assertEquals(EntityUtil.FAKE_TOTAL, pixabay!!.total)
			assertEquals(1, pixabay.images.size)
			assertTrue(pixabay.images[0] is PixabayImageSource)
			assertEquals(EntityUtil.FAKE_PREVIEW_URL, pixabay.images[0].previewURL)
		}
	}
})
