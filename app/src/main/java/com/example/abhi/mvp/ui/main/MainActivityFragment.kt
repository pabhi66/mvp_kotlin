package com.example.abhi.mvp.ui.main

import android.support.v4.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.abhi.mvp.R
import com.example.abhi.mvp.data.response.Post
import com.example.abhi.mvp.injection.components.FragmentComponent
import com.example.abhi.mvp.ui.base.BaseFragment
import javax.inject.Inject

/**
 * A placeholder fragment containing a simple view.
 */
class MainActivityFragment : BaseFragment(), MainContract.View {

    @Inject lateinit var mainPresenter: MainPresenter

    override val layout: Int = R.layout.fragment_main

    override fun inject(fragmentComponent: FragmentComponent) {
        fragmentComponent.inject(this)
    }

    override fun attachView() {
        mainPresenter.attachView(this)
    }

    override fun detachPresenter() {
        mainPresenter.detachView()
    }

    override fun showPosts(posts: List<Post>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showProgress(show: Boolean) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showError(error: Throwable) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun postClicked() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}
