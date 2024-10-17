package com.example.dicodingevent.ui.favorite

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.data.local.Event
import com.example.data.local.FavouriteRepository

class FavourtieViewModel(application: Application): ViewModel() {

    private val mFavRepository: FavouriteRepository = FavouriteRepository(application)

    fun insert(event: Event) {
        mFavRepository.insert(event)
    }

    fun delete(event: Event) {
        mFavRepository.delete(event)
    }

    fun getAllFavEvent(): LiveData<List<Event>> = mFavRepository.getAllFavEvent()
}