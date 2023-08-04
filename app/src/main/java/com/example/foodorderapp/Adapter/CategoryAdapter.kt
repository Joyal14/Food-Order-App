package com.example.foodorderapp.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.foodorderapp.R
import com.example.foodorderapp.pojo.Category

class CategoryAdapter(private val context: Context, private val categories:List<Category>) : RecyclerView.Adapter<CategoryAdapter.MealViewHolder>(){
    inner class MealViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var mealImage: ImageView = itemView.findViewById(R.id.imgNews)
        var title: TextView = itemView.findViewById(R.id.txtTitle)
        var display: TextView = itemView.findViewById(R.id.txtdisplay)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_list, parent, false)
       return MealViewHolder(view)
    }

    override fun getItemCount(): Int {
       return categories.size
    }

    override fun onBindViewHolder(holder: MealViewHolder, position: Int) {
        val meal = categories[position]
        holder.title.text = meal.strCategory
        holder.display.text = meal.strCategoryDescription

        //for Image display need to use Glide lb retrofit not support imageView
        Glide.with(context).load(meal.strCategoryThumb).into(holder.mealImage)

        holder.itemView.setOnClickListener {
            Toast.makeText(context, meal.strCategory, Toast.LENGTH_SHORT).show()
        }

    }
}