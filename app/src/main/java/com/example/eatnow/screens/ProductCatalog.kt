package com.example.eatnow.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonElevation
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.eatnow.DisplayedData
import com.example.eatnow.MasterApp
import com.example.eatnow.db.Food


@Composable
fun ProdList(
    viewModel: AppViewModel,
    prod_catalog : String,
    navController: NavController,
    modifier: Modifier = Modifier
) {

    val eatables by viewModel.getEatables().observeAsState()

    val beverages by viewModel.getBeverages().observeAsState()

    val cartStatus by viewModel.cartStatus.observeAsState()

    Scaffold(
        topBar = {
            if (prod_catalog == "e"){
                ProdCatalogTopAppBar(
                    category = "Eatables",
                    cartNumberItems = viewModel.getCartStatus(cartStatus)
                )
            }
            else{
                ProdCatalogTopAppBar(
                    category = "Beverages",
                    cartNumberItems = viewModel.getCartStatus(cartStatus)
                )
            }
            
        },
        bottomBar = {
            ProdCatalogBottomBar(navController = navController , bottomAppBarText = "Checkout" , isThisCartScreen = false , viewModel = viewModel )
        }
    ){
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .padding(paddingValues = it)
                .fillMaxWidth()
        ) {
//            Button(
//                onClick = { navController.navigate(route = MasterApp.CartScreen.name) }
//            ) {
//                Text(text = "go to cart")
//            }
//
//            Spacer(modifier = modifier.height(60.dp))

            if (prod_catalog == "e") {
                eatables?.let {
                    LazyColumn(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = modifier
                            .fillMaxWidth()
                    ) {
                        items(eatables!!){
                            EachProdCard(element = it , viewModel = viewModel)
                        }
                    }
                }
            }
            else{
                beverages?.let {
                    LazyColumn(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = modifier
                            .fillMaxWidth()
                    ) {
                        items(beverages!!){
                            EachProdCard(element = it , viewModel = viewModel)
                        }
                    }
                }
            }

//        LazyColumn(
//            verticalArrangement = Arrangement.Center,
//            horizontalAlignment = Alignment.CenterHorizontally,
//            modifier = modifier
//                .fillMaxWidth()
//        ) {
//            items(DisplayedData.getBeverages()){
//                EachProdCard(element = it , viewModel = viewModel)
//            }
//        }
        }
    }






}

@Composable
fun EachProdCard(
    viewModel: AppViewModel,
    element : Food,
    modifier: Modifier = Modifier
) {

    Card(
        modifier = modifier
            .clip(RoundedCornerShape(topStart = 10.dp, bottomEnd = 10.dp))
            .padding(10.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = modifier
                .fillMaxWidth()
                .padding(10.dp)

        ) {
            Image(
                painter = painterResource(id = element.imageId),
                contentDescription = null ,
                contentScale = ContentScale.Crop,
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
                fontWeight = FontWeight.ExtraBold,
                modifier = modifier

            )


            //add to cart or Plus minus button shown
            
            if(element.qty == 0) {
                Button(
                    onClick = {
                        viewModel.insertIntoDatabase(insertThis = element)
                        viewModel.incProdQty(id = element.id) },
                    elevation = ButtonDefaults.buttonElevation(pressedElevation = 50.dp),

                ) {
                    Text(
                        text = "add to cart",
                        textAlign = TextAlign.Justify,
                        fontWeight = FontWeight.Medium
                    )
                }


            }
            else{
                ChangeQtyBtn(
                    incQtybyOne = {viewModel.incProdQty(id = element.id)},
                    decQtybyOne = {viewModel.decProdQty(id = element.id)},
                    prodQty = element.qty.toString()
                )
            }
        }
    }
}



@Composable
fun ChangeQtyBtn(
    decQtybyOne : ()->Unit,
    incQtybyOne : ()->Unit,
    prodQty : String,

) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .padding(5.dp)
            .border(2.dp, Color.Gray, CircleShape)

    ) {
        IconButton(
            onClick = decQtybyOne
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = null
            )
        }

        //product qty displayed here
        Text(
            text = prodQty,
            fontWeight = FontWeight.Medium
        )

        IconButton(
            onClick = incQtybyOne
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                contentDescription = null
            )
        } 
    }
}


