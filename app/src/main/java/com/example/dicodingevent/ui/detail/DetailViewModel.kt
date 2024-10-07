package com.example.dicodingevent.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.data.ApiConfig
import com.example.data.DetailEventResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class DetailViewModel {

   private val _detailEvent = MutableLiveData<DetailEventResponse?>()
    val detailEvent: LiveData<DetailEventResponse?> = _detailEvent

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading


    private fun getDetailEvent(id: Int){
        _isLoading.postValue(true)
        val client = ApiConfig.getApiService().getEventDetail(id)

        client.enqueue(object: Callback<DetailEventResponse> {
            override fun onResponse(
                call: Call<DetailEventResponse>,
                response: Response<DetailEventResponse>
            ) {
                _isLoading.postValue(false)
                val responseBody = response.body()
                if (response.isSuccessful){
                    _detailEvent.value = responseBody
                }
        }
            override fun onFailure(call: Call<DetailEventResponse>, t: Throwable) {
                _isLoading.postValue(false)
            }
        })

    }

}