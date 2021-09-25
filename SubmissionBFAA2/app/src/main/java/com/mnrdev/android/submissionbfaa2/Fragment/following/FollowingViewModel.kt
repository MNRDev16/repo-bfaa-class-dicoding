package com.mnrdev.android.submissionbfaa2.Fragment.following

import android.service.controls.ControlsProviderService
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mnrdev.android.submissionbfaa2.ApiManager.ApiConfig
import com.mnrdev.android.submissionbfaa2.ApiManager.response.ItemsItem
import com.mnrdev.android.submissionbfaa2.ApiManager.response.SearchResponse
import com.mnrdev.android.submissionbfaa2.ApiManager.response.UserResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FollowingViewModel : ViewModel() {

    private val _followingData = MutableLiveData<UserResponse>()
    val followingData : LiveData<UserResponse> = _followingData

    fun getFollowingData(username : String){
        val client = ApiConfig.getApiService().getFollowing(username)
        client.enqueue(object : Callback<UserResponse> {
            override fun onResponse(
                call: Call<UserResponse>,
                response: Response<UserResponse>
            ) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        _followingData.value = responseBody!!
                    }
                } else {
                    Log.e(ControlsProviderService.TAG, "onFailure: ${response.message()}")
                }
            }
            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                Log.e(ControlsProviderService.TAG, "onFailure: ${t.message}")
            }
        })
    }
}