package com.catsoft.charts.ui.base

import androidx.viewbinding.ViewBinding
import io.reactivex.disposables.CompositeDisposable

abstract class BaseFragment<TViewBinding : ViewBinding> : ViewBindingFragment<TViewBinding>() {

    protected var compositeDisposable = CompositeDisposable()
        private set

    override fun onDestroyView() {
        compositeDisposable.dispose()
        compositeDisposable = CompositeDisposable()

        super.onDestroyView()
    }
}