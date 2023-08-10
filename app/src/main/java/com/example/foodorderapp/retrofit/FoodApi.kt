package com.example.foodorderapp.retrofit

import com.example.foodorderapp.pojo.Category
import com.example.foodorderapp.pojo.MealLists
import retrofit2.Call
import retrofit2.http.GET

interface FoodApi {
    @GET("categories.php")
    fun getRandomMeal(): Call<Category>
}