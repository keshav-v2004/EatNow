package com.example.eatnow.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query



@Dao
interface FoodDao {

    @Query("select * from Food")
    fun getAllFood() : LiveData<List<Food>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun InsertIntoDatabase(food : Food)



    @Query("update Food set qty = qty+1 where id = :id")
    fun incQTY(id : Int)

    @Query("update Food set qty = qty-1 where id = :id")
    fun decQTY(id : Int)


    @Query("update Food set qty = 0 where id = :id")
    fun setQtyToZero(id : Int)



    @Query("select * from Food where category like :character")
    fun getEatables(character : String = "e") : LiveData<List<Food>>

    @Query("select * from Food where category like :character")
    fun getBeverages(character : String = "b") : LiveData<List<Food>>



}