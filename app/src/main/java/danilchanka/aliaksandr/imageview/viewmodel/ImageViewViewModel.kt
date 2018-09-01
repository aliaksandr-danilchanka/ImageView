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
import retrofit2.HttpException

class ImageViewViewModel : BaseLoadingViewModel<ImageViewView>() {

    var login: String = Utils.BASE_LOGIN
    var password: String = Utils.BASE_PASSWORD
    var isLoading = ObservableBoolean(false)
    var imageBitmap = ObservableField<Bitmap>()

    private var isError: Boolean = false
    private var isConnectionError: Boolean = false

    override fun attachView(view: ImageViewView) {
        super.attachView(view)
        showViewErrors()
    }

    fun onSubmitClick(view: View) {
        if (isViewAttached()) {
            getView().hideSoftKeyboard()
        }
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
                            if (error is HttpException && error.code() == 401) isError = true
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
                getView().onError()
                isError = false
            }
            if (isConnectionError) {
                getView().onErrorConnection()
                isConnectionError = false
            }
        }
    }
}