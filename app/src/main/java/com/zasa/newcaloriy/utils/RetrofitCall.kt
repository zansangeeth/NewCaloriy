package com.zasa.newcaloriy.utils

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Sangeeth Amirthanathan
 * on 10/12/2022.
 */
object RetrofitCall {
    val retrofit =
        Retrofit.Builder().baseUrl(Constants.BASE_URL).addConverterFactory(GsonConverterFactory.create())
            .build()
}