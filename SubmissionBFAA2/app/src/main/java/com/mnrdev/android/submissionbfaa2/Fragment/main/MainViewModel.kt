package com.mnrdev.android.submissionbfaa2.Fragment.main

import android.service.controls.ControlsProviderService
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mnrdev.android.submissionbfaa2.ApiManager.ApiConfig
import com.mnrdev.android.submissionbfaa2.ApiManager.ApiService
import com.mnrdev.android.submissionbfaa2.ApiManager.response.ItemsItem
import com.mnrdev.android.submissionbfaa2.ApiManager.response.SearchResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {

    private val _searchData = MutableLiveData<List<ItemsItem>>()
    val searchData : LiveData<List<ItemsItem>> = _searchData

    private val _loading = MutableLiveData<Boolean>()
    val loading : LiveData<Boolean> = _loading

    private val _failed = MutableLiveData<Boolean>()
    val failed : LiveData<Boolean> = _failed

    var message = "Request failed"

    init{
        getSearchData("\"\"")
    }
    fun getSearchData(username : String) {
        _failed.value = false
        _loading.value = true
        val client = ApiConfig.getApiService().getSearch(ApiService.token,username)
        client.enqueue(object : Callback<SearchResponse> {
            override fun onResponse(
                call: Call<SearchResponse>,
                response: Response<SearchResponse>
            ) {
                _loading.value = false
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        if(responseBody.items.isNotEmpty()){
                            _searchData.value = responseBody.items
                        }else{
                            _failed.value = true
                            message = "User Not Found"
                        }
                    }
                } else {
                    _failed.value = true
                    message = "Request failed"
                }
            }
            override fun onFailure(call: Call<SearchResponse>, t: Throwable) {
                _loading.value = false
                _failed.value = true
                message = "Request failed"
            }
        })
    }
}