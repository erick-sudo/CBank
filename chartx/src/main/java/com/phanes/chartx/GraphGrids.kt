package com.phanes.chartx

class GraphGrids(
    var verticalGridLines: VerticalGridLines = VerticalGridLines(),
    var horizontalGridLines: HorizontalGridLines = HorizontalGridLines(),
    var radarGridLines: RadarGridLines = RadarGridLines()
) {
    var xChartAllowance: Int = 0
    var yChartAllowance: Int = 0

    val highestChartAllowance
        get() = maxOf(xChartAllowance, yChartAllowance)

    fun configure(configureGraphGrids: GraphGrids.() -> Unit): GraphGrids {
        configureGraphGrids(this)
        return this
    }
}

abstract class GridLines {
    var units: Int = 1
        set(value) {
            require(value > 0) {
                "Units must be greater than 0"
            }
            field = value
        }
    var minorInterval: Float = 1f
        set(value) {
            require(value > 0) {
                "Minor Interval should not be zero"
            }
            field = value
        }

    val majorInterval
        get() = minorInterval * units
}

class VerticalGridLines : GridLines() {
    fun configure(configureGridLine: VerticalGridLines.() -> Unit): VerticalGridLines {
        configureGridLine(this)
        return this
    }
}

class HorizontalGridLines : GridLines() {
    fun configure(configureGridLine: HorizontalGridLines.() -> Unit): HorizontalGridLines {
        configureGridLine(this)
        return this
    }
}

class RadarGridLines : GridLines() {
    fun configure(configureGridLine: RadarGridLines.() -> Unit): RadarGridLines {
        configureGridLine(this)
        return this
    }
}