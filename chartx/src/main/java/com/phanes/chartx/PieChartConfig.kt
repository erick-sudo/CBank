package com.phanes.chartx

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

class PieChartConfig
    constructor(
        override val composeGraph: @Composable () -> Unit
) : GraphConfig(composeGraph) {

    internal var segmentColors: MutableList<MutableList<Color>> = mutableListOf()

    internal var segmentLabels: MutableList<MutableList<String>> = mutableListOf()

    fun addSegmentColors(colors: List<Color>) {
        segmentColors = mutableListOf(colors.toMutableList())
    }

    fun addSegmentLabels(labels: List<String>) {
        segmentLabels = mutableListOf(labels.toMutableList())
    }
}