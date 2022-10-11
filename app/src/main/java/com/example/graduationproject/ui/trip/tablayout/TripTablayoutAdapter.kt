package com.example.graduationproject.ui.trip.tablayout

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.graduationproject.ui.trip.TripFragment
import com.example.graduationproject.ui.trip.tablayout.bookmark.TabBookmarkFragment
import com.example.graduationproject.ui.trip.tablayout.trips.TabTripFragment

class TripTablayoutAdapter(activity: TripFragment): FragmentStateAdapter(activity) {

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> TabTripFragment()
            1 -> TabBookmarkFragment()
            else -> TabTripFragment()
        }
    }
}