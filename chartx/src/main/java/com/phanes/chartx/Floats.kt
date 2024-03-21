package com.phanes.chartx

import kotlin.math.PI

fun Float.toRadians(): Float {
    return (this * (PI / 180)).toFloat()
}