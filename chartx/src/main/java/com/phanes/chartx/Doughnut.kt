package com.phanes.chartx

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke

internal class Doughnut(
    size: Size
): PieChart(size) {

    override fun plot(drawScope: DrawScope) {

        datasetAngles.map { dataset ->
            var startAngle = -90f
            dataset.mapIndexed { index, sweepAngle ->
                val path = Path().apply {

                    val start = computeCoordinateAroundCenter(startAngle, radius)
                    val last = computeCoordinateAroundCenter(startAngle + sweepAngle, radius * (1f/2f))
                    moveTo(start.x, start.y)
                    arcTo(
                        rect = Rect(
                            topLeft = Offset(center.x - radius, center.y - radius),
                            bottomRight = Offset(center.x + radius, center.y + radius)
                        ),
                        startAngleDegrees = startAngle,
                        sweepAngleDegrees = sweepAngle,
                        forceMoveTo = false
                    )
                    lineTo(last.x, last.y)
                    arcTo(
                        rect = Rect(
                            topLeft = Offset(center.x - radius * (1f/2f), center.y - radius * (1f/2f)),
                            bottomRight = Offset(center.x + radius * (1f/2f), center.y + radius * (1f/2f))
                        ),
                        startAngleDegrees = startAngle + sweepAngle,
                        sweepAngleDegrees = -sweepAngle,
                        forceMoveTo = false
                    )
                    close()
                }
                startAngle += sweepAngle
                path
            }
        }.forEachIndexed { datasetIndex, paths ->
            paths.forEachIndexed { pathIndex, path ->
                drawScope.apply {
                    drawPath(
                        path = path,
                        color = try {
                            graphConfig.segmentColors[datasetIndex][pathIndex]
                        } catch (ex: IndexOutOfBoundsException) {
                            Lib.randomColor(200)
                        },
                    )
                }
            }
        }
    }
}
