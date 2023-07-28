package com.example.foodorderapp.retrofit

import com.example.foodorderapp.pojo.MealLists
import retrofit2.Call
import retrofit2.http.GET

interface MealsApi {
    @GET("random.php")
    fun getRandomMeals(): Call<MealLists>

}