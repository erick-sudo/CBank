package com.phanes.chartx

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.drawscope.DrawScope
import kotlin.math.absoluteValue

internal class BarGraph(
    size: Size
): LineGraph (size){

    override val indexAxisInterval
        get() = when(graphConfig.indexAxis) {
            IndexAxis.X -> (plottableWidth - (2 * horizontalAllowance))/largestDatasetCount
            IndexAxis.Y -> (plottableHeight - (2* verticalAllowance))/largestDatasetCount
        }.absoluteValue

    private val multipleBarWidthInterval
        get() = (indexAxisInterval/graphConfig.dataset.size).absoluteValue

    private val maxBarHeight
        get() = when(graphConfig.indexAxis) {
            IndexAxis.X -> plottableHeight - (verticalAllowance * 2)
            IndexAxis.Y -> plottableWidth - (horizontalAllowance * 2)
        }.absoluteValue

    private val groupedDatasets
        get() = (0..<largestDatasetCount).map { outerIndex ->
            (0..<graphConfig.dataset.size).map { innerIndex ->
                try {
                    graphConfig.dataset[innerIndex][outerIndex]
                } catch (ex: IndexOutOfBoundsException) {
                    0f
                }
            }
        }


    override fun plot(drawScope: DrawScope) {

        var x = when(graphConfig.indexAxis) {
            IndexAxis.X -> origin.x + horizontalAllowance
            IndexAxis.Y -> origin.y + verticalAllowance
        }
        val barColors = (0..<graphConfig.dataset.size).map { Lib.randomColor(250) }
        groupedDatasets.forEachIndexed { outerIndex, groupedDataset ->
            groupedDataset.forEachIndexed { innerIndex, data ->
                val barHeight = ((data/maximum) * maxBarHeight)

                drawScope.apply {
                    drawRect(
                        color = barColors[innerIndex],
                        topLeft = Offset(
                            x = when(graphConfig.indexAxis) {
                                IndexAxis.X -> x + (innerIndex * multipleBarWidthInterval)
                                IndexAxis.Y -> (origin.x + horizontalAllowance)
                            },
                            y = when(graphConfig.indexAxis) {
                                IndexAxis.X -> stop.y - verticalAllowance - barHeight
                                IndexAxis.Y -> (origin.y + verticalAllowance + outerIndex * indexAxisInterval) + (innerIndex * multipleBarWidthInterval)
                            }
                        ),
                        size = Size(
                            width = if (graphConfig.indexAxis == IndexAxis.X) multipleBarWidthInterval else barHeight,
                            height = if (graphConfig.indexAxis == IndexAxis.X) barHeight else multipleBarWidthInterval
                        )
                    )
                }
            }
            x += indexAxisInterval
        }
    }
}