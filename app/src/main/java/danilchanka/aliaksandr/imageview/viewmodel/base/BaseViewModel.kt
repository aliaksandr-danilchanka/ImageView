package danilchanka.aliaksandr.imageview.viewmodel.base

import danilchanka.aliaksandr.imageview.view.base.BaseView

interface BaseViewModel<V : BaseView> {

    fun attachView(view: V)

    fun detachView()
}