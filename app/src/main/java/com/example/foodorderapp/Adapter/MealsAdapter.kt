package com.example.foodorderapp.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.foodorderapp.databinding.ItemListBinding
import com.example.foodorderapp.fragment.HomeFragment
import com.example.foodorderapp.pojo.Meal

class MealsAdapter(var context: HomeFragment, var meal: List<Meal>):
    RecyclerView.Adapter<MealsAdapter.MealViewHolder>() {

    class MealViewHolder(itemView: ItemListBinding) : RecyclerView.ViewHolder(itemView.root) {
        var mealsImage: ImageView = itemView.imgNews
        var title: TextView = itemView.txtTitle
        var link: TextView = itemView.txtdisplay
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealViewHolder {
        val binding = ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MealViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return meal.size
    }

    override fun onBindViewHolder(holder: MealViewHolder, position: Int) {
        val meal = meal[position]
        holder.title.text = meal.strCategory
        holder.link.text = meal.strYoutube

        //for Image display need to use Glide lb retrofit not support imageView
        Glide.with(context).load(meal.strImageSource).into(holder.mealsImage)

        holder.itemView.setOnClickListener {

        }
    }
}