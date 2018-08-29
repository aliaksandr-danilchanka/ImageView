package danilchanka.aliaksandr.imageview.viewmodel

import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import android.graphics.Bitmap
import android.view.View
import danilchanka.aliaksandr.imageview.api.RestHelper
import danilchanka.aliaksandr.imageview.util.Utils
import danilchanka.aliaksandr.imageview.view.ImageViewView
import danilchanka.aliaksandr.imageview.viewmodel.base.BaseLoadingViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class ImageViewViewModel : BaseLoadingViewModel<ImageViewView>() {

    var login: String = "danilchanka"
    var password: String = "aliaksandr"
    var isLoading = ObservableBoolean(false)
    var imageBitmap = ObservableField<Bitmap>()

    var isError: Boolean = false
    var isConnectionError: Boolean = false

    override fun attachView(view: ImageViewView) {
        super.attachView(view)
        showViewErrors()
    }

    fun onSubmitClick(view: View) {
        isLoading.set(true)
        addSubscription(
                RestHelper.getRestInterface()
                        .getImageBase64Encoded(login, Utils.stringToSha1(password))
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe({ result ->
                            imageBitmap.set(Utils.stringBase64ToBitmap(result.image))
                            isLoading.set(false)
                        }) { error ->
                            if (error.localizedMessage == Utils.HTTP_ERROR_MESSAGE) isError = true
                            else isConnectionError = true
                            isLoading.set(false)
                            showViewErrors()
                        }
        )
    }

    fun changeLogin(s: CharSequence) {
        login = s.toString()
    }

    fun changePassword(s: CharSequence) {
        password = s.toString()
    }

    private fun showViewErrors() {
        if (isViewAttached()) {
            if (isError) {
                getListener().onError()
                isError = false
            }
            if (isConnectionError) {
                getListener().onErrorConnection()
                isConnectionError = false
            }
        }
    }
}