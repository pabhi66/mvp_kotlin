package com.example.abhi.mvp.injection.components

import com.example.abhi.mvp.injection.modules.ActivityModule
import com.example.abhi.mvp.injection.modules.FragmentModule
import com.example.abhi.mvp.injection.scopes.ConfigPersistent
import dagger.Component

/**
 * @author Abhishek Prajapati
 * @version 1.0.0
 * @since 12/18/17.
 */
@ConfigPersistent
@Component(dependencies = arrayOf(AppComponent::class))
interface ConfigPersistentComponent {

    fun activityComponent(activityModule: ActivityModule): ActivityComponent

    fun fragmentComponent(fragmentModule: FragmentModule): FragmentComponent

}