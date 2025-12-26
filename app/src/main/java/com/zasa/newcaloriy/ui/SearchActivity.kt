package com.zasa.newcaloriy.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.zasa.newcaloriy.R
import com.zasa.newcaloriy.databinding.ActivitySearchBinding


class SearchActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySearchBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySearchBinding.inflate(layoutInflater)

        setTheme(R.style.Theme_NewCaloriy)
        setContentView(binding.root)

        binding.btnGetMeals.setOnClickListener {

            val targetCalories = binding.etCalories.text.toString()
            if (targetCalories.isNullOrEmpty()) {
                Toast.makeText(this, "Please enter the calories amount", Toast.LENGTH_SHORT).show()
            } else if (targetCalories.toInt() in 1000..3000) {
                val mealsIntent = Intent(this, MainActivity::class.java)
                mealsIntent.putExtra("targetCalories", targetCalories)
                startActivity(mealsIntent)
                binding.btnGetMeals.isEnabled = false
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