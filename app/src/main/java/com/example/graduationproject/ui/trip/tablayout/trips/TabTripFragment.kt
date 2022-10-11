package com.example.graduationproject.ui.trip.tablayout.trips

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.graduationproject.*
import com.example.graduationproject.data.TripDatabase
import com.example.graduationproject.databinding.FragmentTabTripBinding
import com.example.graduationproject.model.trip.TripModel

class TabTripFragment : Fragment() {

    private lateinit var binding: FragmentTabTripBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTabTripBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tripDatabase = TripDatabase.getTripDatabase(requireActivity().applicationContext)
        val trip: ArrayList<TripModel> = tripDatabase?.tripDao()?.getAllTrip() as ArrayList<TripModel>

        val adapter = TripAddAdapter(trip){

        }
        binding.setVariable(BR.adapter,adapter)
    }
}