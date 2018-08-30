package danilchanka.aliaksandr.imageview.viewmodel.base

import android.arch.lifecycle.ViewModel
import danilchanka.aliaksandr.imageview.view.base.BaseView
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseLoadingViewModel<T : BaseView> : ViewModel(), BaseViewModel<T> {

    private var mListener: T? = null
    private val mCompositeDisposable = CompositeDisposable()

    override fun onCleared() {
        super.onCleared()
        mCompositeDisposable.dispose()
    }

    override fun attachView(view: T) {
        mListener = view
    }

    override fun detachView() {
        mListener = null
    }

    fun isViewAttached(): Boolean {
        return mListener != null
    }

    protected fun addSubscription(disposable: Disposable) {
        mCompositeDisposable.add(disposable)
    }

    fun getListener(): T {
        return mListener!!
    }

}