package com.mnrdev.android.submissionbfaa2.ApiManager

import com.mnrdev.android.submissionbfaa2.ApiManager.response.*
import retrofit2.http.*
import retrofit2.Call

interface ApiService {
    companion object{
        const val token = "token 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"
    }

    @GET("search/users")
    fun getSearch(
        @Header("Authorization") token : String,
        @Query("q") id: String
    ): Call<SearchResponse>

    @GET("users/{username}")
    fun getDetail(
        @Path("username") id: String
    ): Call<DetailResponse>

    @GET("users/{username}/followers")
    fun getFollower(
        @Path("username") id: String
    ): Call<List<ItemsItem>>

    @GET("users/{username}/following")
    fun getFollowing(
        @Path("username") id: String
    ): Call<List<ItemsItem>>
}