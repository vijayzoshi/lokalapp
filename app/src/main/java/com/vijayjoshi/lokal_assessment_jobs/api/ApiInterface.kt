package com.vijayjoshi.lokal_assessment_jobs.api

import com.vijayjoshi.lokal_assessment_jobs.model.Data
import com.vijayjoshi.lokal_assessment_jobs.model.Result
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {
        @GET("jobs?page=1")
        suspend fun getData() : Response<Data>
    }