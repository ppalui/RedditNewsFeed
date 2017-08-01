package com.base.project.extensions.allias

typealias NewsItemClickListener = (url: String?, title: String) -> Unit
typealias NewsItemCommentsClickListener = (newsId: String?) -> Unit
typealias NewsItemShareClickListener = (newsId: String?, permalink: String, title: String) -> Unit