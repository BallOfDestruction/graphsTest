package com.catsoft.charts.ui.main_chart

import com.scichart.charting.modifiers.TouchModifierBase
import com.scichart.charting.visuals.annotations.HorizontalLineAnnotation
import com.scichart.core.utility.touch.ModifierTouchEventArgs
import com.scichart.extensions.builders.SciChartBuilder

class HorizontalRolloverModifier(val builder: SciChartBuilder) : TouchModifierBase()
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