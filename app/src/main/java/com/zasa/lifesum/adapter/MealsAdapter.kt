package com.zasa.lifesum.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.zasa.lifesum.R
import com.zasa.lifesum.response.Meal
import com.zasa.lifesum.ui.WebActivity

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
        var mListener : onItemClickListner? = null
        // Pass mListener directly (it is now nullable)
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

        }

    }

    override fun getItemCount() = meals.size

    inner class ViewHolder(
        itemView: View,
        listener: onItemClickListner?,
        ) : RecyclerView.ViewHolder(itemView){
        @SuppressLint("SetTextI18n")
        fun bindMeal(meal: Meal) {
            itemView.findViewById<TextView>(R.id.tvMealTitle).text = meal.title
            itemView.findViewById<TextView>(R.id.tvReadyInMinutes).text = "Ready in Minutes : ${meal.readyInMinutes}"
            itemView.findViewById<TextView>(R.id.tvServings).text = "Servings can be ${meal.servings} Members"
        }

        val button = itemView.findViewById<Button>(R.id.btnGetLink)

        init {
            itemView.setOnClickListener {
                // adapterPosition is deprecated, use bindingAdapterPosition instead
                val position = bindingAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    listener!!.onItemClick(position)
                }
            }
        }

    }
}

