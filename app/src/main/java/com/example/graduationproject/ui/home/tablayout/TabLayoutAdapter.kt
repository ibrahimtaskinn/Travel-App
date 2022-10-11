package com.example.graduationproject.ui.home.tablayout

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.graduationproject.ui.home.HomeFragment
import com.example.graduationproject.ui.home.tablayout.all.AllFragment
import com.example.graduationproject.ui.home.tablayout.flights.FlightsFragment
import com.example.graduationproject.ui.home.tablayout.hotels.HotelsFragment
import com.example.graduationproject.ui.home.tablayout.transportations.TransportationsFragment

class TabLayoutAdapter(activity: HomeFragment) : FragmentStateAdapter(activity) {
    override fun getItemCount(): Int {
        return 4
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> AllFragment()
            1 -> FlightsFragment()
            2 -> HotelsFragment()
            3 -> TransportationsFragment()
            else -> AllFragment()
        }
    }
}