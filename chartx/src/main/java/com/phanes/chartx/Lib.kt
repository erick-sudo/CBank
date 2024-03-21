package com.phanes.chartx

import androidx.compose.ui.graphics.Color
import kotlin.random.Random


internal object Lib {
    fun randomColor(alpha: Int): Color {
        return Color(randomColorShade(), randomColorShade(), randomColorShade(), alpha)
    }

    private fun randomColorShade(): Int {
        return Random.nextInt(255)
    }
}
