package com.example.graduationproject.ui.guide

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.graduationproject.BR
import com.example.graduationproject.R
import com.example.graduationproject.databinding.FragmentGuideBinding
import com.example.graduationproject.di.TravelApi
import com.example.graduationproject.model.general.GeneralModel
import com.example.graduationproject.model.guidecategories.GuideCategoriesModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GuideFragment : Fragment() {

    private lateinit var guideBinding:FragmentGuideBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        guideBinding = FragmentGuideBinding.inflate(layoutInflater)
        return guideBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        guideBinding.guideSearchBottom.setOnClickListener {
            findNavController().apply {
                navigate(R.id.action_guide_page_to_searchItemFragment)
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

                val category = "mightneed"
                val category2 = "toppick"
                val filterList = list.filter { it.category == category}
                val filterList2 = list.filter { it.category == category2 }
                val adapter = MightneedAdapter(filterList
                ){
                    findNavController().apply {
                        navigate(R.id.action_guide_page_to_detailFragment)
                    }

                    editor?.putString("title", it.title)
                    editor?.putString("description", it.description)
                    editor?.putString("country", it.country)
                    editor?.putString("city", it.city)
                    editor?.putString("img_src", it.images[0].url)
                    editor?.apply()
                }

                val adapter2 = ToppickAdapter(filterList2
                ){
                    findNavController().apply {
                        navigate(R.id.action_guide_page_to_detailFragment)
                    }

                    editor?.putString("title", it.title)
                    editor?.putString("description", it.description)
                    editor?.putString("country", it.country)
                    editor?.putString("city", it.city)
                    editor?.putString("img_src", it.images[0].url)
                    editor?.apply()
                }
                guideBinding.setVariable(BR.adapter,adapter)
                guideBinding.setVariable(BR.adapter2,adapter2)
            }

            override fun onFailure(call: Call<List<GeneralModel>>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })

        TravelApi.retrofitService.getTravel2().enqueue(object : Callback<List<GuideCategoriesModel>> {
            override fun onResponse(
                call2: Call<List<GuideCategoriesModel>>,
                response2: Response<List<GuideCategoriesModel>>
            ) {
                val list2 = ArrayList(response2.body()!!)

                val adapter3 = GuideCategoryAdapter(list2
                ){
                    Toast.makeText(activity, "Clicked "+ it.title, Toast.LENGTH_SHORT).show()
                }

                guideBinding.setVariable(BR.adapter3,adapter3)
            }

            override fun onFailure(call2: Call<List<GuideCategoriesModel>>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })

    }
}