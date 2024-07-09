package com.example.eatnow

import android.app.Application
import androidx.room.Room
import com.example.eatnow.db.FoodDatabase

class MainApplication : Application() {

    companion object{
        lateinit var foodDatabase: FoodDatabase
    }

    override fun onCreate() {
        super.onCreate()

        foodDatabase = Room.databaseBuilder(
            applicationContext,
            FoodDatabase::class.java,
            FoodDatabase.NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }

}