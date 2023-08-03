package com.example.foodorderapp.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foodorderapp.Adapter.MealAdapter
import com.example.foodorderapp.databinding.FragmentHomeBinding
import com.example.foodorderapp.pojo.MealLists
import com.example.foodorderapp.retrofit.RetrofitInstance
import retrofit2.Call
import retrofit2.Response

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var myadapter: MealAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var mealsList : ArrayList<MealLists>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    getNews()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       getNews()
    }
    private fun getNews() {
        val meals = RetrofitInstance.foodApi.getRandomMeal()
        meals.enqueue(object : retrofit2.Callback<MealLists> {
            override fun onResponse(call: Call<MealLists>, response: Response<MealLists>) {
                val meals = response.body()
                if (meals != null) {
                    Log.d("News error", meals.toString())
                    myadapter = context?.let { MealAdapter(it,meals.meals) }!!
                    binding.rvItem.adapter = myadapter
                    binding.rvItem.layoutManager = LinearLayoutManager(context)
                }
            }


            override fun onFailure(call: Call<MealLists>, t: Throwable) {
                Log.d("News error", "Error in Fetching in news")
            }
        })
    }
}