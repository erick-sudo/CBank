package com.phanes.chartx

import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextMeasurer
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import com.phanes.chartx.extensions.evenSpacing
import com.phanes.chartx.extensions.lineSpace
import com.phanes.chartx.extensions.transformAlpha
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.math.absoluteValue
import kotlin.math.roundToInt
import kotlin.math.sin

internal class Radar(
    size: Size
): PieChart(size) {

    private var markerRadius: Float = 0f

    private val radarAnglesSize
        get() = try {
            (360f / graphConfig.dataset.first().size)
        } catch (ex: NoSuchElementException) {
            0f
        }

    private val outerPolygonAngleSize
        get() = 180f - radarAnglesSize

    private val sideWidth
        get() = (radius/(0.5f * sin(radarAnglesSize/2))).absoluteValue

    fun pointMarkerRadius(radius: Float): Radar {
        this.markerRadius = radius
        return this
    }

    override fun grid(drawScope: DrawScope): GraphConfig {
        require(graphConfig.dataset.size == 1) {
            "Radar graphs require one dataset"
        }

        val gridLines = lineSpace(start = 0f, end = radius + graphConfig.grid.radarGridLines.minorInterval, step = graphConfig.grid.radarGridLines.minorInterval)
        (0 ..< gridLines.size - 1).forEach { index ->
            val polygon1 = lineSpace(start = -90f, end = 270f ,step = radarAnglesSize).map { computeCoordinateAroundCenter( it, gridLines[index]) }
            val polygon2 = lineSpace(start = 270f, end = -90f ,step = radarAnglesSize, order = -1).map { computeCoordinateAroundCenter( it, gridLines[index + 1]) }

            val polygonDimensions = polygon1 + polygon2

            drawScope.apply {
                drawPath(
                    path = Path().apply {
                        moveTo(polygonDimensions.first().x, polygonDimensions.first().y)

                        polygonDimensions.slice(1..<polygonDimensions.size).forEach { (x, y) ->
                            lineTo(x, y)
                        }
                        close()
                    },
                    color = when(gridLines[index] % graphConfig.grid.radarGridLines.majorInterval == 0f) {
                        true -> graphConfig.colors.majorRadarGridLineColor.transformAlpha((gridLines[index]/radius * 200).roundToInt())
                        false -> graphConfig.colors.minorRadarGridLineColor.transformAlpha((gridLines[index]/radius * 200).roundToInt())
                    }
                )
            }
        }

        graphConfig.grid.configure {
            horizontalGridLines.configure {
                units = radarGridLines.units
            }
        }

//        horizontalGrids(
//            hStart = origin.x,
//            hEnd = origin.y + diameter - horizontalAllowance,
//            vStart = origin.y + verticalAllowance,
//            vEnd = stop.y,
//            drawScope = drawScope,
//            tickAllowance = horizontalAllowance,
//            temporaryMinorInterval = graphConfig.grid.radarGridLines.minorInterval
//        )

        lineSpace(start = -90f, end = 270f, step = radarAnglesSize).forEach { angle ->
            val outerCoordinate = computeCoordinateAroundCenter(angle , radius)

            drawScope.apply {
                drawLine(
                    color = Color(0, 0, 0, 255),
                    start = Offset(
                        x = outerCoordinate.x,
                        y = outerCoordinate.y,
                    ),
                    end = Offset(
                        x = center.x,
                        y = center.y
                    ),
                    strokeWidth = 1f
                )
            }
        }

        return graphConfig
    }

    override fun frame(drawScope: DrawScope): GraphConfig {
        val polygonDimensions = lineSpace(start = -90f, end = 270f ,step = radarAnglesSize).map { computeCoordinateAroundCenter( it, radius) }

        drawScope.apply {
            drawPath(
                path = Path().apply {
                    moveTo(polygonDimensions.first().x, polygonDimensions.first().y)

                    polygonDimensions.slice(1..<polygonDimensions.size).forEach { (x, y) ->
                        lineTo(x, y)
                    }
                    close()
                },
                color = graphConfig.colors.majorRadarGridLineColor,
                style = Stroke(width = 1f)
            )
        }

        //super.frame(drawScope)
        return graphConfig
    }

    override fun label(textMeasurer: TextMeasurer, drawScope: DrawScope) {
        drawScope.apply {
            evenSpacing(start = -90f, end = 270f, numberOfPosts = graphConfig.dataset.first().size, circular = true, order = -1)
                .map { angle ->
                    (angle + 90) to computeCoordinateAroundCenter(
                        angle,
                        radius + (graphConfig.padding)
                    )
                }
                .forEachIndexed { index, (angle, offset) ->
                    try {

                        val text = buildAnnotatedString {
                            withStyle(
                                SpanStyle(
                                    fontWeight = FontWeight.ExtraBold,
                                    color = graphConfig.colors.majorRadarGridLineColor
                                )
                            ) {
                                append(graphConfig.labels[index])
                            }
                        }
                        rotate((angle + (radarAnglesSize/2)), offset) {
                            drawText(
                                textMeasurer = textMeasurer,
                                text = text,
                                topLeft = offset,
                                maxLines = 1,
                                softWrap = false,
                                size = Size(sideWidth, 80f)
                            )
                        }
                    } catch (ex: NoSuchElementException) {
                        // Ignore Label
                    } catch (ex: IndexOutOfBoundsException) {
                        // Ignore Label
                    }
                }
        }
    }

    override fun plot(drawScope: DrawScope) {
        require(graphConfig.dataset.size == 1) {
            "Radar graphs require one dataset"
        }

        val polygonDimensions = evenSpacing(start = -90f, end = 270f, numberOfPosts = graphConfig.dataset.first().size, circular = true, order = -1)
            .mapIndexed { index, angle ->
                computeCoordinateAroundCenter(
                    angle,
                    (graphConfig.dataset.first()[index]/maximum) * (radius - graphConfig.grid.highestChartAllowance)
                )
            }

        drawScope.apply {
            drawPath(
                path = Path().apply {
                    moveTo(polygonDimensions.first().x, polygonDimensions.first().y)

                    polygonDimensions.slice(1..<polygonDimensions.size).forEach { (x, y) ->
                        lineTo(x, y)
                    }
                    close()
                },
                color = try {
                    graphConfig.datasetColors.first().first()
                } catch (ex: NoSuchElementException) {
                    Lib.randomColor(200)
                }
            )
        }

        polygonDimensions.forEach { (x, y) ->
            drawScope.apply {

                drawArc(
                    color = Color(0,0 , 0, 255),
                    topLeft = Offset(
                        x = x - markerRadius,
                        y = y - markerRadius
                    ),
                    startAngle = 0f,
                    sweepAngle = 360f,
                    useCenter = false,
                    size = Size(markerRadius * 2, markerRadius * 2)
                )
            }
        }
    }
}