package com.example.graduationproject.ui.search

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.graduationproject.BR
import com.example.graduationproject.R
import com.example.graduationproject.databinding.FragmentSearchBinding
import com.example.graduationproject.di.TravelApi
import com.example.graduationproject.model.general.GeneralModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchFragment : Fragment() {

    private lateinit var searchFragmentBinding: FragmentSearchBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        searchFragmentBinding = FragmentSearchBinding.inflate(layoutInflater)
        return searchFragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        searchFragmentBinding.searchBottom.setOnClickListener {
            findNavController().apply {
                navigate(R.id.action_search_page_to_searchItemFragment)
            }
        }

        val sharedPreferences = activity?.getSharedPreferences("detail_content", Context.MODE_PRIVATE)
        val editor = sharedPreferences?.edit()

        TravelApi.retrofitService.getTravel().enqueue(object : Callback<List<GeneralModel>> {
            override fun onResponse(
                call: Call<List<GeneralModel>>,
                response: Response<List<GeneralModel>>
            ) {
                val list = ArrayList(response.body()!!)

                val category = "topdestination"
                val category2 = "nearby"
                val filterList = list.filter { it.category == category}
                val filterList2 = list.filter { it.category == category2 }
                val adapter = TopDestinationsAdapter(filterList
                ){
                    findNavController().apply {
                        navigate(R.id.action_search_page_to_detailFragment)
                    }

                    editor?.putString("title", it.title)
                    editor?.putString("description", it.description)
                    editor?.putString("country", it.country)
                    editor?.putString("city", it.city)
                    editor?.putString("img_src", it.images[0].url)
                    editor?.apply()
                }

                val adapter2 = NearbyAdapter(filterList2){
                    findNavController().apply {
                        navigate(R.id.action_search_page_to_detailFragment)
                    }

                    editor?.putString("title", it.title)
                    editor?.putString("description", it.description)
                    editor?.putString("country", it.country)
                    editor?.putString("city", it.city)
                    editor?.putString("img_src", it.images[0].url)
                    editor?.apply()
                }
                searchFragmentBinding.setVariable(BR.adapter2,adapter2)
                searchFragmentBinding.setVariable(BR.adapter,adapter)
            }

            override fun onFailure(call: Call<List<GeneralModel>>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }
}