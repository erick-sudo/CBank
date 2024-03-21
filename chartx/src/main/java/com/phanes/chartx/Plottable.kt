package com.phanes.chartx

import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.text.TextMeasurer

interface Plottable {
    fun plot(drawScope: DrawScope)

    fun frame(drawScope: DrawScope): GraphConfig

    fun grid(drawScope: DrawScope): GraphConfig

    fun label(textMeasurer: TextMeasurer, drawScope: DrawScope)
}
