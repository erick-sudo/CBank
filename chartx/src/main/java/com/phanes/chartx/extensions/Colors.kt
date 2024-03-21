package com.phanes.chartx.extensions

import androidx.compose.ui.graphics.Color
import kotlin.math.roundToInt

fun Color.transformAlpha(alpha: Int): Color {
    return this.let { (r, g, b) ->
        Color((r*255).roundToInt(), (g*255).roundToInt(), (b*255).roundToInt(), alpha)
    }
}