package danilchanka.aliaksandr.imageview.fragment.binder

import android.databinding.BindingAdapter
import android.graphics.Bitmap
import android.view.View
import android.widget.ImageView


object Binder {

    @BindingAdapter("hide")
    @JvmStatic
    fun setHide(view: View, hide: Boolean) {
        view.visibility = if (hide) View.GONE else View.VISIBLE
    }

    @BindingAdapter("show")
    @JvmStatic
    fun setShow(view: View, show: Boolean) {
        view.visibility = if (show) View.VISIBLE else View.GONE
    }

    @BindingAdapter("bitmapImage")
    @JvmStatic
    fun setBitmapImage(imageView: ImageView, bitmap: Bitmap?) {
        if (bitmap != null) imageView.setImageBitmap(bitmap)
    }
}