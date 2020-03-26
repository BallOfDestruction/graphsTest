package com.catsoft.charts.ui.main_chart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.catsoft.charts.databinding.FragmentMainChartBinding
import com.catsoft.charts.di.Injectable
import com.catsoft.charts.ui.base.BaseFragment

class MainChartFragment : BaseFragment<FragmentMainChartBinding>(), Injectable {
    private val viewModel: MainChartViewModel by viewModels(factoryProducer = { viewModelFactory })

    override fun getViewBindingInflater(): (LayoutInflater, ViewGroup?, Boolean) -> FragmentMainChartBinding =
        FragmentMainChartBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
    }
}