package com.phanes.phanesbank.ui

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.ui.layout.Layout
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.State
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.phanes.phanesbank.R
import com.phanes.phanesbank.screens.NavRoutes
import com.phanes.phanesbank.screens.UseNavigate

@Composable
fun TopNavigationBar(

) {

}

@Composable
fun BottomNavigationBar(
    navHostController: NavHostController
) {

    val navIcons = listOf(
        NavRoutes.Home to Icons.Outlined.Home,
        NavRoutes.Groups to ImageVector.vectorResource(id = R.drawable.outline_groups_24),
        NavRoutes.Loans to ImageVector.vectorResource(id = R.drawable.outline_monetization_on_24),
        NavRoutes.Wallet to ImageVector.vectorResource(id = R.drawable.outline_account_balance_wallet_24)
    )

    val backStackEntry by navHostController.currentBackStackEntryAsState();

    val currentRoute = backStackEntry?.destination?.route

    BottomAppBar {
        navIcons.forEach { (route, icon) ->
            NavigationBarItem(
                selected = currentRoute == route.route,
                onClick = {
                          UseNavigate.navigate(
                              navHostController,
                              route
                          )
                },
                icon = {
                    Icon(
                        imageVector = icon,
                        contentDescription = route.route
                    )
                },
                label = {
                    Text(
                        text = route.label
                    )
                }
            )
        }
    }
}

@Composable
fun HorizontalUnderlinedNavigationBar(
    modifier: Modifier = Modifier,
    onChange: (Int) -> Int,
    labels: List<AnnotatedString>
) {

    var selectedIndex by remember {
        mutableIntStateOf(0)
    }

    ConstraintLayout(
        modifier = Modifier
            .then(modifier)
            .wrapContentHeight()
            .fillMaxWidth()
            .padding(5.dp)
    )
    {
        val refs = labels.map { createRef() }

        createHorizontalChain(*refs.toTypedArray())

        refs.forEachIndexed { index, ref ->
            UnderlinedNavigationBarItem(
                selected = index == selectedIndex,
                modifier = Modifier
                    .constrainAs(ref) {
                        centerVerticallyTo(parent)
                    },
                label = labels[index],
                onClick = {
                    selectedIndex = onChange(index)
                }
            )
        }
    }
}

@Composable
fun UnderlinedNavigationBarItem(
    modifier: Modifier = Modifier,
    selected: Boolean = false,
    label: AnnotatedString,
    onClick: () -> Unit
) {

    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(50))
            .clickable {
                onClick()
            }
            .then(modifier)
            .width(IntrinsicSize.Max)
            .padding(horizontal = 15.dp, vertical = 5.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = label,
            color = if(selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onBackground
        )

        Box(
            modifier = Modifier
                .height(4.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.dp))
                .background(color = if (selected) MaterialTheme.colorScheme.primary else Color.Transparent)
        )
    }
}