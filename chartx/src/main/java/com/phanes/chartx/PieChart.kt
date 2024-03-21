package com.phanes.chartx

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.text.TextMeasurer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.math.cos
import kotlin.math.roundToInt
import kotlin.math.sin

internal open class PieChart(
    size: Size
) : Graph(size) {

    override val graphConfig: PieChartConfig = PieChartConfig { }

    internal val center
        get() = Offset(
            (origin.x + plottableWidth/2),
            (origin.y + plottableHeight/2)
        )

    internal val radius
        get() = when(plottableWidth < plottableHeight) {
            true -> (plottableWidth - (horizontalAllowance * 2))/2
            false -> (plottableHeight - (verticalAllowance * 2))/2
        }

    internal val diameter
        get() = radius * 2

    internal val datasetAngles
        get() = graphConfig.dataset.map { data ->
            val sum = data.sum()
            data.map { ((it/sum) * 360) }
        }

    internal fun computeCoordinateAroundCenter(angle: Float, radius: Float): Offset {
        return Offset(
            (center.x + (radius * cos(angle.toRadians()))),
            (center.y + (radius * sin(angle.toRadians())))
        )
    }

    override fun plot(drawScope: DrawScope) {
        datasetAngles.forEach { dataset ->
            var startAngle = 90f
            dataset.forEach { angle ->

                drawScope.apply {
                    drawArc(
                        color = Lib.randomColor(200),
                        topLeft = Offset(
                            x = center.x - radius,
                            y = center.y - radius
                        ),
                        startAngle = startAngle,
                        sweepAngle = angle,
                        useCenter = true,
                        size = Size(diameter, diameter)
                    )
                }
                startAngle += angle
            }
        }
    }

    override fun label(textMeasurer: TextMeasurer, drawScope: DrawScope) {

    }
}