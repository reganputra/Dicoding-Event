package com.example.dicodingevent.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.data.ApiConfig
import com.example.data.Responses
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class DetailViewModel: ViewModel() {

   private val _detailEvent = MutableLiveData<Responses?>()
    val detailEvent: LiveData<Responses?> = _detailEvent

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading


     fun getDetailEvent(id: Int){
        _isLoading.postValue(true)
        val client = ApiConfig.getApiService().getEventDetail(id)

        client.enqueue(object: Callback<Responses> {
            override fun onResponse(
                call: Call<Responses>,
                response: Response<Responses>
            ) {
                _isLoading.postValue(false)
                val responseBody = response.body()
                if (response.isSuccessful){
                    _detailEvent.value = responseBody
                }
        }
            override fun onFailure(call: Call<Responses>, t: Throwable) {
                _isLoading.postValue(false)
            }
        })

    }

}