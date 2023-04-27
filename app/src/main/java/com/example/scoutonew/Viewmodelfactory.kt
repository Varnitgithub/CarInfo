package com.example.scoutonew

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class Viewmodelfactory(val repository: repository):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return viewmodel(repository) as T
    }
}