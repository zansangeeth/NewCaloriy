package com.zasa.newcaloriy

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView

/**
 **@Project -> NewCaloriy
 **@Author -> Sangeeth on 8/20/2022
 */
class MealsAdapter(val context: Context, private val meals : List<Meal>) : RecyclerView.Adapter<MealsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_meal, parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val meal =meals[position]
        holder.bindMeal(meal)
    }

    override fun getItemCount() = meals.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bindMeal(meal: Meal) {
            itemView.findViewById<TextView>(R.id.tvMealTitle).text = meal.title
            itemView.findViewById<Button>(R.id.btnGetLink).isClickable = true
            itemView.findViewById<TextView>(R.id.tvReadyInMinutes).text = "Ready in Minutes : ${meal.readyInMinutes}"
            itemView.findViewById<TextView>(R.id.tvServings).text = "Servings can be ${meal.servings} Members"
        }
    }
}

