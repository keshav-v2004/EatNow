package com.example.eatnow

import com.example.eatnow.db.Food

object DisplayedData {

    fun getEatables() : List<Food>{
        return listOf<Food>(
            Food(name = R.string.blue_lays, imageId =  R.drawable.blue_lays, cost =  20 , category = "e"),
            Food(name = R.string.green_lays, imageId =  R.drawable.green_lays, cost =  40, category = "e"),
            Food(name = R.string.hot_dog, imageId =  R.drawable.hot_dog, cost = 50, category = "e"),
            Food(name = R.string.cheese_maggie, imageId =  R.drawable.cheese_maggie,cost =  50, category = "e" ),
            Food(name = R.string.veg_burger, imageId =  R.drawable.veg_burger, cost = 30, category = "e"),
            Food(name = R.string.masala_maggie,imageId =   R.drawable.masala_maggie, cost = 40, category = "e"),
            Food(name = R.string.mad_angle_1,imageId =   R.drawable.mad_angle_1, cost = 20, category = "e" ),
            Food(name = R.string.mad_angle_2, imageId =  R.drawable.mad_angle_2, cost = 20, category = "e"),
            Food(name = R.string.red_lays, imageId =  R.drawable.red_lays, cost = 20, category = "e" ),
            Food(name = R.string.sandwich, imageId =  R.drawable.sandwich, cost = 30, category = "e" ),
        )
    }

    fun getBeverages() : List<Food>{
        return listOf<Food>(
            Food(name = R.string.tea,imageId = R.drawable.tea, cost = 10, category = "b" ),
            Food(name = R.string.sprite, imageId =R.drawable.sprite, cost = 40, category = "b"  ),
            Food(name = R.string.lassi, imageId =R.drawable.lassi,cost =  30, category = "b"),
            Food(name = R.string.pepsi, imageId =R.drawable.pepsi, cost = 40, category = "b"),
            Food(name = R.string.amul_kool_coffee,imageId = R.drawable.amul_kool_coffee,cost =  40, category = "b" ),
            Food(name = R.string.amul_kool_kesar, imageId =R.drawable.amul_kool_kesar,cost =  40, category = "b"),
            Food(name = R.string.coca_cola, imageId =R.drawable.coca_cola,cost =  40, category = "b"),
            Food(name = R.string.elaichi, imageId =R.drawable.amul_kool_elaichi,cost =  40, category = "b"),
            Food(name = R.string.red_bull, imageId =R.drawable.red_bull,cost =  100, category = "b" ),
            Food(name = R.string.hot_coffee, imageId =R.drawable.coffee, cost = 20, category = "b"),
        )
    }
}