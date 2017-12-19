package com.example.abhi.mvp.ui.main

import com.example.abhi.mvp.ui.base.BasePresenter
import com.example.abhi.mvp.utils.rx.scheduler.SchedulerUtils
import com.example.abhi.mvp.data.DataManager
import com.example.abhi.mvp.data.response.Post
import com.example.abhi.mvp.injection.scopes.ConfigPersistent
import javax.inject.Inject


/**
 * @author Abhishek Prajapati
 * @version 1.0.0
 * @since 12/19/17.
 */

@ConfigPersistent
class MainPresenter @Inject
constructor(private val dataManager: DataManager) : BasePresenter<MainContract.View>(), MainContract.Presenter {

    override fun getPosts() {
        checkViewAttached()
        getView()?.showProgress(true)

        dataManager.getPosts()
                .compose<List<Post>>(SchedulerUtils.ioToMain())
                .subscribe({ posts ->
                    getView()?.apply {
                        showProgress(false)
                        showPosts(posts)
                    }
                }) { throwable ->
                    getView()?.apply {
                        showProgress(false)
                        showError(throwable)
                    }
                }
    }
}