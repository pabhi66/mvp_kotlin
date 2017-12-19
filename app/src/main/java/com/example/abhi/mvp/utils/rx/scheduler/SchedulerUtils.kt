package com.example.abhi.mvp.utils.rx.scheduler

/**
 * @author Abhishek Prajapati
 * @version 1.0.0
 * @since 12/19/17.
 */
object SchedulerUtils {

    fun <T> ioToMain(): IoMainScheduler<T> {
        return IoMainScheduler()
    }
}
