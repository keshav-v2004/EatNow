package com.example.eatnow.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.eatnow.DisplayedData
import com.example.eatnow.MasterApp
import com.example.eatnow.R
import com.example.eatnow.whichScreenToGo

@Composable
fun StartScreen(
    navController: NavController,
    modifier: Modifier = Modifier) {

    Scaffold(
        topBar = {
            EntryScreenAppBar()
        }
    ){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .padding(paddingValues = it)
                .fillMaxSize()
                .statusBarsPadding()
        ) {
            Spacer(modifier = modifier.height(25.dp))

            Text(
                text = "BreakTime",
                fontSize = 45.sp
            )
            Text(
                text = "Study\nOrder\nRun to Lab\nRepeat ↺",
                fontSize = 40.sp,
                lineHeight = 40.sp
            )

            Spacer(modifier = modifier.height(45.dp))

            Text(
                text = "What do you want to order today ?",
                fontSize = 30.sp,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center,
                modifier = modifier

            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = modifier
                    .weight(1f)
                    .padding(5.dp)
            ) {
                Card(
                    modifier = modifier
                        .size(200.dp)
                        .padding(10.dp)
                ) {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = modifier
                    ) {
                        Text(
                            text = "Eatables",
                            fontSize = 25.sp,
                            fontWeight = FontWeight.Medium
                        )
                        Image(
                            painter = painterResource(id = R.drawable.eatables_front_page),
                            contentDescription = null ,  contentScale = ContentScale.Crop,
                            modifier = modifier
                                .size(180.dp)
                                .clickable {
                                    whichScreenToGo = "e"
                                    navController.navigate(route = MasterApp.Prod_catalog.name)
                                }
                        )


                    }
                }

                Card (
                    modifier = modifier
                        .size(200.dp)
                        .padding(10.dp)
                ){
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = modifier
                    ) {
                        Text(
                            text = "Beverages",
                            fontSize = 25.sp,
                            fontWeight = FontWeight.Medium
                        )
                        Image(painter = painterResource(id = R.drawable.drinkables_front_page),
                            contentDescription = null, contentScale = ContentScale.Crop,
                            modifier = modifier
                                .size(180.dp)
                                .clickable {
                                    whichScreenToGo = "b"
                                    navController.navigate(route = MasterApp.Prod_catalog.name)
                                }
                        )

                    }
                }
            }
        }
    }


}

@Preview
@Composable
private fun Pre() {
//    StartScreen()
}