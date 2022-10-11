package com.example.graduationproject.ui.search.searchitem

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.appcompat.widget.SearchView
import androidx.navigation.fragment.findNavController
import com.example.graduationproject.BR
import com.example.graduationproject.R
import com.example.graduationproject.databinding.FragmentSearchItemBinding
import com.example.graduationproject.di.TravelApi
import com.example.graduationproject.model.general.GeneralModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import kotlin.collections.ArrayList

class SearchItemFragment : Fragment() {

    private lateinit var binding: FragmentSearchItemBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchItemBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.SearchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                filter(newText)
                return false
            }
        })
    }

    fun filter(newText: String){
        val sharedPreferences = activity?.getSharedPreferences("detail_content", Context.MODE_PRIVATE)
        val editor = sharedPreferences?.edit()

        TravelApi.retrofitService.getTravel().enqueue(object : Callback<List<GeneralModel>> {
            override fun onResponse(
                call: Call<List<GeneralModel>>,
                response: Response<List<GeneralModel>>
            ) {
                val list = ArrayList(response.body()!!)

                val filterList = list.filter { it.title.lowercase().contains(newText.lowercase())}
                val adapter = SearchItemAdapter(filterList
                ) {
                    findNavController().apply {
                        navigate(R.id.action_searchItemFragment_to_detailFragment)
                    }
                    editor?.putString("title", it.title)
                    editor?.putString("description", it.description)
                    editor?.putString("country", it.country)
                    editor?.putString("city", it.city)
                    editor?.putString("img_src", it.images[0].url)
                    editor?.apply()
                }
                binding.setVariable(BR.adapter,adapter)
            }
            override fun onFailure(call: Call<List<GeneralModel>>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }
}
