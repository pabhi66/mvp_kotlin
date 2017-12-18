package com.example.abhi.mvp.ui.base

import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.support.v4.util.LongSparseArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.ButterKnife
import com.example.abhi.mvp.MainApplication
import com.example.abhi.mvp.injection.components.ConfigPersistentComponent
import com.example.abhi.mvp.injection.components.DaggerConfigPersistentComponent
import com.example.abhi.mvp.injection.components.FragmentComponent
import com.example.abhi.mvp.injection.modules.FragmentModule
import timber.log.Timber
import java.util.concurrent.atomic.AtomicLong

/**
 * @author Abhishek Prajapati
 * @version 1.0.0
 * @since 12/18/17.
 */
abstract class BaseFragment : Fragment() {

    private var fragmentComponent: FragmentComponent? = null
    private var fragmentId = 0L

    companion object {
        private val KEY_FRAGMENT_ID = "KEY_FRAGMENT_ID"
        private val componentsArray = LongSparseArray<ConfigPersistentComponent>()
        private val NEXT_ID = AtomicLong(0)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Create the FragmentComponent and reuses cached ConfigPersistentComponent if this is
        // being called after a configuration change.
        fragmentId = savedInstanceState?.getLong(KEY_FRAGMENT_ID) ?: NEXT_ID.getAndIncrement()
        val configPersistentComponent: ConfigPersistentComponent
        if (componentsArray.get(fragmentId) == null) {
            Timber.i("Creating new ConfigPersistentComponent id=%d", fragmentId)
            configPersistentComponent = DaggerConfigPersistentComponent.builder()
                    .appComponent(MainApplication[activity].component)
                    .build()
            componentsArray.put(fragmentId, configPersistentComponent)
        } else {
            Timber.i("Reusing ConfigPersistentComponent id=%d", fragmentId)
            configPersistentComponent = componentsArray.get(fragmentId)
        }
        fragmentComponent = configPersistentComponent.fragmentComponent(FragmentModule(this))
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View? = inflater?.inflate(getLayout(), container, false)
        ButterKnife.bind(this, view as View)
        return view
    }

    @LayoutRes abstract fun getLayout(): Int

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState?.putLong(KEY_FRAGMENT_ID, fragmentId)
    }

    override fun onDestroy() {
        if (!activity!!.isChangingConfigurations) {
            Timber.i("Clearing ConfigPersistentComponent id=%d", fragmentId);
            componentsArray.remove(fragmentId);
        }
        super.onDestroy()
    }

    fun injectFragmentComponent() = fragmentComponent as FragmentComponent

}