package com.example.abhi.mvp.utils.rx.scheduler

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * @author Abhishek Prajapati
 * @version 1.0.0
 * @since 12/19/17.
 */
class ComputationMainScheduler<T> private constructor() : BaseScheduler<T>(Schedulers.computation(), AndroidSchedulers.mainThread())
