package danilchanka.aliaksandr.imageview.view

interface ImageViewView {
    fun showNormal()

    fun showLoading()

    fun validateFields(): Boolean

    fun addValidatingTextWatchers()
}