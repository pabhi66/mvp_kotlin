package com.example.abhi.mvp.injection.components

import com.example.abhi.mvp.injection.modules.FragmentModule
import com.example.abhi.mvp.injection.scopes.PerFragment
import com.example.abhi.mvp.ui.main.MainActivityFragment
import dagger.Subcomponent

/**
 * @author Abhishek Prajapati
 * @version 1.0.0
 * @since 12/18/17.
 */
@PerFragment
@Subcomponent(modules = [(FragmentModule::class)])
interface FragmentComponent {
    fun inject(mainActivityFragment: MainActivityFragment)
}