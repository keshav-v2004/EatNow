package com.example.eatnow.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.eatnow.R


@Entity
data class Food(
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,

    val name : Int,

    val imageId : Int,

    val cost : Int,

    var qty : Int = 0,

    val category : String,
)


