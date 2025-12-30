package com.zasa.lifesum.ui

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
import com.zasa.lifesum.*
import com.zasa.lifesum.adapter.MealsAdapter
import com.zasa.lifesum.api.SpoonacularService
import com.zasa.lifesum.databinding.ActivityMainBinding
import com.zasa.lifesum.response.Meal
import com.zasa.lifesum.response.SpoonacularData
import com.zasa.lifesum.utils.Constants.API_KEY
import com.zasa.lifesum.utils.RetrofitCall
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var mAdView: AdView

    val meals = mutableListOf<Meal>()

    val mealAdapter = MealsAdapter(this, meals)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
        setTheme(R.style.Theme_NewCaloriy)

//        setContentView(R.layout.activity_main)

        MobileAds.initialize(this) {}

        mAdView = findViewById(R.id.adView)
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)



        val spoonacularService = RetrofitCall.retrofit.create(SpoonacularService::class.java)
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
                        binding.rvMeals.apply {
                            layoutManager = LinearLayoutManager(this@MainActivity)
                            adapter = mealAdapter
                            meals.addAll(body.meals)
                            mealAdapter.notifyDataSetChanged()
                        }
                        binding.tvCalories.text = "Calories : ${body.nutrients.calories}"
                        binding.tvCarbohydrates.text = "Carbohydrates : ${body.nutrients.carbohydrates}"
                        binding.tvFat.text = "Fat : ${body.nutrients.fat}"
                        binding.tvProtein.text = "Protein : ${body.nutrients.protein}"

                        binding.shimmerViewContainer.stopShimmer()
                        binding.shimmerViewContainer.visibility = View.GONE


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
                    }

                }


                override fun onFailure(call: Call<SpoonacularData>, t: Throwable) {
                    Log.i(TAG, "$t")
                }


            })

        // Add this inside onCreate()
        onBackPressedDispatcher.addCallback(this, object : androidx.activity.OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                // Your custom back logic
                startActivity(Intent(this@MainActivity, SearchActivity::class.java))
                finish()
            }
        })

    }



}