package com.example.eatnow.screens

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eatnow.MainApplication
import com.example.eatnow.db.Food
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AppViewModel : ViewModel() {

    val foodDao = MainApplication.foodDatabase.getFoodDao()

    val cartStatus = foodDao.getAllFood()

    fun insertIntoDatabase(insertThis : Food) {
        viewModelScope.launch(Dispatchers.IO) {
            foodDao.InsertIntoDatabase(insertThis)
        }
    }

    fun incProdQty(id : Int) {
        viewModelScope.launch(Dispatchers.IO) {
            foodDao.incQTY(id)
        }
    }

    fun decProdQty(id : Int) {
        viewModelScope.launch(Dispatchers.IO) {
            foodDao.decQTY(id)
        }
    }

    fun setQtyZero(id : Int) {
        viewModelScope.launch(Dispatchers.IO) {
            foodDao.setQtyToZero(id)
        }
    }

    fun getEatables(): LiveData<List<Food>> {
        return foodDao.getEatables()
    }

    fun getBeverages(): LiveData<List<Food>> {
        return foodDao.getBeverages()
    }



    fun calculateTotalPrice(cartStatus :  List<Food>?) : Int {
        var price : Int = 0

        if (cartStatus != null) {
            for (i in cartStatus){
                if(i.qty !=0) {
                    price = price + (i.qty * i.cost)
                }
            }
        }

        return price

    }


}