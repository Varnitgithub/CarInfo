package com.example.scoutonew

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface myDao {

    @Insert(onConflict = (OnConflictStrategy.IGNORE))
    suspend fun insert(result:ArrayList<Result>)

    @Query("Select * from Car_Info")
   fun getdata():List<Result>

 @Insert(onConflict = (OnConflictStrategy.IGNORE))
    suspend fun insertcarinfo(carInfo: carInfo)

    @Query("Select * from data")
   fun getcarinfofromtable():LiveData<List<carInfo>>

   @Delete
   suspend fun delete(carInfo: carInfo):Unit


}