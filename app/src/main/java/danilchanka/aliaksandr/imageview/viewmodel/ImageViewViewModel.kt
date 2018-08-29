package danilchanka.aliaksandr.imageview.viewmodel

import android.arch.lifecycle.ViewModel
import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import android.graphics.Bitmap
import android.view.View
import danilchanka.aliaksandr.imageview.api.RestHelper
import danilchanka.aliaksandr.imageview.util.Utils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class ImageViewViewModel : ViewModel() {

    var login: String = "danilchanka"
    var password: String = "aliaksandr"
    var isLoading = ObservableBoolean(false)
    var imageBitmap = ObservableField<Bitmap>()

    fun onSubmitClick(view: View) {
        isLoading.set(true)
        val apiService = RestHelper.create()
        apiService.getImageBase64Encoded(login, Utils.stringToSha1(password))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({ result ->
                    imageBitmap.set(Utils.stringBase64ToBitmap(result.image))
                    isLoading.set(false)
                }, { error ->
                    error.printStackTrace()
                })
    }

    fun changeLogin(s: CharSequence){
        login = s.toString()
    }

    fun changePassword(s: CharSequence){
        password = s.toString()
    }
}