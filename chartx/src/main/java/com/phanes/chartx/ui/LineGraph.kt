package com.phanes.chartx.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.phanes.chartx.GraphConfig
import com.phanes.chartx.LineGraph
import kotlinx.coroutines.launch


@Composable
fun LineGraph(
    modifier: Modifier = Modifier,
    configureLineGraph: GraphConfig.() -> Unit
) {

    Canvas(
        modifier = Modifier.then(modifier)
            .fillMaxSize(),
        onDraw = {
            if (size.width > 0 && size.height > 0) {
                val lineGraph = LineGraph(size)
                configureLineGraph(lineGraph.graphConfig)
                lineGraph.pointMarkerRadius(5f).shadeAreaUnderGraph(true)
                lineGraph.grid(this@Canvas)
                //lineGraph.frame(this)
                lineGraph.plot(this@Canvas)
            }
        }
    )
}