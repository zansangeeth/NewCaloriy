package com.zasa.newcaloriy.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.zasa.newcaloriy.R
import kotlinx.android.synthetic.main.activity_search.*

class SearchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_NewCaloriy)
        setContentView(R.layout.activity_search)

        btnGetMeals.setOnClickListener {

            val targetCalories = etCalories.text.toString()
            if (targetCalories.isNullOrEmpty()) {
                Toast.makeText(this, "Please enter the calories amount", Toast.LENGTH_SHORT).show()
            } else if (targetCalories.toInt() in 1000..3000) {
                val mealsIntent = Intent(this, MainActivity::class.java)
                mealsIntent.putExtra("targetCalories", targetCalories)
                startActivity(mealsIntent)
                btnGetMeals.isEnabled = false
            } else {
                Toast.makeText(
                    this,
                    "Calorie Amount per day between 1000 to 3000",
                    Toast.LENGTH_SHORT
                ).show()
            }

        }

    }
}