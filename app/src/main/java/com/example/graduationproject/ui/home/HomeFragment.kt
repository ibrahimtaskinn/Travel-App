package com.example.graduationproject.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.graduationproject.ui.home.tablayout.TabLayoutAdapter
import com.example.graduationproject.databinding.FragmentHomeBinding
import com.google.android.material.tabs.TabLayoutMediator

class HomeFragment : Fragment() {

    private lateinit var fragmentHomeBinding: FragmentHomeBinding
    private var tabTitle = arrayOf("All","Flights","Hotels","Transportation")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentHomeBinding = FragmentHomeBinding.inflate(layoutInflater)
        return fragmentHomeBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val pager = fragmentHomeBinding.viewPager2
        val tl = fragmentHomeBinding.tabLayout

        pager.adapter = TabLayoutAdapter(this)

        TabLayoutMediator(tl,pager){
                tab,position ->
            tab.text = tabTitle[position]
        }.attach()

        fragmentHomeBinding.apply {
            button.setOnClickListener{Toast.makeText(activity, "Clicked Flights", Toast.LENGTH_SHORT).show()}
            button1.setOnClickListener{Toast.makeText(activity, "Clicked Hotels", Toast.LENGTH_SHORT).show()}
            button2.setOnClickListener{Toast.makeText(activity, "Clicked Cars", Toast.LENGTH_SHORT).show()}
            button3.setOnClickListener{Toast.makeText(activity, "Clicked Taxi", Toast.LENGTH_SHORT).show()}
        }
    }
}