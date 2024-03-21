package com.phanes.chartx

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.drawscope.DrawScope
import java.lang.IndexOutOfBoundsException

internal class PolarArea(
    size: Size
): PieChart(size) {

    private val polarRanks
        get() = graphConfig.dataset.map { data ->
            val sum = data.sum()
            val sortedDataset = data.sorted().mapIndexed { index, v ->
                v to (index + 1)
            }.toMap()
            data.map { value ->
                PolarRank(
                    angle = ((value/sum) * 360),
                    rank = sortedDataset[value] ?: 1,
                    value = value
                )
            }
        }

    override fun plot(drawScope: DrawScope) {
        polarRanks.forEachIndexed { datasetIndex, dataset ->
            var startAngle = 90f
            val maxValue = dataset.maxBy { it.value }.value
            dataset.forEachIndexed { dataIndex, polarRank ->
                val polarRadius = polarRank.polarRadius(radius, maxValue)

                drawScope.apply {
                    drawArc(
                        color = try {
                            graphConfig.segmentColors[datasetIndex][dataIndex]
                        } catch (ex: IndexOutOfBoundsException) {
                            Lib.randomColor(200)
                        },
                        topLeft = Offset(
                            x = center.x - polarRadius,
                            y = center.y - polarRadius
                        ),
                        startAngle = startAngle,
                        sweepAngle = polarRank.angle,
                        useCenter = true,
                        size = Size(polarRadius * 2, polarRadius * 2)
                    )
                }

                startAngle += polarRank.angle
            }
        }
    }
}

data class PolarRank(
    val angle: Float,
    val rank: Int,
    val value: Float
) {

    fun polarRadius(resolutionRadius: Float, rank1Value: Float): Float {
        return ((value/rank1Value) * resolutionRadius)
    }
}