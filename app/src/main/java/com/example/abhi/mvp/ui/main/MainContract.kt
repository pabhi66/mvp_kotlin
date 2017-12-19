package com.example.abhi.mvp.ui.main

import com.example.abhi.mvp.ui.base.BaseView
import com.example.abhi.mvp.data.response.Post
import com.example.abhi.mvp.ui.base.Presenter


/**
 * @author Abhishek Prajapati
 * @version 1.0.0
 * @since 12/19/17.
 */
interface MainContract {

    interface View: BaseView {
        fun showPosts(posts: List<Post>)

        fun showProgress(show: Boolean)

        fun showError(error: Throwable)

        fun postClicked()
    }

    interface Presenter : com.example.abhi.mvp.ui.base.Presenter<View> {
        fun getPosts()
    }
}