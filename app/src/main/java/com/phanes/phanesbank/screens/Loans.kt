package com.phanes.phanesbank.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Button
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.phanes.phanesbank.R
import com.phanes.phanesbank.ui.EmptyResults
import com.phanes.phanesbank.ui.HorizontalUnderlinedNavigationBar
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Loans() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {

        val coroutineScope = rememberCoroutineScope()

        val loanStatusPager = rememberPagerState {
            3
        }

        val loanStatusPages: List<@Composable ()-> Unit> = remember {
            listOf(
                @Composable {
                    EmptyResults {
                        Text(text = "You do not have any loans pending approval")
                        Spacer(modifier = Modifier.height(40.dp))
                        Image(painter = painterResource(id = R.drawable.tree_branch_vector), contentDescription = "")
                        Spacer(modifier = Modifier.height(40.dp))

                        Button(onClick = { /*TODO*/ }) {
                            Text(text = "Click here to apply")
                        }
                    }
                },
                @Composable {
                    EmptyResults {
                        Text(text = "You do not have any active loans")
                        Spacer(modifier = Modifier.height(40.dp))
                        Image(painter = painterResource(id = R.drawable.tree_branch_vector), contentDescription = "")
                        Spacer(modifier = Modifier.height(40.dp))

                        Button(onClick = { /*TODO*/ }) {
                            Text(text = "Click here to apply")
                        }
                    }
                },
                @Composable {
                    EmptyResults {
                        Text(text = "No loan history found")
                        Spacer(modifier = Modifier.height(40.dp))
                        Image(painter = painterResource(id = R.drawable.tree_branch_vector), contentDescription = "")
                        Spacer(modifier = Modifier.height(40.dp))

                        Button(onClick = { /*TODO*/ }) {
                            Text(text = "Click here to apply")
                        }
                    }
                }
            )
        }

        HorizontalUnderlinedNavigationBar(
            labels = listOf("Pending", "Active", "Completed").map {
                buildAnnotatedString {
                    withStyle(
                        SpanStyle(
                            fontWeight = FontWeight.Bold
                        )
                    ) {
                        append(it)
                    }
                }
            },
            onChange = { page ->
                coroutineScope.launch {
                    loanStatusPager.animateScrollToPage(page)
                }
                page
            }
        )

        HorizontalPager(state = loanStatusPager) { page ->
            loanStatusPages[page]()
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun LoansPreview() {
    Loans()
}