package com.phanes.chartx

import androidx.compose.ui.graphics.Color

class GraphColorScheme {

    var background = Color.White
    var majorVerticalGridLineColor = Color(0, 0, 0, 150)
    var minorVerticalGridLineColor = Color(0, 0, 0, 100)
    var majorHorizontalGridLineColor = Color(0, 0, 0, 150)
    var minorHorizontalGridLineColor = Color(0, 0, 0, 100)
    var majorRadarGridLineColor = Color(0, 0, 0, 150)
    var minorRadarGridLineColor = Color(0, 0, 0, 100)

    var xAxisTickLabelsColor = Color(0, 0, 0, 255)
    var xAxisLabel = Color(0, 0, 0, 255)
    var yAxisTickLabelsColor = Color(0, 0, 0, 255)
    var yAxisLabel = Color(0, 0, 0, 255)
    var zAxisTickLabelsColor = Color(0, 0, 0, 255)
    var zAxisLabel = Color(0, 0, 0, 255)

    var titleColor = Color(0, 0, 0, 255)

    fun applyColors(config: GraphColorScheme.() -> Unit) {
        config(this)
    }
}