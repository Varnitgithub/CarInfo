package com.example.scoutonew

import android.net.LinkAddress
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.scoutonew.Result
import com.example.scoutonew.repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class viewmodel(private val repository: repository):ViewModel() {

 var livecarinfo =  repository.getlivedataforCarInfo


    init{
        viewModelScope.launch(Dispatchers.IO) {
                repository.getdata()

            }

        }
    suspend fun givecarinfo(carInfo: carInfo){
        repository.getCarInfo(carInfo)
    }
    suspend fun delete(carInfo: carInfo){
        repository.delete(carInfo)
    }
    val viewModelLiveData: LiveData<ArrayList<Result>> = repository.getlivedata
}