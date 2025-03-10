package com.example.eatnow.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.eatnow.db.Food

@Composable
fun CartScreen(
    navController: NavController,
    viewModel: AppViewModel,
    modifier: Modifier = Modifier
) {
    val cart by viewModel.cartStatus.observeAsState()

    Scaffold(
        bottomBar = {
            ProdCatalogBottomBar(
                navController = navController,
                bottomAppBarText = "Pay Now" ,
                isThisCartScreen = true ,
                viewModel = viewModel
            )
        }
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .padding(paddingValues = it)
                .statusBarsPadding()

        ) {
            Text(
                text = "Your Cart",
                fontSize = 45.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xff003566)
            )
            
            Spacer(modifier = modifier.height(60.dp))
            if (viewModel.calculateTotalPrice(cart) ==0 ){
                Text(
                    text = "Nothing Added In Cart",
                    textAlign = TextAlign.Center,
                    color = Color(0xff3a0ca3),
                    lineHeight = 55.sp,
                    fontSize = 50.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = modifier
                        .weight(1f)
                )
            }
            else{
                cart?.let {
                    LazyColumn {
                        items(cart!!) {
                            if (it.qty != 0){
                                EachCartProduct(
                                    element = it,
                                    incQtyByOne = {viewModel.incProdQty(id = it.id)},
                                    decQtyByOne = {viewModel.decProdQty(id = it.id)},
                                    EachprodTotal = viewModel.getEachTotalPrice(element = it)
                                )
                            }
                        }
                    }
                }
            }

        }
    }


}

@Composable
fun EachCartProduct(
    decQtyByOne : ()->Unit,
    incQtyByOne : ()->Unit,
    EachprodTotal : String,
    element : Food,
    modifier: Modifier = Modifier
)
{
    Card(
        modifier = modifier
            .padding(10.dp)
            .clip(RoundedCornerShape(10.dp))
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = modifier
                .fillMaxWidth()

        ) {
            Image(
                painter = painterResource(id = element.imageId),
                contentDescription = null,
                modifier = modifier
                    .size(100.dp)
                    .clip(RoundedCornerShape(10.dp))
            )

            Text(
                text = stringResource(id = element.name),
                fontSize = 25.sp,
                fontWeight = FontWeight.Medium,
                modifier = modifier
                    .weight(1f)

            )

            Text(
                text = "₹"+element.cost.toString(),
                fontSize = 25.sp,
                fontWeight = FontWeight.Medium,
                modifier = modifier
            )

            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = modifier

            ) {
                ChangeQtyBtn(
                    decQtybyOne = decQtyByOne,
                    incQtybyOne = incQtyByOne,
                    prodQty = element.qty.toString()
                )

                Text(
                    text = EachprodTotal,
                    fontSize = 25.sp,
                    fontWeight = FontWeight.ExtraBold,

                )
            }

        }
    }
}