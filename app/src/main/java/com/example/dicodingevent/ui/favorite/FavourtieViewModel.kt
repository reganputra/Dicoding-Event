package com.example.dicodingevent.ui.favorite

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.data.local.Event
import com.example.data.local.FavouriteRepository

class FavourtieViewModel(application: Application): ViewModel() {

    private val mFavRepository: FavouriteRepository = FavouriteRepository(application)

    fun getAllFavEvent(): LiveData<List<Event>> = mFavRepository.getAllFavEvent()
}