package com.zasa.newcaloriy.ui

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import com.zasa.newcaloriy.*
import com.zasa.newcaloriy.adapter.MealsAdapter
import com.zasa.newcaloriy.api.SpoonacularService
import com.zasa.newcaloriy.response.Meal
import com.zasa.newcaloriy.response.SpoonacularData
import com.zasa.newcaloriy.utils.Constants.API_KEY
import com.zasa.newcaloriy.utils.Constants.BASE_URL
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_meal.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {
    lateinit var mAdView: AdView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_NewCaloriy)
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
                @SuppressLint("NotifyDataSetChanged", "SetTextI18n")
                override fun onResponse(
                    call: Call<SpoonacularData>,
                    response: Response<SpoonacularData>
                ) {

                    val body = response.body()
                    if (body == null) {
                        Log.w(TAG, "failed to fetch data..")
                        return
                    } else {
                        Log.i(TAG, "$response")
                        rvMeals.apply {
                            layoutManager = LinearLayoutManager(this@MainActivity)
                            adapter = mealAdapter
                            meals.addAll(body.meals)
                            mealAdapter.notifyDataSetChanged()
                        }

                        tvCalories.text = "Calories : ${body.nutrients.calories}"
                        tvCarbohydrates.text = "Carbohydrates : ${body.nutrients.carbohydrates}"
                        tvFat.text = "Fat : ${body.nutrients.fat}"
                        tvProtein.text = "Protein : ${body.nutrients.protein}"

                        shimmer_view_container.stopShimmer()
                        shimmer_view_container.visibility = View.GONE


                        mealAdapter.setOnItemClickListener(object :
                            MealsAdapter.onItemClickListner {
                            override fun onItemClick(position: Int) {
                                Toast.makeText(
                                    this@MainActivity,
                                    "you clicked $position",
                                    Toast.LENGTH_SHORT
                                ).show()
                                val webViewIntent =
                                    Intent(this@MainActivity, WebActivity::class.java)
                                webViewIntent.putExtra("title", meals[position].title)
                                webViewIntent.putExtra("sourceUrl", meals[position].sourceUrl)
                                startActivity(webViewIntent)
                            }
                        })

//                        mealAdapter.setOnBtnClickListener(object : MealsAdapter.onButtonClicked{
//                            override fun onButtonItemClicked(position: Int) {
//                                Toast.makeText(
//                                    this@MainActivity,
//                                    "you clicked the button $position",
//                                    Toast.LENGTH_SHORT
//                                ).show()
//                            }
//                        })

                    }

                }


                override fun onFailure(call: Call<SpoonacularData>, t: Throwable) {
                    Log.i(TAG, "$t")
                }

            })

    }


}