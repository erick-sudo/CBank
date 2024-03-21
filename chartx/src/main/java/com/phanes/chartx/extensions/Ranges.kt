package com.phanes.chartx.extensions

import kotlin.math.absoluteValue
import kotlin.math.ceil

fun lineSpace(start: Float, end: Float, step: Float = 1f, order: Int = 0): List<Float> {
    require(step>0) {
        "Step should be greater than 0 found $step"
    }
    val n = (end - start).absoluteValue/step

    return (0..< n.toInt()).fold(listOf(if(order >= 0) start else end)) { acc, _ ->
        acc + (if(order >= 0) acc.last() + step else acc.last() - step)
    }
}

fun evenSpacing(start: Float, end: Float, numberOfPosts: Int, circular: Boolean = false, order: Int = 0): List<Float> {
    require(numberOfPosts > 0) {
        "Number of posts should be at least 1"
    }

    val step = (end - start).absoluteValue / numberOfPosts

    return when(circular) {
        true -> (0..<numberOfPosts).let {
            if(order >= 0) it else it.reversed()
        }.map { start + it*step }
        else -> (0..< numberOfPosts).fold(listOf(if(order >= 0) start else end)) { acc, _ ->
            acc + (if(order >= 0) acc.last() + step else acc.last() - step)
        }
    }
}