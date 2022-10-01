package com.zasa.newcaloriy.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.zasa.newcaloriy.R
import com.zasa.newcaloriy.response.Meal
import com.zasa.newcaloriy.ui.WebActivity
import kotlinx.android.synthetic.main.activity_search.*

/**
 **@Project -> NewCaloriy
 **@Author -> Sangeeth on 8/20/2022
 */
class MealsAdapter(val context: Context, private val meals : List<Meal>) : RecyclerView.Adapter<MealsAdapter.ViewHolder>() {

    lateinit var mListener : onItemClickListner

    interface onItemClickListner{
        fun onItemClick(position: Int)
    }


    fun setOnItemClickListener(listener: onItemClickListner){
        mListener = listener
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_meal, parent,false), mListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val meal =meals[position]
        holder.bindMeal(meal)
        holder.button.setOnClickListener {
            val intent = Intent(context, WebActivity::class.java)
            intent.putExtra("title", meals[position].title)
            intent.putExtra("sourceUrl", meals[position].sourceUrl)
            context.startActivity(intent)
            holder.button.isEnabled = false
        }

    }

    override fun getItemCount() = meals.size

    inner class ViewHolder(
        itemView: View,
        listener: onItemClickListner,
        ) : RecyclerView.ViewHolder(itemView){
        fun bindMeal(meal: Meal) {
            itemView.findViewById<TextView>(R.id.tvMealTitle).text = meal.title
            itemView.findViewById<TextView>(R.id.tvReadyInMinutes).text = "Ready in Minutes : ${meal.readyInMinutes}"
            itemView.findViewById<TextView>(R.id.tvServings).text = "Servings can be ${meal.servings} Members"
        }

        val button = itemView.findViewById<Button>(R.id.btnGetLink)

        init {
            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }
    }
}

