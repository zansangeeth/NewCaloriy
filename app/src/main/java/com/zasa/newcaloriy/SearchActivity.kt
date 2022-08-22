package com.zasa.newcaloriy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_search.*

class SearchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        btnGetMeals.setOnClickListener {
            val mealsIntent = Intent(this, MainActivity::class.java)
            val targetCalories = etCalories.text.toString()
            mealsIntent.putExtra("targetCalories", targetCalories)
            startActivity(mealsIntent)
        }


    }
}