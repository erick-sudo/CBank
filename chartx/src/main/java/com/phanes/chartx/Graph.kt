package com.phanes.chartx

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import com.phanes.chartx.extensions.lineSpace
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

internal abstract class Graph(
    val size: Size
): Plottable {

    /**
     * Default graph configurations
     */
    open val graphConfig: GraphConfig = GraphConfig { }

    internal val origin
        get() = Offset(graphConfig.padding, graphConfig.padding)

    internal val stop
        get() = Offset(size.width - graphConfig.padding, size.height - graphConfig.padding)

    internal val minimum
        get() = graphConfig.dataset.flatten().min()
    internal val maximum
        get() = graphConfig.dataset.flatten().max()

    internal val largestDatasetCount
        get() = graphConfig.dataset.fold(0) { acc, curr ->
            if(curr.size > acc) curr.size else acc
        }

    internal val plottableHeight
        get() = stop.y - origin.y

    internal val plottableWidth
        get() = stop.x - origin.x

    internal val verticalAllowance
        get() = graphConfig.grid.yChartAllowance * graphConfig.grid.horizontalGridLines.minorInterval

    internal val horizontalAllowance
        get() = graphConfig.grid.xChartAllowance * graphConfig.grid.verticalGridLines.minorInterval



    override fun grid(drawScope: DrawScope): GraphConfig {
        verticalGrids(origin.x, stop.x, origin.y, stop.y, drawScope, 0f)
        horizontalGrids(origin.x, stop.x, origin.y, stop.y, drawScope, 0f)
        return graphConfig
    }

    fun verticalGrids(hStart: Float, hEnd: Float, vStart: Float, vEnd: Float, drawScope: DrawScope, temporaryMinorInterval: Float = 0f, tickAllowance: Float = 0f): Graph {

        val step = when(temporaryMinorInterval > 0f) {
            true -> temporaryMinorInterval
            else -> graphConfig.grid.verticalGridLines.minorInterval
        }

        lineSpace(start = hStart, end = hEnd, step = step).forEach { x ->
                drawScope.apply {
                    drawLine(
                        start = Offset(x, vStart - tickAllowance),
                        end = Offset(x, vEnd + tickAllowance),
                        color = when((x-hStart) % graphConfig.grid.verticalGridLines.majorInterval == 0f) {
                            true -> graphConfig.colors.majorVerticalGridLineColor
                            else -> graphConfig.colors.minorVerticalGridLineColor
                        },
                        strokeWidth = 2f
                    )
                }
        }
        return this
    }

    fun horizontalGrids(hStart: Float, hEnd: Float, vStart: Float, vEnd: Float, drawScope: DrawScope, temporaryMinorInterval: Float = 0f, tickAllowance: Float = 0f): Graph {

        val step = when(temporaryMinorInterval > 0f) {
            true -> temporaryMinorInterval
            else -> graphConfig.grid.horizontalGridLines.minorInterval
        }

        lineSpace(start = vStart, end = vEnd, step = step, order = -1).forEach { y ->
            drawScope.apply {
                drawLine(
                    start = Offset(hStart - tickAllowance, y),
                    end = Offset(hEnd + tickAllowance, y),
                    color = when(((if(graphConfig.indexAxis == IndexAxis.X) vEnd - y else y) % graphConfig.grid.horizontalGridLines.majorInterval) == 0f) {
                        true -> graphConfig.colors.majorVerticalGridLineColor
                        else -> graphConfig.colors.minorVerticalGridLineColor
                    },
                    strokeWidth = 2f
                )
            }
        }
        return this
    }

    override fun frame(drawScope: DrawScope): GraphConfig {
        drawScope.apply {
            drawRect(
                topLeft = origin,
                color = Color.Black,
                size = Size(plottableWidth, plottableHeight),
                style = Stroke(width = 10f)
            )
        }
        return graphConfig
    }
}
