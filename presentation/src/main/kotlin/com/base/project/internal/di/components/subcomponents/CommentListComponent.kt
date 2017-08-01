package com.base.project.internal.di.components.subcomponents

import com.base.project.internal.di.PerFragment
import com.base.project.ui.fragment.CommentListFragment
import dagger.Subcomponent

@PerFragment
@Subcomponent(modules = arrayOf())
interface CommentListComponent {
	fun inject(commentListFragment: CommentListFragment)
}