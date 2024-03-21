package com.phanes.chartx.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.phanes.chartx.GraphConfig
import com.phanes.chartx.PieChartConfig
import com.phanes.chartx.PolarArea
import kotlinx.coroutines.launch

@Composable
fun PolarArea(
    modifier: Modifier = Modifier,
    configureLineGraph: PieChartConfig.() -> Unit
) {

    Canvas(
        modifier = Modifier.then(modifier)
            .fillMaxWidth()
            .fillMaxHeight(),
        onDraw = {
            if (size.width > 0 && size.height > 0) {
                val polarArea = PolarArea(size)
                configureLineGraph(polarArea.graphConfig)
                //polarArea.grid(this)
                //polarArea.frame(this)
                polarArea.plot(this@Canvas)
            }
        }
    )
}