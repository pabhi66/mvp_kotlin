package com.example.abhi.mvp.injection.modules

import android.app.Application
import android.content.Context
import com.example.abhi.mvp.injection.scopes.ApplicationContext
import dagger.Module
import dagger.Provides

/**
 * @author Abhishek Prajapati
 * @version 1.0.0
 * @since 12/18/17.
 */

@Module(includes = arrayOf(ApiModule::class))
class AppModule(private val application: Application) {

    @Provides
    internal fun provideApplication(): Application {
        return application
    }

    @Provides
    @ApplicationContext
    internal fun provideContext(): Context {
        return application
    }
}