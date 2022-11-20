package com.timer.framework.mvvm.data.repository

import com.timer.framework.mvvm.data.api.ApiHelper

class MainRepository (private val apiHelper: ApiHelper) {

    suspend fun getUsers() =  apiHelper.getUsers()

}