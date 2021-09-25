package com.mnrdev.android.submissionbfaa2.Fragment.main

import android.service.controls.ControlsProviderService
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mnrdev.android.submissionbfaa2.ApiManager.ApiConfig
import com.mnrdev.android.submissionbfaa2.ApiManager.response.SearchResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {

    private val _searchData = MutableLiveData<SearchResponse>()
    val searchData : LiveData<SearchResponse> = _searchData

    private val _loading = MutableLiveData<Boolean>()
    val loading : LiveData<Boolean> = _loading

    fun getSearchData(username : String) {
        _loading.value = true
        val client = ApiConfig.getApiService().getSearch(username)
        client.enqueue(object : Callback<SearchResponse> {
            override fun onResponse(
                call: Call<SearchResponse>,
                response: Response<SearchResponse>
            ) {
                _loading.value = false
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        _searchData.value = responseBody!!
                    }
                } else {
                    Log.e(ControlsProviderService.TAG, "onFailure: ${response.message()}")
                }
            }
            override fun onFailure(call: Call<SearchResponse>, t: Throwable) {
                _loading.value = false
                Log.e(ControlsProviderService.TAG, "onFailure: ${t.message}")
            }
        })
    }
}