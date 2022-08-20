package com.zasa.newcaloriy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://api.spoonacular.com/"
private const val TAG = "MainActivity"
private const val API_KEY = "5daeba7d6f3045b3affa64e67a2329c3"

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val meals = mutableListOf<Meal>()
        val adapter = MealsAdapter(this, meals)
        rvMeals.adapter = adapter
        rvMeals.layoutManager = LinearLayoutManager(this)

        val retrofit =
            Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
                .build()

        val spoonacularService = retrofit.create(SpoonacularService::class.java)
        spoonacularService.getMeals("$API_KEY", "day", "1000")
            .enqueue(object : Callback<SpoonacularData> {
                override fun onResponse(
                    call: Call<SpoonacularData>,
                    response: Response<SpoonacularData>
                ) {
                    val body = response.body()
                    if (body == null) {
                        Log.w(TAG, "failed to fetch data..")
                        return
                    }
                    Log.i(TAG, "$response")
                    meals.addAll(body.meals)
                    adapter.notifyDataSetChanged()
                }

                override fun onFailure(call: Call<SpoonacularData>, t: Throwable) {
                    Log.i(TAG, "$t")
                }

            })


    }
}