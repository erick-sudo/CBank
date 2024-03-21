package com.phanes.chartx.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.phanes.chartx.GraphConfig
import com.phanes.chartx.PieChart
import com.phanes.chartx.PieChartConfig
import kotlinx.coroutines.launch

@Composable
fun PieChart(
    modifier: Modifier = Modifier,
    configureLineGraph: PieChartConfig.() -> Unit
) {

    Canvas(
        modifier = Modifier.then(modifier)
            .fillMaxWidth()
            .fillMaxHeight(),
        onDraw = {
            if (size.width > 0 && size.height > 0) {
                val pieChart = PieChart(size)
                configureLineGraph(pieChart.graphConfig)
                // pieChart.grid(this)
                //pieChart.frame(this)
                pieChart.plot(this@Canvas)
            }
        }
    )
}