package com.example.scoutonew

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface newDao {

    @Insert(onConflict = (OnConflictStrategy.IGNORE))
    suspend fun insertcarinfo(carInfo: carInfo)

    @Query("Select * from data")
    fun getcarinfo():LiveData<List<carInfo>>
}