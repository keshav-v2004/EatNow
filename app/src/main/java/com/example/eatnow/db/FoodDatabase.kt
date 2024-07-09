package com.example.eatnow.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [Food::class],
    version = 4,
    exportSchema = false
)
abstract class FoodDatabase : RoomDatabase() {

    abstract fun getFoodDao() : FoodDao

    companion object {
        const val NAME = "FOOD_DB"
    }

}