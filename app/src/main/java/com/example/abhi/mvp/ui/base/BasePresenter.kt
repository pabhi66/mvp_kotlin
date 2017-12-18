package com.example.abhi.mvp.ui.base

import rx.subscriptions.CompositeSubscription
import rx.subscriptions.Subscriptions

/**
 * @author Abhishek Prajapati
 * @version 1.0.0
 * @since 12/18/17.
 */

open class BasePresenter<V : BaseView> : Presenter<V> {

    var baseView: V? = null
    private val compositeSubscription = CompositeSubscription()

    override fun attachView(view: V) {
        this.baseView = view
    }

    override fun detachView() {
        baseView = null
        if(!compositeSubscription.isUnsubscribed) {
            compositeSubscription.clear()
        }
    }

    override val isViewAttached: Boolean
        get() = baseView != null

    override val view: V?
        get() = baseView

    fun checkViewAttached() {
        if(!isViewAttached) throw ViewNotAttachedException()
    }

    fun addSubscription(subs: Subscriptions) {
        compositeSubscription.add(subs)
    }

    private fun CompositeSubscription.add(subs: Subscriptions) {}

    private class ViewNotAttachedException internal constructor() : RuntimeException("Please call Presenter.attachView(MvpView) before" + " requesting data to the Presenter")

}


