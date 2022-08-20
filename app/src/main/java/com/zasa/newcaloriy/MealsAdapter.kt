package com.zasa.newcaloriy

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.zasa.newcaloriy.MealsAdapter.ViewHolder

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
        holder.bind(meal)
    }

    override fun getItemCount() = meals.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bind(meal: Meal) {
            itemView.findViewById<TextView>(R.id.tvMealTitle).text = meal.title
            itemView.findViewById<TextView>(R.id.tvUrl).text = meal.sourceUrl

        }


    }
}