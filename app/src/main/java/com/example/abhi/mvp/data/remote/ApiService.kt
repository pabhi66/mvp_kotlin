package com.example.abhi.mvp.data.remote

import com.example.abhi.mvp.data.response.Post
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * @author Abhishek Prajapati
 * @version 1.0.0
 * @since 12/18/17.
 */

interface ApiService {
    @GET("/posts")
    fun getPosts(): Single<List<Post>>

    @GET("/posts/{id}")
    fun getPost(@Path("id") id: Int): Single<Post>
}