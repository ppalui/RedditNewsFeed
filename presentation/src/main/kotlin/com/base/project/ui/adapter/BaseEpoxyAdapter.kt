package com.kingpower.member.ui.adapter.base

import com.airbnb.epoxy.EpoxyAdapter
import com.airbnb.epoxy.EpoxyModel
import com.kingpower.member.model.epoxy.EmptyModel_
import com.kingpower.member.model.epoxy.ErrorModel_
import com.kingpower.member.model.epoxy.LoadingModel_

abstract class BaseEpoxyAdapter : EpoxyAdapter() {
    open protected val errorModel: EpoxyModel<*> = ErrorModel_()
    open protected val emptyModel: EpoxyModel<*> = EmptyModel_()
    open protected val loadingModel: EpoxyModel<*> = LoadingModel_()

    protected abstract fun clearItemList()

    fun loading() {
        clearItemList()
        addModel(loadingModel)
    }

    fun error() {
        clearItemList()
        addModel(errorModel)
    }

    fun empty() {
        clearItemList()
        addModel(emptyModel)
    }
}