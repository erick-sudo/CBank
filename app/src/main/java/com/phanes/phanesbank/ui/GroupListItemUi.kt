package com.phanes.phanesbank.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.phanes.phanesbank.R
import com.phanes.phanesbank.ui.models.GroupListItem

@Composable
fun GroupListItemUi(
    groupListItem: GroupListItem
) {

    var menuExpanded by remember {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier
            .padding(start = 10.dp, end = 10.dp, top = 10.dp)
    ) {
        Text(
            text = groupListItem.name,
            style = TextStyle(
                fontWeight = FontWeight.Bold
            )
        )

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier
                    .size(80.dp)
                    .padding(10.dp)
                    .clip(RoundedCornerShape(50)),
                painter = painterResource(id = R.drawable.lady),
                contentDescription = groupListItem.name
            )

            Column(
                modifier = Modifier
                    .weight(1f),
            ) {
//                Text(
//                    text = "Credit Score"
//                )

                AnimatedCreditScoreIndicator(
                    modifier = Modifier,
                    creditScore = groupListItem.creditScore
                )

//                Text(
//                    text = buildAnnotatedString {
//                        withStyle(
//                            SpanStyle(
//                                fontWeight = FontWeight.Bold
//                            )
//                        ) {
//                            append(groupListItem.numberOfMembers.toString())
//                        }
//                        append(" ")
//                        withStyle(
//                            SpanStyle(
//                                fontWeight = FontWeight.ExtraLight
//                            )
//                        ) {
//                            append("Members")
//                        }
//                    }
//                )
            }

            Box(modifier = Modifier) {
                Icon(
                    modifier = Modifier
                        .clickable {
                            menuExpanded = true
                        },
                    imageVector = Icons.Filled.MoreVert,
                    contentDescription = "More"
                )

                DropdownMenu(expanded = menuExpanded, onDismissRequest = { menuExpanded = false }) {
                    listOf("One", "Two", "Three", "Four").forEach { txt ->
                        DropdownMenuItem(text = { Text(text = txt) }, onClick = { /*TODO*/ })
                    }
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun GroupListItemUiPreview() {

}