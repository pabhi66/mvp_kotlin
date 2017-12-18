package com.example.abhi.mvp.injection.components

import com.example.abhi.mvp.injection.modules.ActivityModule
import com.example.abhi.mvp.injection.scopes.PerActivity
import com.example.abhi.mvp.ui.base.BaseActivity
import com.example.abhi.mvp.ui.main.MainActivity
import dagger.Subcomponent

/**
 * @author Abhishek Prajapati
 * @version 1.0.0
 * @since 12/18/17.
 */
@PerActivity @Subcomponent(modules = arrayOf(ActivityModule::class))
interface ActivityComponent {
    fun inject(baseActivity: BaseActivity)

    fun inject(mainActivity: MainActivity)

}