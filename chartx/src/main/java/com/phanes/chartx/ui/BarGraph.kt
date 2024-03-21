package com.phanes.chartx.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.phanes.chartx.BarGraph
import com.phanes.chartx.GraphConfig
import kotlinx.coroutines.launch

@Composable
fun BarGraph(
    modifier: Modifier = Modifier,
    configureGraph: GraphConfig.() -> Unit
) {

    Canvas(
        modifier = Modifier.then(modifier)
            .fillMaxSize(),
        onDraw = {
            if (size.width > 0 && size.height > 0) {
                val barGraph = BarGraph(size)
                configureGraph(barGraph.graphConfig)

                barGraph.grid(this@Canvas)
                //barGraph.frame(this@Canvas)
                barGraph.plot(this@Canvas)
            }
        }
    )
}