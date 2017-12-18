package com.example.abhi.mvp

import android.content.Context
import android.support.multidex.MultiDexApplication
import com.example.abhi.mvp.injection.components.AppComponent
import com.example.abhi.mvp.injection.components.DaggerAppComponent
import com.example.abhi.mvp.injection.modules.AppModule
import com.example.abhi.mvp.injection.modules.NetworkModule
import com.facebook.stetho.Stetho
import com.singhajit.sherlock.core.Sherlock
import com.squareup.leakcanary.LeakCanary
import com.tspoon.traceur.Traceur
import timber.log.Timber

/**
 * @author Abhishek Prajapati
 * @version 1.0.0
 * @since 12/18/17.
 */
class MainApplication : MultiDexApplication() {

    private var appComponent: AppComponent? = null

    companion object {
        operator fun get(context: Context): MainApplication {
            return context.applicationContext as MainApplication
        }
    }

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
            Stetho.initializeWithDefaults(this)
            LeakCanary.install(this)
            Sherlock.init(this)
            Traceur.enableLogging()
        }
    }

    var component: AppComponent
        get() {
            if(appComponent == null) {
                appComponent = DaggerAppComponent.builder()
                        .appModule(AppModule(this))
                        .networkModule(NetworkModule(this, BuildConfig.API_URL))
                        .build()
            }
            return appComponent as AppComponent
        }
    set(appComponent) {
        this.appComponent = appComponent
    }
}