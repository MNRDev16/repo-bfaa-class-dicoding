package com.mnrdev.android.submissionbfaa2

import retrofit2.http.*
import retrofit2.Call

interface ApiService {
    @GET("search/users")
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
    ): Call<Response>

    @GET("users/{username}/following")
    fun getFollowing(
        @Query("username") id: String
    ): Call<Response>
}