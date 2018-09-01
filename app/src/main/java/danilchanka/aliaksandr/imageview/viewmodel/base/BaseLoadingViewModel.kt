package danilchanka.aliaksandr.imageview.viewmodel.base

import android.arch.lifecycle.ViewModel
import danilchanka.aliaksandr.imageview.view.base.BaseView
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseLoadingViewModel<V : BaseView> : ViewModel(), BaseViewModel<V> {

    private var mView: V? = null
    private val mCompositeDisposable = CompositeDisposable()

    override fun onCleared() {
        super.onCleared()
        mCompositeDisposable.dispose()
    }

    override fun attachView(view: V) {
        mView = view
    }

    override fun detachView() {
        mView = null
    }

    fun isViewAttached(): Boolean {
        return mView != null
    }

    protected fun addSubscription(disposable: Disposable) {
        mCompositeDisposable.add(disposable)
    }

    fun getView(): V {
        return mView!!
    }

}