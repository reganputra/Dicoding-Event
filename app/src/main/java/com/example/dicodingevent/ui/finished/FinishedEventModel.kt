package com.example.dicodingevent.ui.finished

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.data.ApiConfig
import com.example.data.EventResponse
import com.example.data.ListEventsItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FinishedEventModel : ViewModel() {

    private val _listEvents = MutableLiveData<List<ListEventsItem>>()
    val listEvents: LiveData<List<ListEventsItem>> = _listEvents

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading


    init {
        getFinishedEvent()
    }

    private fun getFinishedEvent(){
        _isLoading.postValue(true)
        val client = ApiConfig.getApiService().getListEvents("0")

        client.enqueue(object: Callback<EventResponse>{
            override fun onResponse(
                call: Call<EventResponse>,
                response: Response<EventResponse>
            ) {
                _isLoading.postValue(false)
                val responseBody = response.body()
                if (response.isSuccessful){
                    _listEvents.value = responseBody?.listEvents
                }
            }
            override fun onFailure(call: Call<EventResponse>, t: Throwable) {
                _isLoading.postValue(false)
            }
        })
    }

}