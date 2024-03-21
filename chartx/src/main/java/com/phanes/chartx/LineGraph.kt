package com.phanes.chartx

import android.util.Log
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.text.TextMeasurer
import kotlin.math.absoluteValue

internal open class LineGraph(
    size: Size
): Graph (size){

    private var shaded: Boolean = false

    private var markerRadius: Float = 0f

    // Best fit indexAxis interval
    open val indexAxisInterval
        get() = when(graphConfig.indexAxis) {
            IndexAxis.X -> (plottableWidth - (horizontalAllowance * 2)) / (if(largestDatasetCount > 1) largestDatasetCount -1 else 1)
            IndexAxis.Y -> (plottableHeight - (verticalAllowance * 2)) / (if(largestDatasetCount > 1) largestDatasetCount -1 else 1)
        }.absoluteValue

    fun shadeAreaUnderGraph(shade: Boolean): LineGraph {
        shaded = shade
        return this
    }

    fun pointMarkerRadius(radius: Float): LineGraph {
        this.markerRadius = radius
        return this
    }

    internal fun verticalGrids(drawScope: DrawScope) {
        verticalGrids(
            hStart = origin.x + horizontalAllowance,
            hEnd = stop.x - horizontalAllowance,
            vStart = origin.y + verticalAllowance,
            vEnd = stop.y - verticalAllowance,
            drawScope = drawScope,
            tickAllowance = verticalAllowance,
            temporaryMinorInterval = if (graphConfig.indexAxis == IndexAxis.X) indexAxisInterval else 0f
        )
    }

    internal fun horizontalGrids(drawScope: DrawScope) {
        horizontalGrids(
            hStart = origin.x + horizontalAllowance,
            hEnd = stop.x - horizontalAllowance,
            vStart = origin.y + verticalAllowance,
            vEnd = stop.y - verticalAllowance,
            drawScope = drawScope,
            tickAllowance = horizontalAllowance,
            temporaryMinorInterval = if(graphConfig.indexAxis == IndexAxis.Y) indexAxisInterval else 0f
        )
    }

    override fun grid(drawScope: DrawScope): GraphConfig {
        // Setting the computed index axis interval to the right axis
        when(graphConfig.indexAxis) {
            IndexAxis.X -> {
                graphConfig.grid.configure {
                    verticalGridLines.configure {
                        minorInterval = indexAxisInterval/largestDatasetCount
                    }
                }
            }
            IndexAxis.Y -> {
                graphConfig.grid.configure {
                    horizontalGridLines.configure {
                        minorInterval = indexAxisInterval/largestDatasetCount
                    }
                }
            }
        }
        verticalGrids(drawScope = drawScope)
        horizontalGrids(drawScope = drawScope)

        return graphConfig
    }

    override fun plot(drawScope: DrawScope) {

        if(largestDatasetCount > 0) {
            val plottingSpace = when(graphConfig.indexAxis) {
                IndexAxis.X -> plottableHeight - (verticalAllowance * 2)
                IndexAxis.Y -> plottableWidth - (horizontalAllowance * 2)
            }
            val linearScaleFactor = plottingSpace / (maximum - minimum)

            val plotPoints: List<List<ArcDimension>> = graphConfig.dataset.map { data ->
                var x = when(graphConfig.indexAxis) {
                    IndexAxis.X -> origin.x + horizontalAllowance
                    IndexAxis.Y -> origin.y + verticalAllowance
                }

                val newDataset = data.map { value ->
                    val arcDimension = ArcDimension(
                        x = when(graphConfig.indexAxis) {
                            IndexAxis.X -> x - markerRadius
                            IndexAxis.Y -> origin.x + horizontalAllowance + ((value - minimum) * linearScaleFactor) - markerRadius
                        },
                        y = when(graphConfig.indexAxis) {
                            IndexAxis.X -> stop.y - verticalAllowance - ((value - minimum) * linearScaleFactor) - markerRadius
                            IndexAxis.Y -> x - markerRadius
                        },
                        horizontalDiameter = markerRadius * 2f,
                        verticalDiameter = markerRadius * 2f,
                        offsetAngle = 0f,
                        sweepAngle = 360f
                    )

                    x += indexAxisInterval

                    arcDimension
                }
                newDataset
            }


            plotPoints.forEach { arcDimensions ->
                drawScope.apply {
                    if(shaded) {
                        val pathPoints = arcDimensions.map { arcDimension ->
                            Offset(
                                x = arcDimension.x + markerRadius,
                                y = arcDimension.y + markerRadius
                            )
                        }
                        drawPath(
                            path = Path().apply {
                                moveTo(
                                    x = origin.x + horizontalAllowance,
                                    y = if(graphConfig.indexAxis == IndexAxis.X) stop.y - verticalAllowance else origin.y + verticalAllowance
                                )
                                pathPoints.forEach { point ->
                                    lineTo(
                                        x = point.x,
                                        y = point.y
                                    )
                                }
                                lineTo(
                                    x = if(graphConfig.indexAxis == IndexAxis.X) stop.x - horizontalAllowance else origin.x + horizontalAllowance,
                                    y = stop.y - verticalAllowance
                                )
                                close()
                            },
                            color = Lib.randomColor(123)
                        )
                    }

                    arcDimensions.forEachIndexed {index, (x, y, _, _, _, _) ->
                        if(index < arcDimensions.size - 1) {
                            drawLine(
                                start = Offset(
                                    x + markerRadius,
                                    y + markerRadius
                                ),
                                end = Offset(
                                    arcDimensions[index + 1].x + markerRadius,
                                    arcDimensions[index + 1].y + markerRadius
                                ),
                                color = Color.Black,
                                strokeWidth = 1f
                            )
                        }
                    }
                    arcDimensions.forEach { (x, y, width, height, startAngle, sweepAngle) ->
                        drawArc(
                            color = graphConfig.colors.majorHorizontalGridLineColor,
                            topLeft = Offset(
                                x = x,
                                y = y
                            ),
                            startAngle = startAngle,
                            sweepAngle = sweepAngle,
                            useCenter = true,
                            size = Size(width, height)
                        )
                    }
                }
            }
        }
    }

    override fun label(textMeasurer: TextMeasurer, drawScope: DrawScope) {
        TODO("Not yet implemented")
    }
}
