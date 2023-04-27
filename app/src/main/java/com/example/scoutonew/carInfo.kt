package com.example.scoutonew

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("data")
data class carInfo(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val MakeID: Int,
    val MakeName: String,
)
