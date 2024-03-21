package com.phanes.phanesbank.screens

//import androidx.compose.foundation.ExperimentalFoundationApi
//import androidx.compose.foundation.pager.HorizontalPager
//import androidx.compose.foundation.pager.rememberPagerState
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.remember
//import androidx.compose.ui.Modifier
//import androidx.navigation.NavHostController
//import com.phanes.chartx.GraphConfig
//import com.phanes.chartx.IndexAxis
//import com.phanes.chartx.ui.BarGraph
//import com.phanes.chartx.ui.Doughnut
//import com.phanes.chartx.ui.LineGraph
//import com.phanes.chartx.ui.PieChart
//import com.phanes.chartx.ui.PolarArea
//import com.phanes.chartx.ui.Radar
//
//@OptIn(ExperimentalFoundationApi::class)
//@Composable
//fun Home(
//    navHostController: NavHostController
//) {
////
////    val graphPager = rememberPagerState {
////        5
////    }
////
////    val graphConfigs: List<GraphConfig> = remember {
////        listOf(
////            GraphConfig {
////              BarGraph {
////                  addDatasets(
////                      listOf(56.76f, 75.89f, 45.6f, 67.8f, 90.0f),
////                      listOf(30.0f, 50.0f, 150.6f, 43.8f, 143.34f),
////                      listOf(45.34f, 58.34f, 92.45f, 102.45f, 18.56f))
////                  changeIndexAxis(IndexAxis.X)
////                  contentPadding(100f)
////                  configureGrids {
////                    verticalGridLines.configure {
////                        minorInterval = 100f
////                        units = 10
////                    }
////
////                    xChartAllowance = 1
////                    yChartAllowance = 1
////
////                    horizontalGridLines.configure {
////                        minorInterval = 100f
////                        units = 10
////                    }
////                }
////              }
////            },
////            GraphConfig {
////                LineGraph {
////                    addDatasets(listOf(56.76f, 70.34f, 102.54f, 45.78f, 67.90f,), listOf(100.76f, 77.34f, 67.54f, 25.78f, 97.90f,))
////                    contentPadding(100f)
////                    changeIndexAxis(IndexAxis.X)
////                    grid.configure {
////                        verticalGridLines.configure {
////                            minorInterval = 100f
////                            units = 5
////                        }
////                        horizontalGridLines.configure {
////                            minorInterval = 50f
////                            units = 5
////                        }
////                    }
////                }
////            },
////            GraphConfig {
////                PieChart {
////                    addDatasets(listOf(56.76f, 70.34f, 102.54f, 45.78f, 67.90f,))
////                    contentPadding(100f)
////                    changeIndexAxis(IndexAxis.X)
////                    grid.configure {
////                        verticalGridLines.configure {
////                            minorInterval = 20f
////                            units = 20
////                        }
////                        horizontalGridLines.configure {
////                            minorInterval = 20f
////                            units = 20
////                        }
////                    }
////                }
////            },
////            GraphConfig {
////                Radar {
////                    addDatasets(listOf(56.76f, 70.34f, 102.54f, 45.78f, 67.90f, 34.5f, 67.5f, 87.6f))
////                    contentPadding(100f)
////                    changeIndexAxis(IndexAxis.X)
////                    grid.configure {
////                        horizontalGridLines.configure {
////                            minorInterval = 50f
////                            units = 5
////                        }
////                        radarGridLines.configure {
////                            minorInterval = 20f
////                            units = 5
////                        }
////                    }
////                }
////            },
////            GraphConfig {
////                PolarArea {
////                    addDatasets(listOf(56.76f, 70.34f, 102.54f, 45.78f, 67.90f, 98.67f))
////                    contentPadding(100f)
////                    changeIndexAxis(IndexAxis.X)
////                    grid.configure {
////                        verticalGridLines.configure {
////                            minorInterval = 20f
////                            units = 20
////                        }
////                        horizontalGridLines.configure {
////                            minorInterval = 20f
////                            units = 20
////                        }
////                    }
////                }
////            }
////        )
////    }
////
////    HorizontalPager(state = graphPager) { page ->
////        graphConfigs[page].composeGraph()
////    }
////
////    LineGraph(
////        modifier = Modifier
////    ) {
////        addDatasets(listOf(56.76f, 70.34f, 102.54f, 45.78f, 67.90f,), listOf(100.76f, 77.34f, 67.54f, 25.78f, 97.90f,))
////        contentPadding(100f)
////        changeIndexAxis(IndexAxis.X)
////        grid.configure {
////            verticalGridLines.configure {
////                minorInterval = 100f
////                units = 5
////            }
////            horizontalGridLines.configure {
////                minorInterval = 50f
////                units = 5
////            }
////        }
////    }
//
////    PieChart(
////        modifier = Modifier
////    ) {
////        addDatasets(listOf(56.76f, 70.34f, 102.54f, 45.78f, 67.90f,))
////        contentPadding(100f)
////        changeIndexAxis(IndexAxis.X)
////        grid.configure {
////            verticalGridLines.configure {
////                minorInterval = 20f
////                units = 20
////            }
////            horizontalGridLines.configure {
////                minorInterval = 20f
////                units = 20
////            }
////        }
////    }
////    Doughnut(
////        modifier = Modifier
////    ) {
////        addDatasets(listOf(56.76f, 70.34f, 102.54f, 45.78f, 20f, 67.90f,))
////        contentPadding(100f)
////        changeIndexAxis(IndexAxis.X)
////        grid.configure {
////            verticalGridLines.configure {
////                minorInterval = 20f
////                units = 20
////            }
////            horizontalGridLines.configure {
////                minorInterval = 20f
////                units = 20
////            }
////        }
////    }
////
////    Radar(
////        modifier = Modifier
////    ) {
////        addDatasets(listOf(56.76f, 70.34f, 102.54f, 45.78f, 67.90f, 34.5f))
////        addDataLabels(
////            listOf(
////                "Business Turnover",
////                "Total contributions",
////                "Age of account",
////                "Personal credit score",
////                "Number of loans",
////                "Amount of loans repaid"
////            )
////        )
////        contentPadding(100f)
////        changeIndexAxis(IndexAxis.X)
////        grid.configure {
////            horizontalGridLines.configure {
////                minorInterval = 50f
////                units = 5
////            }
////            radarGridLines.configure {
////                minorInterval = 50f
////                units = 5
////            }
////        }
////    }
////
////    PolarArea(
////        modifier = Modifier
////    ) {
////        addDatasets(listOf(56.76f, 70.34f, 102.54f, 45.78f, 67.90f, 98.67f))
////        contentPadding(100f)
////        changeIndexAxis(IndexAxis.X)
////        grid.configure {
////            verticalGridLines.configure {
////                minorInterval = 20f
////                units = 20
////            }
////            horizontalGridLines.configure {
////                minorInterval = 20f
////                units = 20
////            }
////        }
////    }
//}
//
////@Preview(showBackground = true)
////@Composable
////fun HomePreview() {
////    Home(navHostController = rememberNavController())
////}


