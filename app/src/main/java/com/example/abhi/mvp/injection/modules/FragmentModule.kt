package com.example.abhi.mvp.injection.modules

import android.app.Activity
import android.content.Context
import android.support.v4.app.Fragment
import com.example.abhi.mvp.injection.scopes.ActivityContext
import dagger.Module
import dagger.Provides

/**
 * @author Abhishek Prajapati
 * @version 1.0.0
 * @since 12/18/17.
 */

@Module
class FragmentModule(private val fragment: Fragment) {

    @Provides
    internal fun providesFragment(): Fragment {
        return fragment
    }

    @Provides
    internal fun provideActivity(): Activity {
        return fragment.activity!!
    }

    @Provides
    @ActivityContext
    internal fun providesContext(): Context {
        return fragment.activity!!
    }

}