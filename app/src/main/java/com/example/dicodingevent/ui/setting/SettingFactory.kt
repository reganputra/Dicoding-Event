package com.example.dicodingevent.ui.setting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.data.ViewModelFactory

class SettingFactory(private val ref: SettingDataStore) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SettingViewModel::class.java)) {
            return SettingViewModel(ref) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class" + modelClass.name)
    }
}