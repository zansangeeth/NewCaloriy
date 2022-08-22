package com.zasa.newcaloriy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
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
    lateinit var mAdView :AdView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        MobileAds.initialize(this) {}

        mAdView = findViewById(R.id.adView)
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)



        val meals = mutableListOf<Meal>()
        val mealAdapter = MealsAdapter(this, meals)

        val retrofit =
            Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
                .build()

        val spoonacularService = retrofit.create(SpoonacularService::class.java)
        val targetCalories = intent.getStringExtra("targetCalories")


        spoonacularService.getMeals("$API_KEY", "day", "$targetCalories")
            .enqueue(object : Callback<SpoonacularData> {
                override fun onResponse(
                    call: Call<SpoonacularData>,
                    response: Response<SpoonacularData>
                ) {
                    val body = response.body()
                    if (body == null) {
                        Log.w(TAG, "failed to fetch data..")
                        return
                    }else{
                        Log.i(TAG, "$response")
//                        rvMeals.apply {
//                            layoutManager = LinearLayoutManager(this@MainActivity)
//                            adapter = mealAdapter
//                            meals.addAll(body.meals)
//                            mealAdapter.notifyDataSetChanged()
//                        }
                        val mealInfo : SpoonacularData = response.body()!!
                        meals.addAll(mealInfo.meals)
                        rvMeals.layoutManager = LinearLayoutManager(this@MainActivity)
                        rvMeals.adapter = mealAdapter
                        mealAdapter.notifyDataSetChanged()

                    }

                }

                override fun onFailure(call: Call<SpoonacularData>, t: Throwable) {
                    Log.i(TAG, "$t")
                }

            })

    }
}