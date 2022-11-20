package com.timer.framework.mvvm.data.api

import com.timer.framework.mvvm.data.model.User
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("users")
    suspend fun getUsers(): Response<List<User>>

}