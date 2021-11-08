package com.mnrdev.android.submissionbfaa2.Fragment.following

import android.service.controls.ControlsProviderService
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mnrdev.android.submissionbfaa2.ApiManager.ApiConfig
import com.mnrdev.android.submissionbfaa2.ApiManager.response.ItemsItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FollowingViewModel : ViewModel() {

    private val _followingData = MutableLiveData<List<ItemsItem>>()
    val followingData : LiveData<List<ItemsItem>> = _followingData

    private val _login = MutableLiveData<String>()
    val login : LiveData<String> = _login

    private val _failed = MutableLiveData<Boolean>()
    val failed : LiveData<Boolean> = _failed

    var message = "Request Failed"

    fun setUserLogin(userLogin : String){
        _login.value = userLogin
    }

    fun getFollowingData(username : String){
        _failed.value = false
        val client = ApiConfig.getApiService().getFollowing(username)
        client.enqueue(object : Callback<List<ItemsItem>> {
            override fun onResponse(
                call: Call<List<ItemsItem>>,
                response: Response<List<ItemsItem>>
            ) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        if(responseBody.isNotEmpty()){
                            _followingData.value = responseBody!!
                        }else{
                            message = "EMPTY"
                            _failed.value = true
                        }
                    }
                } else {
                    message = "Request Failed"
                    _failed.value = true
                }
            }
            override fun onFailure(call: Call<List<ItemsItem>>, t: Throwable) {
                message = "Request Failed"
                _failed.value = true
            }
        })
    }
}