package com.example.abhi.mvp.ui.main

import android.support.v4.app.Fragment
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import butterknife.BindView
import com.example.abhi.mvp.R
import com.example.abhi.mvp.data.response.Post
import com.example.abhi.mvp.injection.components.FragmentComponent
import com.example.abhi.mvp.ui.base.BaseFragment
import io.reactivex.disposables.Disposable
import javax.inject.Inject

/**
 * A placeholder fragment containing a simple view.
 */
class MainActivityFragment : BaseFragment(), MainContract.View {

    @Inject lateinit var postsAdapter: PostsAdapter
    @Inject lateinit var mainPresenter: MainPresenter

    override val layout: Int = R.layout.fragment_main

    @BindView(R.id.recycler_view)
    lateinit var recyclerView: RecyclerView

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = postsAdapter
        postClicked()
        mainPresenter.getPosts()
    }

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
        postsAdapter.setPosts(posts)
    }

    override fun showProgress(show: Boolean) {
    }

    override fun showError(error: Throwable) {
    }

    override fun postClicked() {
        val disposable = postsAdapter.postClick
                .subscribe({ post ->
                    Toast.makeText(context, post.title, Toast.LENGTH_SHORT).show()
                    // to start new activity
                    // startActivity(DetailActivity.getStartIntent(this, post)),
                }, { throwable -> Toast.makeText(context, throwable.toString(), Toast.LENGTH_SHORT).show() })
        mainPresenter.addDisposable(disposable)

    }


}
