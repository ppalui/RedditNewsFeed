package com.base.project.internal.di

/**
 * Interface representing a contract for clients that contains a component for dependency injection.
 */
interface HasComponent<out C> {
	fun component(): C
}