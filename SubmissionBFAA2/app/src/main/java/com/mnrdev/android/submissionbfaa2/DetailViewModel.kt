package com.mnrdev.android.submissionbfaa2

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mnrdev.android.submissionbfaa2.ApiManager.ApiConfig
import com.mnrdev.android.submissionbfaa2.ApiManager.response.DetailResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailViewModel : ViewModel() {

    private val _detailUser = MutableLiveData<DetailResponse>()
    val detailUser : LiveData<DetailResponse> = _detailUser

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading : LiveData<Boolean> = _isLoading

    private val _failureText = MutableLiveData<Event<String>>()
    val failureText : LiveData<Event<String>> = _failureText

    fun getDetailUser(username : String){
        _isLoading.value = true
        val client = ApiConfig.getApiService().getDetail(username)
        client.enqueue(object : Callback<DetailResponse> {
            override fun onResponse(
                call: Call<DetailResponse>,
                response: Response<DetailResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        _detailUser.value = responseBody!!
                    }
                } else {
                    _failureText.value = Event("onFailure: ${response.message()}")
                }
            }
            override fun onFailure(call: Call<DetailResponse>, t: Throwable) {
                _isLoading.value = false
                _failureText.value = Event("onFailure: ${t.message}")
            }
        })
    }
}