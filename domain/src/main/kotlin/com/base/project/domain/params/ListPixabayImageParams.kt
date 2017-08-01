package com.base.project.domain.params

import com.base.project.domain.repository.SourceType

class ListPixabayImageParams constructor(var keyword: String? = null,
										 var page: Int = 1,
										 var limit: Int,
										 var sourceType: SourceType = SourceType.CLOUD)