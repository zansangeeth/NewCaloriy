package com.zasa.newcaloriy

import retrofit2.http.GET
import retrofit2.http.Query

/**
 **@Project -> NewCaloriy
 **@Author -> Sangeeth on 8/20/2022
 */
interface SpoonacularService {

    @GET("mealplanner/generate")
    fun getMeals(
        @Query("apiKey") apiKey: String,
        @Query("timeFrame") timeFrame : String,
        @Query("targetCalories") targetCalories : String
    ) : retrofit2.Call<SpoonacularData>
}