package com.example.abhi.mvp.ui.base

/**
 * @author Abhishek Prajapati
 * @version 1.0.0
 * @since 12/18/17.
 */
interface Presenter<V : BaseView> {

    /**
     * @return true if view is attached to presenter
     */
    val isViewAttached: Boolean

    /**
     * @return view
     */
    val view: V?

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