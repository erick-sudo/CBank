package com.phanes.phanesbank.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.phanes.phanesbank.ui.EmptyResults
import com.phanes.phanesbank.R

@Composable
fun Groups() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            ,
        verticalArrangement = Arrangement.Center
    ) {

        EmptyResults {
            Text(text = "You haven't joined any groups yet\nJoin groups to access larger loans")
            Spacer(modifier = Modifier.height(40.dp))
            Image(painter = painterResource(id = R.drawable.tree_branch_vector), contentDescription = "")
            Spacer(modifier = Modifier.height(40.dp))

            Button(onClick = { /*TODO*/ }) {
                Text(text = "Join Groups")
            }
        }
    }
}