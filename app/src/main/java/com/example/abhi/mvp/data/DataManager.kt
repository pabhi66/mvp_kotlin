package com.example.abhi.mvp.data

import com.example.abhi.mvp.data.remote.ApiService
import com.example.abhi.mvp.data.response.Post
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

/**
 * @author Abhishek Prajapati
 * @version 1.0.0
 * @since 12/18/17.
 */

@Singleton
class DataManager @Inject
constructor(private val apiService: ApiService){
    fun getPosts(): Single<List<Post>> {
        return apiService.getPosts()
    }

    fun getPost(id: Int): Single<Post> {
        return apiService.getPost(id)
    }
}