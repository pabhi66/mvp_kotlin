package com.example.abhi.mvp.injection.modules

import android.app.Activity
import android.content.Context
import com.example.abhi.mvp.injection.scopes.ActivityContext
import dagger.Module
import dagger.Provides

/**
 * @author Abhishek Prajapati
 * @version 1.0.0
 * @since 12/18/17.
 */

@Module class ActivityModule(private val activity: Activity) {

    @Provides
    internal fun provideActivity(): Activity {
        return activity
    }

    @Provides
    @ActivityContext
    internal fun providesContext(): Context {
        return activity
    }
}