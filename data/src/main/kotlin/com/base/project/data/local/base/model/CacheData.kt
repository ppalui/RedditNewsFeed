package com.base.project.data.local.base.model

class CacheData<out T>(val data: T) {
	var timeStamp: Long = System.currentTimeMillis()
}