package com.catsoft.charts.ui.main_chart

import android.graphics.PointF
import com.scichart.charting.modifiers.TouchModifierBase
import com.scichart.charting.visuals.annotations.HorizontalLineAnnotation
import com.scichart.core.utility.touch.ModifierTouchEventArgs
import com.scichart.extensions.builders.SciChartBuilder

class HorizontalRolloverModifier(private val builder: SciChartBuilder) : TouchModifierBase() {
    private var line: HorizontalLineAnnotation? = null

    override fun onTouchDown(args: ModifierTouchEventArgs?): Boolean {
        regenerate(args)
        return super.onTouchDown(args)
    }

    override fun onTouchMove(args: ModifierTouchEventArgs?): Boolean {
        regenerate(args)
        return super.onTouchMove(args)
    }

    override fun onTouchUp(args: ModifierTouchEventArgs?): Boolean {
        clear()
        return super.onTouchUp(args)
    }

    private fun regenerate(args: ModifierTouchEventArgs?) {
        clear()
        val x = args?.e?.x ?: 0f
        val y = args?.e?.y ?: 0f
        val point = PointF(x, y)

        this.parentSurface.translatePoint(point, parentSurface.renderableSeriesArea)

        val yValue = yAxis?.getDataValue(point.y)

        line = builder.newHorizontalLineAnnotation()
            .withYValue(yValue)
            .build()

        this.parentSurface.annotations.add(line)
    }

    private fun clear() {
        line?.let {
            this.parentSurface.annotations.remove(it)
            line = null
        }
    }
}