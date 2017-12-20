package com.example.abhi.mvp.ui.main

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.example.abhi.mvp.R
import com.example.abhi.mvp.data.response.Post
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.Subject
import javax.inject.Inject

/**
 * @author Abhishek Prajapati
 * @version 1.0.0
 * @since 12/19/17.
 */
class PostsAdapter @Inject
internal constructor() : RecyclerView.Adapter<PostsAdapter.PostViewHolder>() {

    private var postsList: List<Post>? = null
    private val postClickSubject: Subject<Post>

    val postClick: Observable<Post>
        get() = postClickSubject

    init {
        postsList = emptyList()
        postClickSubject = PublishSubject.create()
    }

    fun setPosts(posts: List<Post>) {
        this.postsList = posts
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.posts_list, parent, false)
        return PostViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = this.postsList!![position]
        holder.onBind(post)
    }

    override fun getItemCount(): Int {
        return postsList!!.size
    }

    inner class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        @BindView(R.id.post_title)
        lateinit var postTitle: TextView

        lateinit var post: Post

        init {
            ButterKnife.bind(this, itemView)
            itemView.setOnClickListener { postClickSubject.onNext(post) }
        }

        fun onBind(post: Post) {
            this.post = post
            postTitle.text = this.post.title
        }
    }
}