import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.phanes.chartx.GraphConfig
import com.phanes.chartx.IndexAxis
import com.phanes.chartx.PieChartConfig
import com.phanes.chartx.extensions.transformAlpha
import com.phanes.chartx.ui.Doughnut
import com.phanes.chartx.ui.PolarArea
import com.phanes.chartx.ui.Radar
import com.phanes.phanesbank.R
import com.phanes.phanesbank.extensions.toCurrency
import com.phanes.phanesbank.groups
import com.phanes.phanesbank.ui.AnimatedCreditScoreIndicator
import com.phanes.phanesbank.ui.GroupListItemUi
import com.phanes.phanesbank.ui.models.GroupListItem
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Home(
    navHostController: NavHostController
) {

    val netWorthParameters = listOf("Current Balance", "Investment", "Loans")
    val netWorthParameterColors = listOf(
        Color(69, 163, 74, 175),
        Color(63, 100, 235, 175),
        Color(202, 138, 24, 175)
    )

    val creditScore = 7.6f

    val scrollState = rememberScrollState()

    val creditStatusPagerState = rememberPagerState {
        2
    }

    val coroutineScope = rememberCoroutineScope()

    val gridLineColorMajor = MaterialTheme.colorScheme.onBackground
    val gridLineColorMinor = MaterialTheme.colorScheme.onBackground
    val background = MaterialTheme.colorScheme.background

    val creditStatusPages: List<GraphConfig> = remember {
        listOf(
            GraphConfig {
                Radar(
                    modifier = Modifier
                        .height(400.dp)
                        .fillMaxWidth()
                ) {
                    addDatasets(listOf(56.76f, 70.34f, 102.54f, 45.78f, 67.90f, 34.5f))
                    addDataLabels(
                        listOf(
                            "Business Turnover",
                            "Total contributions",
                            "Age of account",
                            "Personal credit score",
                            "Number of loans",
                            "Amount of loans repaid"
                        )
                    )
                    contentPadding(100f)
                    datasetColors = mutableListOf(mutableListOf(background.transformAlpha(175)))
                    colors.apply {
                        majorRadarGridLineColor = gridLineColorMajor
                        minorRadarGridLineColor = gridLineColorMinor
                    }
                    grid.configure {
                        horizontalGridLines.configure {
                            minorInterval = 10f
                            units = 5
                        }
                        radarGridLines.configure {
                            minorInterval = 10f
                            units = 10
                        }
                    }
                }
            },
            PieChartConfig {
                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(20.dp))
                    ) {
                        netWorthParameterColors.forEach { color ->
                            Box(
                                modifier = Modifier
                                    .height(10.dp)
                                    .weight(1f)
                                    .background(color)
                            )
                        }
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        netWorthParameters.forEach { parameter ->
                            Text(
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(5.dp),
                                text = parameter,
                                textAlign = TextAlign.Center,
                                fontWeight = FontWeight.ExtraBold,
                                color = MaterialTheme.colorScheme.secondary
                            )
                        }
                    }

                    PolarArea(
                        modifier = Modifier
                            .height(400.dp)
                    ) {
                        addDatasets(listOf(56.76f, 70.34f, 102.54f))
                        addDataLabels(netWorthParameters)
                        addSegmentColors(netWorthParameterColors)
                        contentPadding(100f)
                        changeIndexAxis(IndexAxis.X)
                        grid.configure {
                            verticalGridLines.configure {
                                minorInterval = 20f
                                units = 20
                            }
                            horizontalGridLines.configure {
                                minorInterval = 20f
                                units = 20
                            }
                        }
                    }
                }
            }
        )
    }

    val myGroups = groups.slice(0..2).map { grp ->
        GroupListItem(
            name = grp,
            numberOfMembers = Random.nextInt(5) + 3,
            creditScore = Random.nextDouble(10.0).toFloat()
        )
    }


    Column(
        modifier = Modifier
            .verticalScroll(scrollState)
    ) {
        HeroSection()

        Column(
            modifier = Modifier
                .padding(10.dp)
        ) {
            Card(
                shape = RoundedCornerShape(4)
            ) {
                InfoCard(
                    modifier = Modifier,

                    label = "Credit Score",
                    icon = ImageVector.vectorResource(R.drawable.outline_monetization_on_24)
                ) {
                    AnimatedCreditScoreIndicator(
                        modifier = Modifier,
                        creditScore = creditScore
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Row(

                ) {
                    Column(
                        modifier = Modifier
                            .clickable {
                                coroutineScope.launch {
                                    creditStatusPagerState.animateScrollToPage(0)
                                }
                            }
                            .weight(1f),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            modifier = Modifier
                                .padding(vertical = 5.dp, horizontal = 10.dp)
                                .fillMaxWidth(),
                            text = "Credit Status",
                            textAlign = TextAlign.Center
                        )
                        Box(
                            modifier = Modifier
                                .fillMaxWidth(0.7f)
                                .clip(RoundedCornerShape(20.dp))
                                .height(5.dp)
                                .border(
                                    width = 5.dp,
                                    color = if (creditStatusPagerState.currentPage == 0) MaterialTheme.colorScheme.secondary else Color.Transparent
                                )
                        )
                    }
                    //
                    Column(
                        modifier = Modifier
                            .clickable {
                                coroutineScope.launch {
                                    creditStatusPagerState.animateScrollToPage(1)
                                }
                            }
                            .weight(1f),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            modifier = Modifier
                                .padding(vertical = 5.dp, horizontal = 10.dp)
                                .fillMaxWidth(),
                            text = "Net Worth",
                            textAlign = TextAlign.Center
                        )
                        Box(
                            modifier = Modifier
                                .fillMaxWidth(0.7f)
                                .clip(RoundedCornerShape(20.dp))
                                .height(5.dp)
                                .border(
                                    width = 5.dp,
                                    color = if (creditStatusPagerState.currentPage == 1) MaterialTheme.colorScheme.secondary else Color.Transparent
                                )
                        )
                    }
                }

                HorizontalPager(
                    modifier = Modifier
                        .height(500.dp),
                    state = creditStatusPagerState
                ) {page ->
                        creditStatusPages[page].composeGraph()
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            InfoCard(
                modifier = Modifier,
                label = "Private Loan Limit",
                icon = ImageVector.vectorResource(R.drawable.baseline_perm_identity_24),
            ) {
                Text(
                    text = 979345.54.toCurrency(),
                    style = TextStyle(
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 20.sp
                    )
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            InfoCard(
                modifier = Modifier,
                label = "Group Loan Limit",
                icon = ImageVector.vectorResource(R.drawable.outline_groups_24)
            ) {
                Text(
                    text = 400345.92.toCurrency(),
                    style = TextStyle(
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 20.sp
                    )
                )
            }
        }

        Column(
            modifier = Modifier
                .padding(10.dp),
        ) {
            myGroups.map {
//                Card(
//                    elevation = CardDefaults.cardElevation(
//                        defaultElevation = 10.dp
//                    ),
//                    shape = RoundedCornerShape(4)
//                ) {
//                    GroupListItemUi(groupListItem = it)
//                }

                Spacer(
                    modifier = Modifier
                        .height(1.dp)
                        .fillMaxWidth()
                        .border(width = 1.dp, color = MaterialTheme.colorScheme.onBackground)
                )

                GroupListItemUi(groupListItem = it)
            }
        }
    }
}

@Composable
fun CreditParameters() {

}

@Composable
fun InfoCard(
    modifier: Modifier = Modifier,
    label: String,
    icon: ImageVector,
    value: @Composable () -> Unit
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(10))
            .then(modifier)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Box(
                modifier = Modifier
                    .padding(10.dp)
            ) {
                Icon(
                    modifier = Modifier
                        .size(60.dp),
                    imageVector = icon,
                    contentDescription = label
                )
            }

            Column {
                Text(
                    text = label,
                    style = TextStyle(
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 15.sp
                    )
                )
                Spacer(modifier = Modifier.height(10.dp))

                value()
            }
        }
    }
}

