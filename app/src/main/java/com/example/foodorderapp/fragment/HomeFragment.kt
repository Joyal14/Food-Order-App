package com.example.foodorderapp.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodorderapp.Adapter.MealsAdapter
import com.example.foodorderapp.R
import com.example.foodorderapp.databinding.FragmentHomeBinding
import com.example.foodorderapp.pojo.MealLists
import com.example.foodorderapp.retrofit.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    lateinit var adapter: MealsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentHomeBinding.inflate(inflater,container,false)
        // Inflate the layout for this fragment
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        RetrofitInstance.api.getRandomMeals().enqueue(object : Callback<MealLists>{
            override fun onResponse(call: Call<MealLists>, response: Response<MealLists>) {
                val meals =response.body()
                if(meals != null){
                Log.d("meals message",meals.toString())
                adapter = MealsAdapter(this@HomeFragment,meals.meals)
                binding.rvRandom.adapter = adapter
                binding.rvRandom.layoutManager = LinearLayoutManager(activity,LinearLayoutManager.VERTICAL,false)
                }
            }

            override fun onFailure(call: Call<MealLists>, t: Throwable) {

            }

        })
    }

}