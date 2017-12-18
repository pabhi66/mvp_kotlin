package com.example.abhi.mvp.injection.components

import android.app.Application
import android.content.Context
import com.example.abhi.mvp.data.DataManager
import com.example.abhi.mvp.data.remote.ApiService
import com.example.abhi.mvp.injection.modules.AppModule
import com.example.abhi.mvp.injection.scopes.ApplicationContext
import dagger.Component
import javax.inject.Singleton

/**
 * @author Abhishek Prajapati
 * @version 1.0.0
 * @since 12/18/17.
 */

@Singleton
@Component(modules = [(AppModule::class)])
interface AppComponent {

    @ApplicationContext
    fun context(): Context

    fun application(): Application

    fun dataManager(): DataManager

    fun apiService(): ApiService
}