//@Preview
//@Composable
//fun InfoCardPreview() {
//    InfoCard(label = "Current Loans", icon = ImageVector.vectorResource(R.drawable.outline_monetization_on_24)) {
//
//    }
//}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HeroSection() {

    val coroutineScope = rememberCoroutineScope()

    val heroPager = rememberPagerState { 6 }

    val images = listOf(
        R.drawable.credit,
        R.drawable.lock,
        R.drawable.credit,
        R.drawable.lock,
        R.drawable.credit,
        R.drawable.lock
    )

    val texts = listOf(
        "Nemo quisquam asperiores. Qui voluptates assumenda. Quo quidem molestias.",
        "Architecto aut nobis. Impedit numquam ut. Culpa debitis veritatis.",
        "Doloremque ut velit. Blanditiis aut quaerat. Ullam magni reprehenderit.",
        "Aut modi a. Alias voluptatem nostrum. Dignissimos totam reprehenderit.",
        "Vel qui consequatur. Sapiente nihil vitae. Aut ut iste.",
        "Officia provident sit. Beatae velit quia. Corrupti officiis unde."
    )

    LaunchedEffect(Unit) {
        while (true) {
            delay(2000)
            heroPager.animateScrollToPage((heroPager.currentPage + 1) % heroPager.pageCount)
        }
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp),
    ) {
        HorizontalPager(state = heroPager) { page ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {
//                Image(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(start = 5.dp, top = 5.dp, end = 5.dp),
//                    painter = painterResource(id = images[page]),
//                    contentDescription = null,
//                    contentScale = ContentScale.Crop
//                )

                Text(
                    text = texts[heroPager.currentPage],
                    fontWeight = FontWeight.ExtraBold,
                    modifier = Modifier
                        .padding(30.dp)
                        .align(Alignment.TopCenter)
                )
            }
        }

        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
        ) {
            (0..<heroPager.pageCount).forEach { index ->
                FloatingActionButton(
                    containerColor = if(heroPager.currentPage == index) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.inversePrimary,
                    modifier = Modifier
                        .size(10.dp)
                        .padding(2.dp),
                    onClick = {
                        coroutineScope.launch {
                            heroPager.animateScrollToPage(index)
                        }
                    }
                ) {

                }
            }
        }
    }
}