package com.example.graduationproject.ui.trip

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.graduationproject.R
import com.example.graduationproject.ui.trip.tablayout.TripTablayoutAdapter
import com.example.graduationproject.databinding.FragmentTripBinding
import com.google.android.material.tabs.TabLayoutMediator

class TripFragment : Fragment() {

    private lateinit var binding: FragmentTripBinding
    private var tabTitle = arrayOf("Trips","Bookmark")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTripBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val pager = binding.viewPager2
        val tl = binding.tabLayout

        pager.adapter = TripTablayoutAdapter(this)

        TabLayoutMediator(tl,pager){
                tab,position ->
            tab.text = tabTitle[position]
        }.attach()

        binding.tripAddBottom.setOnClickListener {
            findNavController().apply {
                navigate(R.id.action_trip_page_to_tripAddListFragment)

            }
        }
    }
}