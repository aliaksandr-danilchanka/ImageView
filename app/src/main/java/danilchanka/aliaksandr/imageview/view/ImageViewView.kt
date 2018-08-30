package danilchanka.aliaksandr.imageview.view

import danilchanka.aliaksandr.imageview.view.base.BaseView

interface ImageViewView : BaseView {

    fun onError()

    fun onErrorConnection()
}