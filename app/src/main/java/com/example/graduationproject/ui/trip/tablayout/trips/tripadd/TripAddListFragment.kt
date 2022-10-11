package com.example.graduationproject.ui.trip.tablayout.trips.tripadd

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.graduationproject.BR
import com.example.graduationproject.R
import com.example.graduationproject.databinding.FragmentTripAddListBinding
import com.example.graduationproject.di.TravelApi
import com.example.graduationproject.model.general.GeneralModel
import com.example.graduationproject.ui.search.NearbyAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TripAddListFragment : Fragment() {

    private lateinit var binding : FragmentTripAddListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTripAddListBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sharedPreferences = activity?.getSharedPreferences("trip_add_detail_content", Context.MODE_PRIVATE)
        val editor = sharedPreferences?.edit()
        TravelApi.retrofitService.getTravel().enqueue(object : Callback<List<GeneralModel>> {
            override fun onResponse(
                call: Call<List<GeneralModel>>,
                response: Response<List<GeneralModel>>
            ) {
                val list = ArrayList(response.body()!!)
                val adapter = NearbyAdapter(list) {
                    findNavController().apply {
                        navigate(R.id.action_tripAddListFragment_to_tripAddDetailFragment)
                    }

                    editor?.putString("title", it.title)
                    editor?.putString("country", it.country)
                    editor?.putString("city", it.city)
                    editor?.putString("description", it.description)
                    editor?.putString("img_src", it.images[0].url)
                    editor?.apply()

                }

                binding.setVariable(BR.adapter, adapter)
            }

            override fun onFailure(call: Call<List<GeneralModel>>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }
}