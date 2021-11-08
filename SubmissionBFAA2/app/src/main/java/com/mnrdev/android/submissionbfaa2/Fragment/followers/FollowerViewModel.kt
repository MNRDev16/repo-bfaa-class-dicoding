package com.mnrdev.android.submissionbfaa2.Fragment.followers

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

class FollowerViewModel : ViewModel() {

    private val _followersData = MutableLiveData<List<ItemsItem>>()
    val followersData : LiveData<List<ItemsItem>> = _followersData

    private val _login = MutableLiveData<String>()
    val login : LiveData<String> = _login

    private val _loading = MutableLiveData<Boolean>()
    val loading : LiveData<Boolean> = _loading

    private val _failed = MutableLiveData<Boolean>()
    val failed : LiveData<Boolean> = _failed

    var message = "Request Failed"

    fun setUserLogin(userLogin : String){
        _login.value = userLogin
    }
    fun getFollowingData(username : String){
        _failed.value = false
        _loading.value = true
        val client = ApiConfig.getApiService().getFollower(username)
        client.enqueue(object : Callback<List<ItemsItem>> {
            override fun onResponse(
                call: Call<List<ItemsItem>>,
                response: Response<List<ItemsItem>>
            ) {
                _loading.value = false
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        if(responseBody.isNotEmpty()){
                            _followersData.value = responseBody!!
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
                _loading.value = false
                message = "Request Failed"
                _failed.value = true
            }
        })
    }
}