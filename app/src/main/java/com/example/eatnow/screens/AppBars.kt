package com.example.eatnow.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.eatnow.MasterApp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EntryScreenAppBar(
    modifier: Modifier = Modifier
) {
    CenterAlignedTopAppBar(
        modifier = modifier
            .statusBarsPadding()
            .padding(2.dp)
//            .clip(RoundedCornerShape(10.dp))
            .shadow(elevation = 1.dp),
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Eat Now!",
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 35.sp,
                    modifier = modifier
                        .weight(1f)
                )
            }
        }
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProdCatalogTopAppBar(
    cartNumberItems : String,
    category : String,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        modifier = modifier
            .statusBarsPadding()
            .padding(2.dp)
            .shadow(elevation = 5.dp),
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = category,
                    fontSize = 35.sp,
                    fontWeight = FontWeight.Medium,

                )

                BadgedBox(
                    badge = {
                        Text(
                            text = cartNumberItems,
                            color = Color.Red,
                            fontWeight = FontWeight.Bold
                        )
                    }
                ) {
                    IconButton(
                        onClick = { }
                    ) {
                        Icon(
                            imageVector = Icons.Default.ShoppingCart,
                            contentDescription = null,
                            modifier = modifier
                                .size(48.dp)
                        )
                    }
                }
            }
        }
    )
}

@Composable
fun ProdCatalogBottomBar(
    viewModel: AppViewModel,
    isThisCartScreen : Boolean,
    navController: NavController,
    bottomAppBarText : String,
    modifier: Modifier = Modifier
) {
    val collector by viewModel.cartStatus.observeAsState()
    BottomAppBar(
        content = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = modifier
                    .fillMaxWidth()
            ) {
                IconButton(
                    onClick = {navController.popBackStack()}
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Default.ArrowBack, contentDescription = null
                    )
                }

                if (isThisCartScreen) {
                    if (viewModel.getCartStatus(collector) != "0") {
                        OutlinedButton(
                            onClick = { navController.navigate(route = MasterApp.CartScreen.name) }
                        ) {
                            Text(
                                text = bottomAppBarText + " ₹${viewModel.calculateTotalPrice(collector)}",
                                fontSize = 25.sp
                            )
                        }
                    }
                }
                else{
                    OutlinedButton(
                        onClick = { navController.navigate(route = MasterApp.CartScreen.name) }
                    ) {
                        Text(
                            text = bottomAppBarText,
                            fontSize = 25.sp
                        )
                    }
                }
            }
        }
    )
}

@Preview(showSystemUi = true)
@Composable
private fun Preview() {
//    EntryScreenAppBar()

//    ProdCatalogTopAppBar()
    
//    ProdCatalogBottomBar()
}