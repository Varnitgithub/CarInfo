package com.example.scoutonew

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("Car_Info")
data class Result(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val Make_ID: Int,
    val Make_Name: String
)