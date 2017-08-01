package com.base.project.internal.di.components.subcomponents

import com.base.project.internal.di.PerFragment
import com.base.project.ui.fragment.HomeFragment
import dagger.Subcomponent

/**
 * A scope [PerFragment] component.
 * Injects user specific Fragments.
 */
@PerFragment
@Subcomponent(modules = arrayOf())
interface HomeComponent {
	fun inject(homeFragment: HomeFragment)
}