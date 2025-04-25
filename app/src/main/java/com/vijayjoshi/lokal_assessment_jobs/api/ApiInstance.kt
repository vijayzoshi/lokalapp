package com.vijayjoshi.lokal_assessment_jobs.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiInstance {

    fun getInstance() : Retrofit {

        val instance = Retrofit.Builder()
            .baseUrl("https://testapi.getlokalapp.com/common/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return instance
    }
}