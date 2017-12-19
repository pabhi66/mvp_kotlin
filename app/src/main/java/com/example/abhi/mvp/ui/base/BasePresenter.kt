package com.example.abhi.mvp.ui.base

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable


/**
 * @author Abhishek Prajapati
 * @version 1.0.0
 * @since 12/18/17.
 * Base class that implements the Presenter interface and provides a base implementation for
 * attachView() and detachView(). It also handles keeping a reference to the mvpView that
 * can be accessed from the children classes by calling getMvpView().
 *
 */
open class BasePresenter<V : BaseView> : Presenter<V> {

    private var view: V? = null

    private val compositeDisposable = CompositeDisposable()

    override fun getView(): V? {
        return view
    }


    override val isViewAttached: Boolean
        get() = view != null

    override fun attachView(view: V) {
        this.view = view
    }

    override fun detachView() {
        view = null
        if (!compositeDisposable.isDisposed) {
            compositeDisposable.clear()
        }
    }

    /**
     * Check view attached.
     */
    protected fun checkViewAttached() {
        if (!isViewAttached) {
            throw ViewNotAttachedException()
        }
    }

    /**
     * Add disposable.
     *
     * @param disposable the disposable
     */
    fun addDisposable(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    private class ViewNotAttachedException
    /**
     * Instantiates a new View not attached exception.
     */
    internal constructor() : RuntimeException("Please call Presenter.attachView(BaseView) before" + " requesting data to the Presenter")
}



