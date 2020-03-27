package com.catsoft.charts.utils

import android.os.Build
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

fun Boolean.toVisibility() : Int {
    return if (this) View.VISIBLE else View.GONE
}

fun <T> Fragment.observe(liveData : LiveData<T>, action : (t:T) -> Unit) {
    liveData.observe(this.viewLifecycleOwner, Observer { action.invoke(it) })
}

fun Fragment.getColor(id:Int) : Int = ContextCompat.getColor(requireContext(), id)

fun <T>MutableLiveData<T>.repost() {
    this.postValue(this.value!!)
}