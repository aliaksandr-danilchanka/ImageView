package danilchanka.aliaksandr.imageview.viewmodel

import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField

class ImageViewViewModel : ViewModel() {

    var login = ObservableField("")
    var password = ObservableField("")
}