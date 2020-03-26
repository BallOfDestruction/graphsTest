package com.catsoft.charts.ui.base

import androidx.viewbinding.ViewBinding
import io.reactivex.disposables.CompositeDisposable

abstract class BaseDialogFragment<TViewBinding : ViewBinding> : ViewBindingDialogFragment<TViewBinding>() {

    protected var compositeDisposable = CompositeDisposable()
        private set

    override fun onDestroyView() {
        super.onDestroyView()

        compositeDisposable.dispose()
        compositeDisposable = CompositeDisposable()
    }
}