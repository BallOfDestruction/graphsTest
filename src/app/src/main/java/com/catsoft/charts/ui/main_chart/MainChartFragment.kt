package com.catsoft.charts.ui.main_chart

import android.graphics.Point
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.catsoft.charts.databinding.FragmentMainChartBinding
import com.catsoft.charts.di.Injectable
import com.catsoft.charts.ui.base.BaseFragment
import com.scichart.charting.model.dataSeries.XyDataSeries
import com.scichart.charting.modifiers.ChartModifierBase
import com.scichart.charting.modifiers.RolloverModifier
import com.scichart.charting.modifiers.TouchModifierBase
import com.scichart.charting.visuals.SciChartSurface
import com.scichart.charting.visuals.annotations.HorizontalLineAnnotation
import com.scichart.charting.visuals.axes.AxisTitlePlacement
import com.scichart.charting.visuals.axes.IAxis
import com.scichart.charting.visuals.pointmarkers.EllipsePointMarker
import com.scichart.charting.visuals.renderableSeries.IRenderableSeries
import com.scichart.core.framework.IHitTestable
import com.scichart.core.utility.touch.ModifierTouchEventArgs
import com.scichart.drawing.utility.ColorUtil
import com.scichart.extensions.builders.SciChartBuilder
import io.reactivex.Observable
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.random.Random

class MainChartFragment : BaseFragment<FragmentMainChartBinding>(), Injectable {
    private val viewModel: MainChartViewModel by viewModels(factoryProducer = { viewModelFactory })

    override fun getViewBindingInflater(): (LayoutInflater, ViewGroup?, Boolean) -> FragmentMainChartBinding =
        FragmentMainChartBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        val surface = SciChartSurface(requireContext())
        val chartLayout = viewBinding.chartSurfaceLayout
        chartLayout.addView(surface)
        SciChartBuilder.init(requireContext())

        val visibleRangeMin = Calendar.getInstance().apply { this.add(Calendar.SECOND, -10) }.time
        val visibleRangeMax = Calendar.getInstance().apply { this.add(Calendar.SECOND, 30) }.time

        val sciChartBuilder = SciChartBuilder.instance()
        val xAxis: IAxis = sciChartBuilder.newDateAxis()
            .withVisibleRange(visibleRangeMin, visibleRangeMax)
            .withAxisTitle("Date")
            .build()

        val yMin = -5.0
        val yMax = 5.0

        val yAxis: IAxis = sciChartBuilder.newNumericAxis()
            .withVisibleRange(yMin, yMax)
            .withAxisTitle("Y")
            .withAxisTitlePlacement(AxisTitlePlacement.Left)
            .build()

        val chartModifiers = sciChartBuilder.newModifierGroup()
            .withModifier(HitTestingModifier(sciChartBuilder))
            .withPinchZoomModifier().withReceiveHandledEvents(true).build()
            .withZoomPanModifier().withReceiveHandledEvents(true).build()
            .build()

        val horizontalLineAnnotation = sciChartBuilder.newHorizontalLineAnnotation()
            .withYValue(0.0)
            .build()




        val pointMarker =
            sciChartBuilder.newPointMarker(EllipsePointMarker())
                .withSize(5, 5)
                .withStroke(ColorUtil.argb(255, 176, 196, 222), 2f)
                .withFill(ColorUtil.argb(255, 70, 130, 180))
                .build()


        val dataSeries = XyDataSeries(Date::class.java, Double::class.javaObjectType)

        dataSeries.append(Calendar.getInstance().time, 0.0)

        val scatterSeries: IRenderableSeries = sciChartBuilder.newScatterSeries()
            .withDataSeries(dataSeries)
            .withPointMarker(pointMarker)
            .withStrokeStyle(ColorUtil.argb(0xFF, 0x27, 0x9B, 0x27))
            .build()

        val s = Observable.interval(1, TimeUnit.SECONDS)
            .map { Calendar.getInstance().time }
            .subscribe {
                dataSeries.append(it, Random.nextDouble(-1.0, 1.0))
                surface.zoomExtentsX()
            }

        surface.yAxes.add(yAxis)
        surface.xAxes.add(xAxis)
        surface.renderableSeries.add(scatterSeries)
        surface.chartModifiers.add(chartModifiers)
        surface.annotations.add(horizontalLineAnnotation)
    }
}

class HitTestingModifier(val builder: SciChartBuilder) : TouchModifierBase()
{
    private var line : HorizontalLineAnnotation? = null

    override fun onTouchDown(args: ModifierTouchEventArgs?): Boolean {
        clear()

        line = builder.newHorizontalLineAnnotation()
            .withYValue(args?.e?.y?.toDouble())
            .build()

        this.parentSurface.annotations.add(line)

        return super.onTouchDown(args)
    }

    override fun onTouchUp(args: ModifierTouchEventArgs?): Boolean {
        clear()
        return super.onTouchUp(args)
    }

    fun clear() {
        line?.let {
            this.parentSurface.annotations.remove(it)
            line = null
        }
    }
}