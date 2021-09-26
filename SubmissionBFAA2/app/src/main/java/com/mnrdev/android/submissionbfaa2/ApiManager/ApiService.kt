package com.mnrdev.android.submissionbfaa2.ApiManager

import com.mnrdev.android.submissionbfaa2.ApiManager.response.*
import retrofit2.http.*
import retrofit2.Call

interface ApiService {
    @GET("search/users")
    @Headers("Authorization: token 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")
    fun getSearch(
        @Query("q") id: String
    ): Call<SearchResponse>

    @GET("users/{username}")
    fun getDetail(
        @Path("username") id: String
    ): Call<DetailResponse>

    @GET("users/{username}/followers")
    fun getFollower(
        @Query("username") id: String
    ): Call<UserResponse>

    @GET("users/{username}/following")
    fun getFollowing(
        @Query("username") id: String
    ): Call<UserResponse>
}