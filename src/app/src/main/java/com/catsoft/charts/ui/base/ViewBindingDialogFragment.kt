package com.catsoft.charts.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import javax.inject.Inject

abstract class ViewBindingDialogFragment<TBinding : ViewBinding> : BottomSheetDialogFragment() {
    private var _viewBinding: TBinding? = null
    protected val viewBinding get() = _viewBinding!!

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val creator = getViewBindingInflater()
        _viewBinding = creator(inflater, container, false)
        return viewBinding.root
    }

    abstract fun getViewBindingInflater(): (LayoutInflater, ViewGroup?, Boolean) -> TBinding

    override fun onDestroyView() {
        _viewBinding = null
        super.onDestroyView()
    }
}