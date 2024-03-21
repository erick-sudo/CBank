package com.phanes.chartx.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.rememberTextMeasurer
import com.phanes.chartx.GraphConfig
import com.phanes.chartx.PieChartConfig
import com.phanes.chartx.Radar
import kotlinx.coroutines.launch

@Composable
fun Radar(
    modifier: Modifier = Modifier,
    configureLineGraph: PieChartConfig.() -> Unit
) {

    val textMeasurer = rememberTextMeasurer()

    Box(
        modifier = Modifier.then(modifier)
    ) {
        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            onDraw = {
                if (size.width > 0 && size.height > 0) {
                    val radar = Radar(size)
                    configureLineGraph(radar.graphConfig)
                    radar.pointMarkerRadius(5f)
                    radar.grid(this@Canvas)
                    radar.label( textMeasurer, this@Canvas)
                    radar.frame(this@Canvas)
                    radar.plot(this@Canvas)
                }
            }
        )
    }
}