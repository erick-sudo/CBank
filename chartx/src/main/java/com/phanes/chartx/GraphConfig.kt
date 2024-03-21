package com.phanes.chartx

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

open class GraphConfig constructor(open val composeGraph: @Composable () -> Unit) {

    /**
     * Dataset
     */
    val dataset: MutableList<MutableList<Float>> = mutableListOf()

    var datasetColors: MutableList<MutableList<Color>> = mutableListOf()

    /**
     * Content Padding
     */
    var padding: Float = 0f

    /**
     * Graph title
     */
    var title: String? = null

    /**
     * Index axis Labels
     */
    var labels: MutableList<String> = mutableListOf()

    /**
     * The index axis
     */
    var indexAxis: IndexAxis = IndexAxis.X

    /**
     * Grid Configuration
     */
    var grid: GraphGrids = GraphGrids()

    /**
     * Graph color scheme
     */
    var colors: GraphColorScheme = GraphColorScheme()

    fun addTitle(title: String?): GraphConfig {
        this.title = title
        return this
    }

    fun addDatasets(vararg datasets: List<Float>): GraphConfig {
        datasets.forEach { data ->
            dataset.add(data.toMutableList())
        }
        return this
    }

    fun addDataLabels(labels: List<String>): GraphConfig {
        this.labels = labels.toMutableList()
        return this
    }

    fun changeIndexAxis(indexAxis: IndexAxis): GraphConfig {
        this.indexAxis = indexAxis
        return this
    }

    fun configureGrids(config: GraphGrids.() -> Unit): GraphConfig {
        config(grid)
        return this
    }

    fun contentPadding(padding: Float): GraphConfig {
        this.padding = padding
        return this
    }

    fun colors(colorsConfig: GraphColorScheme.() -> Unit): GraphConfig {
        colorsConfig(colors)
        return this
    }
}
