package com.example.scoutonew

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class repository(private val dataInterface: dataInterface, private val dao: myDao,private val newDao: newDao) {


    private val modellivedata = MutableLiveData<ArrayList<Result>>()

    val getlivedata: LiveData<ArrayList<Result>>
        get() = modellivedata

    val getlivedataforCarInfo = dao.getcarinfofromtable()


    suspend fun getdata(){
      val response = dataInterface.getdata()
       if(response.body()!=null){
           dao.insert(response.body()!!.Results as ArrayList<Result>)
           val getMyData = dao.getdata()
           modellivedata.postValue(getMyData as ArrayList<Result>?)

               // modellivedataforcarInfo.postValue(dao.getcarinfofromtable() as ArrayList<carInfo>?)
       }
    }
    suspend fun getCarInfo(carInfo: carInfo){
        dao.insertcarinfo(carInfo)
    }
    suspend fun delete(carInfo: carInfo){
        dao.delete(carInfo)
    }
}