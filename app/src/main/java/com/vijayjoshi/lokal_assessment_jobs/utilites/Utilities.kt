package com.vijayjoshi.lokal_assessment_jobs.utilites

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.vijayjoshi.lokal_assessment_jobs.R
import com.vijayjoshi.lokal_assessment_jobs.api.ApiInstance
import com.vijayjoshi.lokal_assessment_jobs.api.ApiInterface
import com.vijayjoshi.lokal_assessment_jobs.model.Data

import retrofit2.Response


object Utilities {

    suspend fun getApiData(): Response<Data> {
        val response = ApiInstance.getInstance().create(ApiInterface::class.java).getData()
        return response
    }

    fun loadImage(context: Context, View: ImageView, source: String) {
        Glide.with(context).load(source)
            .into(View)
    }

    fun isInternetAvailable(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkCapabilities = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        return networkCapabilities?.run {
            hasTransport(NetworkCapabilities.TRANSPORT_WIFI) || hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
        } ?: false
    }
}