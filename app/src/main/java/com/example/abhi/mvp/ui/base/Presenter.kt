package com.example.abhi.mvp.ui.base

/**
 * @author Abhishek Prajapati
 * @version 1.0.0
 * @since 12/18/17.
 *
 * Every presenter in the app must either implement this interface or extend BasePresenter
 * indicating the MvpView type that wants to be attached with.
 */
interface Presenter<V : BaseView> {

    /**
     * @return true if view is attached to presenter
     */
    val isViewAttached: Boolean

    /**
     * @return view
     */
    fun getView(): V?

    /**
     * Called when view attached to presenter
     *
     * @param view
     */
    fun attachView(view: V)

    /**
     * Called when view is detached from presenter
     */
    fun detachView()
}
