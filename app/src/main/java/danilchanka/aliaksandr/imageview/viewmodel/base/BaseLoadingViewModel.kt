package danilchanka.aliaksandr.imageview.viewmodel.base

import android.arch.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseLoadingViewModel : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

    protected fun addSubscription(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }
}